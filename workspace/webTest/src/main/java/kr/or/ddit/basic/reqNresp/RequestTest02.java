package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		int num1 = Integer.parseInt(request.getParameter("num1"));
		String cal = request.getParameter("cal");
		int num2 = Integer.parseInt(request.getParameter("num2"));
		
		double result = 0;		// 계산 결과가 저장될 변수 선언
		boolean calcOK = true;  // 계산 성공 여부를 나타내는 변수
		
		switch (cal) {
		case "+": result = num1 + num2; break;
		case "-": result = num1 - num2; break;
		case "*": result = num1 * num2; break;
		case "/": 
			if(num2==0) {
				calcOK = false;
			} else {
				result = (double) num1 / num2;
			}
			break;
		case "%": 
			if(num2==0) {
				calcOK = false;
			} else {
				result = num1 % num2;
			}
			break;
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta chatset='utf-8'><title>계산 처리 연습</title></head>");
		out.println("<body>");
		out.println("<h3> 계산 처리 결과 </h3>");
		
		if(calcOK == true) {
			out.println("<p>" + num1 + cal + num2 + " = " + result + "</p>");
		} else {
			out.println("<p> 0으로 나눌 수 없습니다. </p>");
		}
//		out.println("<p>" + num1 + " " + cal + " " + num2 + " = ");
//		if("+".equals(cal)) {
//			
//			out.println((num1+num2) + "</p>");
//			
//		} else if("-".equals(cal)) {
//			out.println((num1-num2) + "</p>");
//			
//		} else if("*".equals(cal)) {
//			out.println((num1*num2) + "</p>");
//			
//		} else if("/".equals(cal)){
//			out.println(((double)num1/num2) + "</p>");
//			
//		} else {
//			out.println((num1%num2) + "</p>");
//			
//		}
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
