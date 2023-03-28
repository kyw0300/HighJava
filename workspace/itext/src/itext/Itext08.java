package itext;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;

public class Itext08 {
	public static void main(String[] args) throws FileNotFoundException {
		String path = "D:/itextPDF/itext08.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		
		String[] tableHeader = {"Item","Color","Size"};
		
		float columnWidth[] = {200f, 50f, 100f};
		
		Table table = new Table(columnWidth);
	}
}
