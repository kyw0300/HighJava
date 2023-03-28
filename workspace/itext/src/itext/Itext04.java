package itext;

import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import javafx.scene.control.ColorPickerBuilder;

public class Itext04 {
	public static void main(String[] args) throws IOException {
		String path = "D:/itextPDF/itext04.pdf";
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		
		PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
		
		Text text1 = new Text("you are nice!").setFont(font);
		Text text2 = new Text("\nyou are nice!").setFont(font).setBold();
		Text text3 = new Text("\nyou are nice!").setFont(font).setItalic();
		Text text4 = new Text("\nyou are nice!").setFont(font).setUnderline();
		Text text5 = new Text("\nyou are nice!").setFont(font).setBold().setItalic();
		Text text6 = new Text("\nyou are nice!").setFont(font).setFontColor(ColorConstants.BLUE);
		Text text7 = new Text("\nyou are nice!").setFont(font).setBold().setStrokeColor(ColorConstants.GREEN);
		Text text8 = new Text("\nText Rendering")
				.setTextRenderingMode(PdfCanvasConstants.TextRenderingMode.STROKE)
				.setStrokeColor(ColorConstants.RED)
				.setStrokeWidth(0.5f);
		
		Paragraph paragraph = new Paragraph()
				.add(text1)
				.add(text2)
				.add(text3)
				.add(text4)
				.add(text5)
				.add(text6)
				.add(text7)
				.add(text8);
		
		document.add(paragraph);
		document.close();
		
		System.out.println("PDF Created!!");
	}
}
