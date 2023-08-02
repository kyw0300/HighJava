package kr.or.ddit.basic.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.basic.member.service.MymemberService;
import kr.or.ddit.basic.member.vo.MymemberVO;

@WebServlet("/goUpdate.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 100)
public class GoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// -----------------------------------------------------------------------------------------------
				// 업로드된 파일이 저장될 폴더 설정
				String uploadPath = "d:/d_other/uploadFiles";

				// 저장될 폴더가 없으면 새로 만든다.
				File f = new File(uploadPath);
				if (!f.exists()) {
					f.mkdirs();
				}

				// 수신 받은 파일 데이터 처리하기
				String fileName = ""; // 파일명이 저장될 변수 선언
				String saveFileName = null;
				// 전체 Part객체 개수만큼 반복 처리하기
				for (Part part : request.getParts()) {
					fileName = extractFileName(part);

					// 찾은 파일명이 빈문자열("")이면 이것은 파일이 아닌 일반 파라미터 데이터라는 의미이다.
					if (!"".equals(fileName)) { // 파일인지 검사
						System.out.println("filename: " + fileName);
						// 1개의 Upload파일에 대한 정보를 저장하기 위한 VO객체 생성
						MymemberVO fvo = new MymemberVO();

						fvo.setMem_photo(fileName); // 실제 파일명을 VO에 저장

						// 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서 UUID객체를 이용하여
						// 저장할 파일명을 만든다.
						saveFileName = UUID.randomUUID().toString() + "_" + fileName;

						// part.getSize() ==> Upload된 파일의 크기를 반환한다. (단위 : byte)

						// 파일 크기를 구해서 KB단위로 변환해서 VO에 저장한다.
						fvo.setPhoto_size(((long) Math.ceil(part.getSize() / 1024.0)));
//						( (long) Math.ceil(part.getSize() / 1024.0) );

						// 수신 받은 파일 내용을 저장한다.
						try {
							// part.write()메서드 ==> Upload된 파일을 저장하는 메서드
							part.write(uploadPath + File.separator + saveFileName); // 파일 저장하기...
						} catch (Exception e) {
							e.printStackTrace();
						}

//						fileList.add(fvo); // upload된 파일 정보를 List에 추가

					} // if문 끝...

				} // for문 끝...

				// -----------------------------------------------------------------------------------------------
		
		String id = request.getParameter("id");
		String upPass = request.getParameter("pass");
		String upName = request.getParameter("name");
		String upTel = request.getParameter("tel");
		String upAddr = request.getParameter("addr");
//		String photo = request.getParameter(saveFileName);
		
		MymemberVO vo = new MymemberVO();
		vo.setMem_id(id);
		vo.setMem_pass(upPass);
		vo.setMem_name(upName);
		vo.setMem_tel(upTel);
		vo.setMem_addr(upAddr);
		vo.setMem_photo(saveFileName);
		
		MymemberService service = MymemberService.getInstance();
		int cnt = service.updateInfo(vo);
		
		response.sendRedirect(request.getContextPath()+"/mymemberList.do");
	}
	
	// Part구조 안에서 파일명을 찾는 메서드
		private String extractFileName(Part part) {

			String fileName = ""; // 반환할 파일명이 저장될 변수
			String headerValue = part.getHeader("content-disposition"); // 헤더의 '키값'을 이용하여 값을 구한다.

			String[] items = headerValue.split(";");
			for (String item : items) {
				if (item.trim().startsWith("filename")) {
					fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
				}
			}

			return fileName;
		}
	
}
