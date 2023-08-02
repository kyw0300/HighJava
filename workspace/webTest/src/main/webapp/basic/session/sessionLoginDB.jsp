<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div{
	border: 1px solid green;
	width: 250px;
	padding: 50px;
}
table {
	border: 1px solid black;
}
</style>
</head>

<%
	// Session 정보를 가져온다. ==> 
	MemberVO loginMemVO = (MemberVO) session.getAttribute("loginMember");
	if(loginMemVO == null) {
%>

<body>

<form action="<%= request.getContextPath()%>/sessionLoginDB.do" method="post">
<div>

<table border='1'>
<tr>
	<td>ID: </td>
	<td><input type="text" name="uid" placeholder="ID 입력하세요."></td>
</tr>

<tr>
	<td>PASS: </td>	
	<td><input type="text" name="upass" placeholder="PassWord 입력하세요."></td>
</tr>
<tr>
	<td colspan="2" style="text-align:center;"><input type="submit" value="Login"></td>
</tr>
</table>

</div>
</form>

</body>
<%
	} else {
%>

<body>


<div>
	<h2><%=loginMemVO.getMem_name()%>님 반갑습니다.</h2>
	<a href="<%= request.getContextPath()%>/sessionLogoutDB.do">로그아웃</a>

</div>

</body>
<%
	}
%>
</html>