package itext;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;

public class Itext05 {
	public static void main(String[] args) throws FileNotFoundException {
		String path = "D:/itextPDF/itext05.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		
		Style author = new Style();
		author.setFontColor(ColorConstants.BLUE)
				.setFontSize(12f)
				.setItalic();
		
		Paragraph paragraph1 = new Paragraph();
		paragraph1.add("Hello how are you??").addStyle(author);
		
		document.add(paragraph1);
		
		document.close();
		
		System.out.println("PDF Created!!");
	}
}
