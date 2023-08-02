package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int count = 0; // 현재 count값이 저장될 변수
		
		// 쿠키이름을 count로 한다. ==> 현재 이 쿠키가 있는지 검사한다.
		Cookie[] cookieArr = request.getCookies();
		
		
		if(cookieArr != null) {
			for(Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())){ // count라는 쿠키가 있는지 검사
					String value = cookie.getValue(); // 현재의 쿠키값 가져오기
					count = Integer.parseInt(value);
				}
			}
		}
		
		count++; // count 증가
		
		Cookie visitCookie = new Cookie("count", count+"");
		
		response.addCookie(visitCookie);
		
		out.println("<html><head><meta charset='utf-8'><title>쿠키 count 연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>어서오세요. 당신은 " + count + "번째 방문입니다.</h3><br><br>");
		
		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'>"
				+ "카운트 증가하기</a>");
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest02.jsp'>"
				+ "시작문서로 이동하기</a>");
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
