package itext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

public class Itext01 {
	public static void main(String[] args) throws IOException {
		
		// ** 문서 생성 및 페이지 생성 **
		// pdf문서 저장 위치 및 이름 설정 
		String path = "D:/itextPDF/itext01.pdf";
		
		// PdfWriter 클래스를 사용하여 출력 스트림을 지정
		PdfWriter pdfWriter = new PdfWriter(path);
		
		// PdfWriter 객체를 전달하여 PDF 문서를 생성 및 새 페이지 생성
		// (문서의 페이지 & 페이지의 컨텐츠 관리)
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.addNewPage();
		
		// PdfDocument 인스턴스를 전달받아 새 문서 객체 생성 (문서 구조 & 내용 작성)
		// 문서를 열어준다.
		Document document = new Document(pdfDocument);
		
		// ** 폰트 생성 및 스타일 생성 **
		// 폰트 객체 생성
		PdfFont font1 = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
		PdfFont font2 = PdfFontFactory.createFont("D:\\nanum-gothic\\NanumGothic.ttf");
		
		// 스타일 객체 생성 및 텍스트에 적용할 각 요소 입력
		Style style1 = new Style()
				.setFont(font2)
				.setUnderline()
				.setFontSize(40f);
		
		// ** 메세지 생성 및 폰트,스타일 적용 **
		// 메세지 객체 생성
		Paragraph paragraph1 = new Paragraph("Hello! my name is HONG GIL DONG!");
		
		// 폰트 적용 및 폰트 사이즈 설정
		paragraph1.setFont(font1)
				  .setBold()
				  .setItalic()
				  .setFontSize(20f);
		
		// 메세지 객체 생성
		Paragraph paragraph2 = new Paragraph("안녕하세요! 제 이름은 홍길동입니다!");
		
		// css처럼 텍스트에 한번에 style주기
		paragraph2.addStyle(style1);	
		
		// 문서에 내용 추가(입력)
		document.add(paragraph1);
		document.add(paragraph2); 
			
		// ** 이미지 생성 및 삽입 **	
		// 삽입 할 이미지 파일의 경로
		String imgSrc = "D:/itextPDF/사자2.jpg";
			
		// 이미지 파일의 경로를 사용하여 이미지 데이터를 로드하고 iText에서 사용 가능한 ImageData 개체를 생성
		ImageData data = ImageDataFactory.create(imgSrc);
			
		// 이미지 크기 조정
		data.setWidth(300f);
		data.setHeight(300f);
			
		// ImageData 객체를 인수로 받아 Image 객체 생성
		Image image1 = new Image(data);
			
		// Document 클래스의 add() 메서드를 사용하여 이미지를 문서에 추가
		document.add(image1);
		
		document.add(new Paragraph("\n")); // 줄바꿈
		document.add(new Paragraph("\n"));
		
		// ** 테이블 생성 및 삽입 **
		float columnWidth[] = {200f, 50f, 100f}; // 열의 크기 지정 
		Table table = new Table(columnWidth); // 주어진 크기에 맞는 테이블 생성 
		
		// 테이블에 넣을 셀 객체 생성
		Cell cell_11 = new Cell();
		cell_11.add(new Paragraph("Item")); 
		 
		table.addCell(cell_11);										// 1행 1열에 삽입
		table.addCell(new Cell().add(new Paragraph("Qty"))); 		// 1행 2열에 삽입
		table.addCell(new Cell().add(new Paragraph("Available")));  // 1행 3열에 삽입
		 
		 
		table.addCell(new Cell().add(new Paragraph("Orange"))); 	// 2행 1열에 삽입 
		table.addCell(new Cell().add(new Paragraph("1"))); 			// 2행 2열에 삽입
		table.addCell(new Cell().add(new Paragraph("5"))); 			// 2행 3열에 삽입
		 
		 
		table.addCell(new Cell().add(new Paragraph("Apple"))); 		// 3행 1열에 삽입
		table.addCell(new Cell().add(new Paragraph("1"))); 			// 3행 2열에 삽입
		table.addCell(new Cell().add(new Paragraph("4"))); 			// 3행 3열에 삽입
		
		// 문서에 내용 추가(입력)
		document.add(table); 
		
		// QRCode를 만들 주소
		String url = "https://ddit.or.kr/";

		// 새 페이지 추가
		PdfPage pdfPage = pdfDocument.addNewPage();

		// PdfCanvas 객체 생성 (페이지에 요소를 삽입하기 위한 객체)
		PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);

		// 크기 지정을 위한 사각형 객체 생성
		Rectangle rect = new Rectangle(0, 200, 600, 600);

		// QRCode 객체 생성 및 삽입 가능한 형태로 반환
		BarcodeQRCode barcodeQRCode = new BarcodeQRCode(url);
		PdfFormXObject pdfFormXObject = barcodeQRCode.createFormXObject(ColorConstants.BLACK, pdfDocument);
		Image qrCodeImage = new Image(pdfFormXObject).setWidth(rect.getWidth()).setHeight(rect.getHeight());
		Canvas qrCanvas = new Canvas(pdfCanvas, rect);

		// QRCode 삽입
		qrCanvas.add(qrCodeImage);

		// pdfbox와 달리 close와 동시에 파일 작성 완료
		qrCanvas.close();
		pdfDocument.close();
		
		// 문서를 닫는다.
		document.close();
			
		System.out.println("Pdf 파일 생성 완료!");
	}
}
