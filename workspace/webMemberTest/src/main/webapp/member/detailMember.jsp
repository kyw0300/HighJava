<%@page import="kr.or.ddit.basic.member.vo.MymemberVO"%>
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
img{
	width: 300px;
	height: 200px;
	margin: 30px;
}
</style>
</head>
<body>
<%
	MymemberVO vo = (MymemberVO) request.getAttribute("detail");
%>

<h3>회원 정보 상세보기</h3>
<table border="1">
	<tr>
		<td colspan="2">
			<img alt="" src="<%=request.getContextPath()%>/images/photoPrint.do?detailId=<%=vo.getMem_id()%>">
		</td>
	</tr>
	<tr>
		<td>회원ID</td>
		<td><%=vo.getMem_id()%></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><%=vo.getMem_pass()%></td>
	</tr>
	<tr>
		<td>회원이름</td>
		<td><%=vo.getMem_name()%></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><%=vo.getMem_tel()%></td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td><%=vo.getMem_addr()%></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<%=request.getContextPath()%>/update.do?memId=<%=vo.getMem_id()%>"><input type="button" value="수정"></a>
			<a href="<%=request.getContextPath()%>/delete.do?memId=<%=vo.getMem_id()%>"><input type="button" value="삭제"></a>
			<a href="<%=request.getContextPath()%>/mymemberList.do"><input type="button" value="회원목록"></a>
		</td>
	</tr>
	
</table>
</body>
</html>