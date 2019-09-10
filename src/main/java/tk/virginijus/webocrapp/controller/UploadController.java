package tk.virginijus.webocrapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tk.virginijus.webocrapp.WebocrappApplication;
import tk.virginijus.webocrapp.service.ImageReadService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Controller
public class UploadController {

    //Start folder for the uploaded images and libraries
    private static String UPLOADED_FOLDER = "";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("inputName") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        String inputPath  =  "target/classes/";
//        file:/root/webocrapp-0.0.1.war!/WEB-INF/classes!/
        String currentPath = WebocrappApplication.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath()
                ;
        int i= 0;
        //remove all till program folder webocrapp
        System.out.println("Current Path get: " + currentPath);
        i = currentPath.toLowerCase().lastIndexOf(":") + 1;
        if (i > -1) { currentPath = currentPath.substring(i);
        System.out.println("Current Path >: " + currentPath);}
        i = currentPath.toLowerCase().lastIndexOf("webocrapp");
        if (i >0 ) currentPath = currentPath.substring(0,i);
        System.out.println("Current Path <: " + currentPath);
        i = currentPath.toLowerCase().lastIndexOf("webocrapp");
        if (i >0 ) { currentPath = currentPath.substring(0,i);
        System.out.println("Current Path <: " + currentPath);}
        //remove "/" at the end
        String rentPath = currentPath;
        if (rentPath.endsWith("/")) {
            i = currentPath.length();
            if (i>1) { i--;
            currentPath = currentPath.substring(0,i);}
        System.out.println("Current Path /: " + currentPath);}


        UPLOADED_FOLDER = currentPath + "/images";
        inputPath = UPLOADED_FOLDER + "/" + file.getOriginalFilename();

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a image to upload");
            return "redirect:/";
        }

        try {
            //Check the directory
            File fileDir = new File(UPLOADED_FOLDER + "/");
            if (!fileDir.exists()) {
                if (fileDir.mkdir()) {
                    System.out.println("Directory is created! :" + UPLOADED_FOLDER);
                } else {
                    System.out.println("Failed to create directory! :" + UPLOADED_FOLDER );
                }
            } else {
                System.out.println("Used directory! :" + UPLOADED_FOLDER );
            }
            // Get the file and save it
            byte[] bytes = file.getBytes();
            Path path = Paths.get(inputPath);
            System.out.println("Image :" + path);
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

            ImageReadService imageReadService = new ImageReadService();
            String text = imageReadService.readImage(currentPath + "/lib" , path.toString());
            redirectAttributes.addFlashAttribute(  "text",  text);
            redirectAttributes.addFlashAttribute(  "time",  "Time : " + new Date());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}