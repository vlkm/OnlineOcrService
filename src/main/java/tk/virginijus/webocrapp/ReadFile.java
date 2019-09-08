package tk.virginijus.webocrapp;
//controller reads image file and converts to string

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;

public class ReadFile {

        public static void main(String[] args) {
            String inputPath  =  "target/classes/";
            String inputFilePath = inputPath + "files/OA.png";
            Tesseract tesseract = new Tesseract();
            try {
                tesseract.setDatapath(inputPath + "lib");
                //https://github.com/tesseract-ocr/tesseract/wiki/Data-Files
//              tesseract.setLanguage("eng");
                tesseract.setLanguage("lit");
                String fullText  = tesseract.doOCR(new File(inputFilePath));
                System.out.println(fullText);
            } catch (TesseractException e) {
                e.printStackTrace();
            }
        }
}
