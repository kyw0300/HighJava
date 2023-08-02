<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div{
		border: 1px solid black;
		padding: 30px;
		width: 400px;
	}
</style>
</head>
<body>
<div>
	<h2>cookie연습용 main페이지 입니다.</h2>
	<a href="<%=request.getContextPath()%>/basic/cookie/cookieLogin.jsp">Login 창으로 이동</a><br><br>
</div>
</body>
</html>