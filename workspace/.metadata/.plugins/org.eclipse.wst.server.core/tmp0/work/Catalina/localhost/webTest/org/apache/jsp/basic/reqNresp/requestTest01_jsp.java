/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.85
 * Generated at: 2023-03-29 00:11:56 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.basic.reqNresp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class requestTest01_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<!-- \r\n");
      out.write("	HTML 주석\r\n");
      out.write(" -->\r\n");
      out.write(" \r\n");
      out.write(" ");
      out.write("\r\n");
      out.write("  \r\n");

	// 이 영역은 JSP문서에서 JAVA명령을 사용할 수 있는 영역으로 '스크립트릿'이라고 한다.
	String name = "홍길동";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write(" <!-- \r\n");
      out.write(" 	<form>태그의 속성\r\n");
      out.write(" 	1) action  ==> <form>태그 영역에서 구성한 데이터를 받아서 처리할 문서명 또는 서블릿 URL\r\n");
      out.write(" 	2) method  ==> 전송방식(GET,POST) ==> 기본값 : GET\r\n");
      out.write(" 	3) target  ==> 응답이 나타날 프레임명\r\n");
      out.write(" 	4) enctype ==> 인코딩 방식(서버로 파일을 전송할 때는 이 속성에 'multipart/form-data'로 지정해야 한다.)\r\n");
      out.write("  -->\r\n");
      out.write("<h2>");
      out.print(name );
      out.write("의 Request 연습 ");
      out.print(3 * 2 + 7 );
      out.write("</h2>\r\n");
      out.write("<form action=\"/webTest/requestTest01.do\" method=\"get\">\r\n");
      out.write("	<table border=\"1\">\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td>이 름</td>\r\n");
      out.write("			<td><input type=\"text\" size=\"10\" name=\"username\"></td>\r\n");
      out.write("		</tr>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td>직 업</td>\r\n");
      out.write("			<td>\r\n");
      out.write("				<select name=\"job\">\r\n");
      out.write("					<option value=\"무직\">무직</option>\r\n");
      out.write("					<option value=\"회사원\">회사원</option>\r\n");
      out.write("					<option value=\"전문직\">전문직</option>\r\n");
      out.write("					<option value=\"학생\">학생</option>\r\n");
      out.write("				</select>\r\n");
      out.write("			</td>\r\n");
      out.write("		</tr>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td>취 미</td>\r\n");
      out.write("			<td>\r\n");
      out.write("				<input type=\"checkbox\" name=\"hobby\" value=\"여행\">여행\r\n");
      out.write("				<input type=\"checkbox\" name=\"hobby\" value=\"탁구\">탁구\r\n");
      out.write("				<input type=\"checkbox\" name=\"hobby\" value=\"게임\">게임\r\n");
      out.write("				<input type=\"checkbox\" name=\"hobby\" value=\"유튜브보기\">유튜브보기\r\n");
      out.write("				<input type=\"checkbox\" name=\"hobby\" value=\"운동\">운동\r\n");
      out.write("				<input type=\"checkbox\" name=\"hobby\" value=\"독서\">독서\r\n");
      out.write("			</td>\r\n");
      out.write("		</tr>\r\n");
      out.write("		<tr>\r\n");
      out.write("			<td colspan=\"2\" style = \"text-align:center;\">\r\n");
      out.write("				<input type=\"submit\" value=\"전송\">\r\n");
      out.write("				<input type=\"reset\" value=\"취소\">\r\n");
      out.write("			</td>\r\n");
      out.write("		</tr>\r\n");
      out.write("	</table>\r\n");
      out.write("</form> \r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
