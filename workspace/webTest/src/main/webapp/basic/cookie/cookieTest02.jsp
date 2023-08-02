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
		width: 200px;
	}
</style>
</head>
<body>
<div>
	<a href="<%=request.getContextPath()%>/cookieCountServlet.do">Cookie Count 증가 하기</a><br><br>
	<a href="<%=request.getContextPath()%>/cookieCountReset.do">Cookie Count 초기화 하기</a><br><br>
</div>
</body>
</html>