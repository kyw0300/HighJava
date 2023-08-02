<%@page import="kr.or.ddit.basic.member.vo.MymemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img{
	width: 300px;
	height: 200px;
	margin: 30px;
}
</style>
</head>
<body>
<%
	MymemberVO vo = (MymemberVO) request.getAttribute("updateInfo");
%>

<h3>회원 정보 수정폼</h3>
<form action="<%=request.getContextPath()%>/goUpdate.do" method="post" enctype="multipart/form-data">
<table border="1">
	<tr>
		<td colspan="2">
			<img alt="" src="<%=request.getContextPath()%>/images/photoPrint.do?detailId=<%=vo.getMem_id()%>">
		</td>
	</tr>
	<tr>
		<td>회원ID</td>
		<td><%= vo.getMem_id()%><input type="hidden" name="id" value="<%= vo.getMem_id()%>"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pass" value="<%=vo.getMem_pass()%>"></td>
	</tr>
	<tr>
		<td>회원이름</td>
		<td><input type="text" name="name" value="<%=vo.getMem_name()%>"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" name="tel" value="<%=vo.getMem_tel()%>"></td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td><input type="text" name="addr" value="<%=vo.getMem_addr()%>"></td>
	</tr>
	<tr>
		<td>프로필 사진</td>
		<td><input type="file" name="photo"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="저장">
			<a href="<%=request.getContextPath()%>/detailMember.do?detailId=<%=vo.getMem_id()%>"><input type="button" value="취소"></a>
			<a href="<%=request.getContextPath()%>/mymemberList.do"><input type="button" value="회원목록"></a>
		</td>
	</tr>
	
</table>
</form>

</body>
</html>