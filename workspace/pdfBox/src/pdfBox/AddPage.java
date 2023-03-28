package pdfBox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class AddPage {
	public static void main(String[] args) {
		PDDocument document = null;
		try {
			document = PDDocument.load(new File("C:\\Users\\PC-14\\Documents\\PDFBox\\prac01.pdf"));
			
			for(int i=0; i < 10; i++) {
				PDPage blackPage = new PDPage(PDRectangle.A4); // 빈 페이지 생성
				document.addPage(blackPage); // 빈 페이지 문서에 추가
			}
			
			document.save("C:\\Users\\PC-14\\Documents\\PDFBox\\prac01.pdf");
			System.out.println("PDF 생성완료");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { if(document != null) {
				try {
					document.close();
				} catch (IOException e) {}
			}
		}
	}
}
