package itext;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class itextEngTest {
	public static void main(String[] args) throws FileNotFoundException {
		
		
		 // 텍스트 입력 (영문)
	
	      String dest = "c:/d_other/test.pdf"; // pdf문서 저장 위치 및 이름 설정 
	      
	      PdfWriter writer = new PdfWriter(dest);  // 
	      
	      PdfDocument pdf = new PdfDocument(writer);  // 새 페이지 생성 (문서의 페이지 & 페이지의 컨텐츠 관리) 
	      
	      Document document = new Document(pdf); // 새 문서 객체 생성 (문서 구조 & 내용 작성)
	      
	      String para1 = "Hi how are you ? ";  //  파일에 입력될 내용
	      	    	      
	      Paragraph paragraph = new Paragraph(para1); // 내용 객체 생성 
	      document.add(paragraph);   // 문서에 내용 추가(입력) 
	      	      	      
	      document.close();   // 문서 닫기  
	      System.out.println("파일 만들기  완료"); 
	      
	}
}
