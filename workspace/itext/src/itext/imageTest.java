package itext;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

public class imageTest {

	public static void main(String[] args) throws MalformedURLException, FileNotFoundException {
	
		
	// 이미지 파일 만들기 
		
	String imgSrc = "D:/itextPDF/사자2.jpg"; // pdf로 출력할 이미지 파일     
	ImageData data = ImageDataFactory.create(imgSrc); // 이미지 파일 로드 및 pdf문서에 사용가능한 형식으로 변환
	Image image1 = new Image(data);  // 이미지 객체 생성 
	
	String path = "D:/itextPDF/newFile.pdf"; // 새로 만들 pdf 파일 위치 & 제목 설정
	
	Paragraph paragraph = new Paragraph("Hi how are you ?"); // 메세지 객체 생성 
	PdfWriter pdfWriter = new PdfWriter(path); 
	
	PdfDocument pdfDocument = new PdfDocument(pdfWriter); // 새 페이지 생성 (문서의 페이지 & 페이지의 컨텐츠 관리)
	Document document = new Document(pdfDocument); // 새 문서 객체 생성 (문서 구조 & 내용 작성)

	document.add(paragraph); // 메세지 추가(입력)
	document.add(image1); // 이미지 추가(입력)
	
	document.close(); // 문서 작성 종료
	
	System.out.println("파일 만들기 완료");
				
	}

}
