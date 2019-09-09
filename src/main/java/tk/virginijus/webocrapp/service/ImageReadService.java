package tk.virginijus.webocrapp.service;
//controller reads image file and converts to string

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Component;
import java.io.File;
import java.nio.file.Path;

@Component
public class ImageReadService{

    public String readImage(Path inputFilePath) {

        String fullText = "";
        String inputPath  =  "target/classes/";
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

