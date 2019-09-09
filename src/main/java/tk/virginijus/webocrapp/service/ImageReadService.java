package tk.virginijus.webocrapp.service;
//controller reads image file and converts to string

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Component;
import tk.virginijus.webocrapp.WebocrappApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ImageReadService{

    public String readImage(Path inputFilePath) {

        String fullText = "";
        String inputPath  =  "target/classes/";
//         = WebocrappApplication.class.getResource("").getPath();
        final String currentPath = WebocrappApplication.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println(currentPath);
//        to get on server libraries
//        if (currentPath == "/root/") {
//
//            inputPath = currentPath + "lib";
//             if (!Files.exists(inputPath)) {
//             try {
//                    Files.createDirectories(inputPath);
//                    System.out.println("Directory is creating! :" + inputPath);
//                 } catch (IOException e) {
//                 //fail to create directory
//                    e.printStackTrace();
//                    System.out.println("Failed to create directory! :" + inputPath );
//                 }
//             }
//            inputPath = currentPath;
//        }

//      String inputFilePath = inputPath + "files/OA.png";
            Tesseract tesseract = new Tesseract();
            try {
                tesseract.setDatapath(inputPath + "lib");
                //https://github.com/tesseract-ocr/tesseract/wiki/Data-Files
//              tesseract.setLanguage("eng");
                tesseract.setLanguage("lit");
                fullText  = tesseract.doOCR(new File(inputFilePath.toString()));
                System.out.println(fullText);
            } catch (TesseractException e) {
                e.printStackTrace();
            }
        return  fullText;
    }
}

