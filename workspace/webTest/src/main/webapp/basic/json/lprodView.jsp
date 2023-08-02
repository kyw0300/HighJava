<%@page import="kr.or.ddit.basic.json.LprodVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<LprodVO> list = (List<LprodVO>) request.getAttribute("lprodlist");
%>
<table border="1">
<tr><td>LPROD_ID</td><td>LPROD_GU</td><td>LPROD_NM</td></tr>

	<%
		for(LprodVO vo : list) {
	%>
		<tr><td><%= vo.getLprod_id() %></td>
			<td><%= vo.getLprod_gu() %></td>
			<td><%= vo.getLprod_nm() %></td></tr>
	<%
		}
	%>
</table>

[
	<%
		for(int i=0; i<list.size(); i++) {
			LprodVO vo = list.get(i);
			if(i != 0) out.print(", "); // 자바 안쪽 출력은 out.print 바깥쪽
	%>
		{
			"id" : "<%= vo.getLprod_id() %>",
			"gu" : "<%= vo.getLprod_gu() %>",
			"nm" : "<%= vo.getLprod_nm() %>"
		}
		
	<%
		}
	%>
]