package itext;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;

public class Itext02 {
	public static void main(String[] args) throws FileNotFoundException {
		
		List list1 = new List();
		list1.add("Java");
		list1.add("Html");
		list1.add("Css");
		list1.add("Java Script");
		list1.add("Maven");
		list1.add("API");
		
		
		String path = "D:/itextPDF/itext02.pdf";
		
		PdfWriter pdfWriter = new PdfWriter(path);
		
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.addNewPage();
		
		Document document = new Document(pdfDocument);
		
		document.add(list1);
		
		document.close();
		
		System.out.println("Gooe Bye~!");
	}
}
