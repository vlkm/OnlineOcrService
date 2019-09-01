package tk.virginijus.webocrapp;

import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;

@SpringBootApplication
public class WebocrappApplication {

    public static void main(String[] args) {
        String inputFilePath = "/files/Testproc1.jpg";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("/lib");
        try {
            String fullText  = tesseract.doOCR(new File(inputFilePath));
            System.out.println(fullText);
        } catch (TesseractException e) {
            e.printStackTrace();
        }


        SpringApplication.run(WebocrappApplication.class, args);
    }

}
