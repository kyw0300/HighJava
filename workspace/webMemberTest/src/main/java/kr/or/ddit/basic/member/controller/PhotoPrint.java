package kr.or.ddit.basic.member.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.basic.member.service.MymemberService;
import kr.or.ddit.basic.member.vo.MymemberVO;

/**
 * Servlet implementation class PhotoPrint
 */
@WebServlet("/images/photoPrint.do")
public class PhotoPrint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("detailId");
//		System.out.println("상세 회원 id" + id);
		
		MymemberService service = MymemberService.getInstance();
		MymemberVO vo = service.selectDetail(id);
		
		// 파일출력
		// 업로드된 파일들이 저장된 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";
						
		// 저장될 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		String imgPath = uploadPath + File.separator + vo.getMem_photo();
		File imgFile = new File(imgPath);
//		System.out.println(imgPath);
		if(imgFile.exists()) { // 이미지 파일이 있을 때
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			
			try {
				// 출력용 스트림 객체 생성
				bout = new BufferedOutputStream(response.getOutputStream());
				bin = new BufferedInputStream(new FileInputStream(imgFile));
				
				byte[] temp = new byte[1024];
				int len = 0;
				
				while((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
				
			} catch (Exception e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			} finally {
				if(bin!=null) try { bin.close(); } catch(IOException e) {}
				if(bout!=null) try { bout.close(); } catch(IOException e) {}
			}
		}
		
	}

}
