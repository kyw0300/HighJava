<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String idvalue = (String) request.getAttribute("resId");
	if(idvalue != null) {
%>
		{
			"flag" : "이미 존재하는 id입니다."
		}
<%	} else { %>
		{
			"flag" : "사용 가능한 id입니다."
			
		}
<%		
	}
%>