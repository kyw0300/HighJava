package kr.or.ddit.basic.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

@WebServlet("/sessionLoginDB.do")
public class SessionLoginDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("uid");
		String userPass = request.getParameter("upass");
		
		// 입력받은 데이터를 VO에 저장한다.
		MemberVO memVO = new MemberVO();
		memVO.setMem_id(userId);
		memVO.setMem_pass(userPass);
		
		// DAO객체 생성
		MemberDao dao = MemberDao.getInstance();
		
		// DB에서 id와 password가 일치하는 데이터 검색해서 가져오기
		MemberVO loginMemberVO = dao.getMember(memVO);
		
		HttpSession session = request.getSession();
		
		if(loginMemberVO != null) { // 로그인 성공!!
			session.setAttribute("loginMember", loginMemberVO);
		}
		response.sendRedirect(request.getContextPath()+"/basic/session/sessionLoginDB.jsp");
	}

}
