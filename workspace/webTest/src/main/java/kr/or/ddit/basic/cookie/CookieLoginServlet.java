package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 클라이언트가 보낸 데이터 받기
		String userId = request.getParameter("uid");
		String userPass = request.getParameter("upass");
		String chkid = request.getParameter("chkid");
		System.out.println(chkid);
		
		// 쿠키 객체 생성(쿠키이름: 'USERID', 쿠키값: 로그인한 ID)
		Cookie loginCookie = new Cookie("USERID", userId);
		
		// checkbox가 체크된 상태이면 쿠키 저장, 해제된 상태이면 쿠키 삭제
		if(chkid == null) { // checkbox 해제 여부 검사
			loginCookie.setMaxAge(0);
		}
		
		response.addCookie(loginCookie);
		
		//----------------------------------------------------------------------
		// 로그인 성공 여부(id: test, password: 1234)
		if("test".equals(userId) && "1234".equals(userPass)) {
			// 페이지 바로 이동 forward / redirect 지금은 값을 가져가는게 없으므로 redirect가 좋음
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieMain.jsp");
		} else { // 로그인 실패
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieLogin.jsp");
		}
	}
}
