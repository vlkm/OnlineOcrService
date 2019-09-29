package tk.virginijus.onlineocrservice.service;
//controller reads image file and converts to string

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;



@Component
public class ImageReadService{
@ResponseBody
    public String readImage(String libFolder, String inputFilePath) {

        String fullText = "";
        Tesseract tesseract = new Tesseract();
            try {
                //Check tessdata
                File libr = new File(libFolder + "/tessdata/eng.traineddata");
                if (libr.exists()) System.out.println("Good for Linux libFolder! :" + libFolder );
                libr = new File(libFolder + "/eng.traineddata");
                if (libr.exists()) System.out.println("Good for Windows libFolder! :" + libFolder );
                tesseract.setDatapath( libFolder );
//more languages https://github.com/tesseract-ocr/tesseract/wiki/Data-Files
                tesseract.setLanguage("lit"); //defoult "eng"
                File imageToRead = new File(inputFilePath);
                if (imageToRead.exists()) {
                    System.out.println("Read image! :" + imageToRead );
                    fullText  = tesseract.doOCR(imageToRead);
                }
                System.out.println(fullText);
            } catch (TesseractException e) {
                e.printStackTrace();
            }
        return  fullText;
    }
}

