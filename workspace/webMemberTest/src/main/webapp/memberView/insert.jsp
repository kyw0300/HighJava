<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int cnt = (Integer) request.getAttribute("cnt");
	if(cnt > 0) {
%>
	{
		"cnt": "회원가입이 정상적으로 완료되었습니다!"
	}

<%
	} else {
%>
	{
		"cnt": "회원가입에 실패하였습니다..."
	}

<%
	}
%>
