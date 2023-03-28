package itext;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

public class Form {
	public static void main(String[] args) throws IOException {
		String path = "D:/itextPDF/form.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//		Document document = new Document(pdfDocument);
		
		
		
		pdfDocument.close();
//		document.close();
		
		System.out.println("PDF Created!!");
	}
}
