package pdfBox;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class prac2 {
	public static void main(String[] args) {
		PDDocument document = new PDDocument(); // PDDocument 객체 생성

		PDPage page1 = new PDPage();
		document.addPage(page1); // 페이지 추가
		
		try {
			PDPageContentStream contentStream = new PDPageContentStream(document, page1);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
			contentStream.showText("Hello, World!");
			contentStream.showText("Hello, World!!!!!");
			
			contentStream.setLeading(14.5f);

			// 수정할 텍스트 위치 지정
			float x = 100;
			float y = 600;

			contentStream.newLineAtOffset(x, y);
			contentStream.showText("Modified Text");
			
			contentStream.endText();
			contentStream.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			document.save("C:\\Users\\PC-14\\Documents\\입력예제.pdf");
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
