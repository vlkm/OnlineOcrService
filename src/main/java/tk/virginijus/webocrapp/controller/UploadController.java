package tk.virginijus.webocrapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tk.virginijus.webocrapp.service.ImageReadService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Controller
public class UploadController {

    //folder for the uploaded images
    private static String UPLOADED_FOLDER = "/images/";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("inputName") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a image to upload");
            return "redirect:/";
        }

        try {
            //Check the directory
            File fileDir = new File(UPLOADED_FOLDER);
            if (!fileDir.exists()) {
                if (fileDir.mkdir()) {
                    System.out.println("Directory is created! :" + UPLOADED_FOLDER);
                } else {
                    System.out.println("Failed to create directory! :" + UPLOADED_FOLDER );
                }
            }
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            ImageReadService imageReadService = new ImageReadService();
            String text = imageReadService.readImage(path);
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