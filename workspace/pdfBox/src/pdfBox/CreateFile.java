package pdfBox;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

public class CreateFile {
	public static void main(String[] args) {
		PDDocument document = new PDDocument(); // PDF문서 객체 생성
		
		try {
			document.save("C:\\Users\\PC-14\\Documents\\PDFBox\\prac01.pdf");
			System.out.println("PDF 생성완료");
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
