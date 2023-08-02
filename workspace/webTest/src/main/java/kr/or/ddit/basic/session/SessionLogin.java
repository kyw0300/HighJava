package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		
		String userId = request.getParameter("uid");
		String userPass = request.getParameter("upass");
		
		HttpSession session = request.getSession();
		
		if("admin".equals(userId) && "1234".equals(userPass)) {
			session.setAttribute("ID", userId);
			session.setAttribute("PASS", userPass);
		}
		// 페이지 바로 이동 forward / redirect 지금은 값을 가져가는게 없으므로 redirect가 좋음
		response.sendRedirect(request.getContextPath() + "/basic/session/sessionLogin.jsp");
	}
}
