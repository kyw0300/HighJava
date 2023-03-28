package itext;

import java.io.File;
import java.io.IOException;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;

public class QrcodeTest {

	public static void main(String[] args) throws IOException {


		// 저장할 파일 객체 생성
		File file = new File("D:/itextPDF/qrtest.pdf");

		// PDF 문서 생성
		PdfWriter writer = new PdfWriter(file);
		PdfDocument pdfDocument = new PdfDocument(writer);
		
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

		System.out.println("pdf created!");
	}
}
