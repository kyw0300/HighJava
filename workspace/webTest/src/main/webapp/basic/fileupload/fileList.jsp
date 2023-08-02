<%@page import="kr.or.ddit.vo.FileinfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// 컨트롤러(서블릿)에서 보내온 데이터 받기
	List<FileinfoVO> fileList = (List<FileinfoVO>) request.getAttribute("filelist");
%>

<h3>전체 파일 목록</h3>
<br><hr><br>
<a href="<%=request.getContextPath()%>/fileUpload.do">파일 업로드</a>
<table border="1">
<thead>
	<tr><th>번호</th><th>작성자</th><th>저장 파일명</th><th>원래의 파일명</th>
	<th>파일 크기</th><th>날 짜</th><th>비 고</th></tr>
</thead>
<tbody>
<%
	if(fileList == null || fileList.size() == 0) {
%>
	<tr><td colspan="7" style="text-align: center";>저장된 파일 목록이 하나도 없습니다.</td></tr>

<%
	} else {
		for(FileinfoVO vo : fileList) {
%>
	<tr>
		<td><%=vo.getFile_no()%></td>
		<td><%=vo.getFile_writer()%></td>
		<td><%=vo.getSave_file_name()%></td>
		<td><%=vo.getOrigin_file_name()%></td>
		<td><%=vo.getFile_size()%></td>
		<td><%=vo.getFile_date()%></td>
		<td><a href="<%=request.getContextPath()%>/fileDownload.do?fileno=<%=vo.getFile_no()%>">DownLoad</a></td>
	</tr>
<%			
		}
	}
%>
</tbody>
</table>
</body>
</html>