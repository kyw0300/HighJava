package pdfBox;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class WritePDF {
	public static void main(String[] args) {
		PDDocument document = null;
		
		try {
			document = PDDocument.load(new File("C:\\Users\\PC-14\\Documents\\PDFBox\\prac01.pdf"));
			PDPage page1 = document.getPage(0);
			
//			float pageW = page1.getCropBox().getWidth(); // 가로,세로 길이 출력
//			float pageH = page1.getCropBox().getHeight();
//			System.out.println(pageW + ", " + pageH);
			
			PDImageXObject pdImage = new PDImageXObject(document).createFromFile(
										"C:\\Users\\PC-14\\Documents\\PDFBox\\사자.jpg", document); // 배경 이미지 로드;
			
			PDPageContentStream contentStream = new PDPageContentStream(document, page1);
			contentStream.drawImage(pdImage, 0, 400, 250, 250);
			contentStream.close();
			
			document.save("C:\\Users\\PC-14\\Documents\\PDFBox\\prac01.pdf");
			System.out.println("PDF 그림 삽입 완료");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(document != null) {
				try {
					document.close();
				} catch (IOException e) {}
			}
		}
	}
}
