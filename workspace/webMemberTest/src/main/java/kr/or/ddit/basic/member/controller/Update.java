package kr.or.ddit.basic.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.basic.member.service.MymemberService;
import kr.or.ddit.basic.member.vo.MymemberVO;

@WebServlet("/update.do")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String memid = request.getParameter("memId");
		
		MymemberService service = MymemberService.getInstance();
		MymemberVO vo = service.selectDetail(memid);
		
		request.setAttribute("updateInfo", vo);
		request.getRequestDispatcher("/memberView/updateMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
