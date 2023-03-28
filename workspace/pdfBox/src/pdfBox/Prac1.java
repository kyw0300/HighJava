package pdfBox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.text.PDFTextStripper;

public class Prac1 {
	public static void main(String[] args) {
        try {
            // PDF 파일 열기
            PDDocument document = PDDocument.load(new File("d:/pdfBox/PhoneBookTest_고영우.pdf"));

            // PDF 파일에서 텍스트 추출하기
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println(text);
            
            PDPage page1 = document.getPage(0);
            PDRectangle mediaBox1 = page1.getMediaBox();
            float width1 = mediaBox1.getWidth();
            float height1 = mediaBox1.getHeight();
            System.out.println("1페이지의 가로 : " + width1);
            System.out.println("1페이지의 세로 : " + height1);
            
            PDPage page2 = document.getPage(0);
            PDRectangle mediaBox2 = page2.getMediaBox();
            float width2 = mediaBox2.getWidth();
            float height2 = mediaBox2.getHeight();
            System.out.println("1페이지의 가로 : " + width2);
            System.out.println("1페이지의 세로 : " + height2);
            
            // PDF 파일로 저장
//            document.save("C:\\Users\\PC-14\\Documents\\newpdf.pdf");

            // PDF 파일 닫기
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
