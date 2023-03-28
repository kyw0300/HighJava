package itext;

import java.awt.Color;
import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;

public class StyleTest {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		
		// 폰트에 스타일주기
		
		String path = "c:/d_other/fontstyle.pdf";
		
		PdfWriter pdfWriter = new PdfWriter(path);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument);
		
		Style style1 = new Style();  // 스타일 객체 생성 및 텍스트에 적용할 각 요소 입력
		style1.setBold();  
		style1.setFontSize(20f);
		style1.setItalic();
		
		Style style2 = new Style();  
		style2.setUnderline();
		style2.setFontSize(40f);
	    
	    Paragraph paragraph1 = new Paragraph();
	    paragraph1.add("Hello how are you ? ").addStyle(style1);  // css처럼 텍스트에 한번에 style주기  
	    
	    Paragraph paragraph2 = new Paragraph();
	    paragraph2.add("Hello how are you ? ").addStyle(style2);  
	    
	    document.add(paragraph1);
	    document.add(paragraph2);
	    
	    document.close();
	    System.out.println("파일 생성 완료");
					

	}

}
