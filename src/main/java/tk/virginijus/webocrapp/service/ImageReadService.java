package tk.virginijus.webocrapp.service;
//controller reads image file and converts to string

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Component;
import java.io.File;


@Component
public class ImageReadService{

    public String readImage(String libFolder, String inputFilePath) {

        String fullText = "";
        Tesseract tesseract = new Tesseract();
            try {
                System.out.println("Used libFolder! :" + libFolder );
                tesseract.setDatapath( libFolder );
//more languages https://github.com/tesseract-ocr/tesseract/wiki/Data-Files
//              tesseract.setLanguage("eng");
                tesseract.setLanguage("lit");
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

