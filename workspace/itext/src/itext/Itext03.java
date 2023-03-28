package itext;

import java.io.IOException;

import javax.swing.SpringLayout.Constraints;
import javax.swing.text.StyleConstants.FontConstants;

import com.itextpdf.io.font.CFFFont;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.Pfm2afm;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

public class Itext03 {
	public static void main(String[] args) throws IOException {
		String path = "D:/itextPDF/itext03.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		
		
		PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
		PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
		
		Text text1 = new Text("you are nice!").setFont(font);
		Text text2 = new Text("  Hello baby~").setFont(boldFont);
		
		Paragraph paragraph = new Paragraph()
				.add(text1)
				.add(text2);
		
		document.add(paragraph);
		document.close();
		
		System.out.println("Create!!");
		
		
	}
}
