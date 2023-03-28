package itext;


import java.io.FileNotFoundException;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class tableTest {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		
	// 테이블 생성하기 	
		
	String path = "c:/d_other/test.pdf"; 
	
	PdfWriter pdfWriter = new PdfWriter(path);
	PdfDocument pdfDocument = new PdfDocument(pdfWriter);
	Document document = new Document(pdfDocument);
		
	 float columnWidth[] = {200f, 50f, 100f}; // 열의 크기 지정 
	 Table table = new Table(columnWidth); // 주어진 크기에 맞는 테이블 생성 
	 
	 Cell cell_11 = new Cell();
	 cell_11.add(new Paragraph("Item")); // 1행 1열에 삽입
	 
	 table.addCell(cell_11);	
	 table.addCell(new Cell().add(new Paragraph("Qty"))); // 1행 2열에 삽입
	 table.addCell(new Cell().add(new Paragraph("Available")));// 1행 3열에 삽입
	 
	 
	 table.addCell(new Cell().add(new Paragraph("Orange"))); // 2행 1열에 삽입 
	 table.addCell(new Cell().add(new Paragraph("1"))); // 2행 2열에 삽입
	 table.addCell(new Cell().add(new Paragraph("5"))); // 2행 3열에 삽입
	 
	 
	 table.addCell(new Cell().add(new Paragraph("Apple"))); // 3행 1열에 삽입
	 table.addCell(new Cell().add(new Paragraph("1"))); // 3행 2열에 삽입
	 table.addCell(new Cell().add(new Paragraph("4"))); // 3행 3열에 삽입
	 
	 document.add(table); 
	 
	 document.close();
	 System.out.println("파일 만들기 완료");
	 		 		
	}
}
      