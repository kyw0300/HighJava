<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
	div{
		border: 1px solid black;
		padding: 30px;
		width: 300px;
	}
	label{
		width: 50px;
	}
</style>

<%
	// 로그인한 id를 쿠키에서 읽어온다.
	String cookieUserId = ""; // 로그인한 ID가 저장될 변수
	String chk =""; // CheckBox의 체크 여부를 지정할 변수
	
	Cookie[] cookieArr = request.getCookies();
	if(cookieArr != null){
		for(Cookie cookie : cookieArr) {
			if("USERID".equals(cookie.getName())){
				cookieUserId = cookie.getValue();
				chk = "checked";
			}
		}
	}
%>

<body>
<form action="<%= request.getContextPath()%>/cookieLoginServlet.do" method="post">
<div>

<table>
<tr>
	<td>ID: </td>
	<td><input type="text" name="uid" value="<%=cookieUserId %>" placeholder="ID 입력하세요."></td>
</tr>

<tr>
	<td>PASS: </td>	
	<td><input type="text" name="upass" placeholder="PassWord 입력하세요."></td>
</tr>
<tr>
	<td colspan="2"><input type="checkbox" name="chkid" value="check" <%=chk %>> id 기억하기</td>
</tr>
<tr>
	<td colspan="2" style="text-align:center;"><input type="submit" value="Login"></td>
</tr>
</table>

</div>
</form>
</body>
</html>