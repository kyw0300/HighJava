<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>IMG태그의 src속성에 Servlet으로 이미지 처리하기</h3>

<img src="<%=request.getContextPath()%>/images/lion.jpg" width="150"><br><br>

<img src="../../images/lion.jpg" width="150"><br><br>

<img src="<%=request.getContextPath()%>/images/imageSrcView.do?fileno=20" width="150"><br><br>

</body>
</html>
