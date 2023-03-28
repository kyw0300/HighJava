package itext;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class Itext07 {
	public static void main(String[] args) throws FileNotFoundException {
		String path = "D:/itextPDF/itext07.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		
		float columnWidth[] = {200f, 50f, 100f};
		
		Table table = new Table(columnWidth);
		
		Cell cell_11 = new Cell();
		cell_11.add(new Paragraph("Hello how are you?"));
		
		table.addCell(cell_11);
		table.addCell(new Cell().add(new Paragraph("Qty")));
		table.addCell(new Cell().add(new Paragraph("Available")));
		
		table.addCell(new Cell().add(new Paragraph("Mango")));
		table.addCell(new Cell().add(new Paragraph("2 kg")));
		table.addCell(new Cell().add(new Paragraph("Yes")));
		
		table.addCell(new Cell().add(new Paragraph("Orange")));
		table.addCell(new Cell().add(new Paragraph("5 kg")));
		table.addCell(new Cell().add(new Paragraph("No")));
		table.addCell(new Cell().add(new Paragraph("Give me Banana!")));
		
		document.add(table);
		document.close();
		
		System.out.println("PDF Created!!");
	}
}
