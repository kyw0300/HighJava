package kr.or.ddit.basic.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/lprodController.do")
public class LprodController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("application/json; charset=utf-8");
		
		LprodServiceImpl service = LprodServiceImpl.getInstance();
		List<LprodVO> lprodList = service.selectAll();
		
//		// Gson 사용
//		Gson gson = new Gson();
//		String jsonData = gson.toJson(lprodList);
//		
//		response.getWriter().write(jsonData);
//		response.flushBuffer();
		
		request.setAttribute("lprodlist", lprodList);
		request.getRequestDispatcher("/basic/json/lprodView.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
