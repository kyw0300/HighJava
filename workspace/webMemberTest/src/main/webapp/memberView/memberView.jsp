<%@page import="kr.or.ddit.basic.member.vo.MymemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table{
	border: 1px solid blue;
}
</style>
</head>
<body>
<%
	List<MymemberVO> list = (List<MymemberVO>) request.getAttribute("mymemberlist");
%>

<h3>회원 목록 보기</h3>
<br>
<form action="<%=request.getContextPath()%>/member/memberJoin.jsp">
<table border="1">
<thead>
	<tr><td colspan="5" style="text-align: right"><button type="submit" id="join">회원추가</button></td></tr>
	<tr><th>ID</th><th>비밀번호</th><th>이름</th><th>전화</th><th>주소</th></tr>
</thead>
<tbody>
<%
	if(list == null || list.size() == 0) {
%>
	<tr><td colspan="5" style="text-align: center";>저장된 회원 목록이 하나도 없습니다.</td></tr>

<%
	} else {
		for(MymemberVO vo : list) {
%>
		<tr>
			<td><a href="<%=request.getContextPath()%>/detailMember.do?detailId=<%=vo.getMem_id()%>"><%=vo.getMem_id()%></a></td>
			<td><%=vo.getMem_pass()%></td>
			<td><%=vo.getMem_name()%></td>
			<td><%=vo.getMem_tel()%></td>
			<td><%=vo.getMem_addr()%></td>
		</tr>
<%
		}
	}
%>
</tbody>
</table>
</form>
</body>
</html>