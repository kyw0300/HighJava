<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">

$(function(){
	$('#lprodBtn').on('click',function(){
		$.ajax({
			url: '<%= request.getContextPath()%>/lprodController.do',
			type: 'get',
			success: function(res){
				alert("성공");
				
				console.log("result",res);
				
				let htmlCode = "<table border='1'><tr><td>LPROD_ID</td><td>LPROD_GU</td><td>LPROD_NM</td></tr>"
				$.each(res, function(i,v){
					htmlCode += "<tr><td>"+ v.lprod_id +"</td><td>" + v.lprod_gu +"</td><td>" + v.lprod_nm +"</td></tr>";
				});
				htmlCode += "</table>";
				$('#result').html(htmlCode);
			
			},
			error: function(xhr){
				alert("상태: " + xhr.status + "\ncode : " + xhr.statusText);
			},
			dataType: 'json'
		})
	})
	
	$('#lprodBtn2').on('click',function(){
		location.href = "<%=request.getContextPath()%>/lprodList2.do";
	})
})
</script>
<body>

<!-- 
	아래의 'Lprod'자료 가져오기' 버튼을 클릭하면 DB의 LPROD테이블의 전체 데이터를 가져와 
	id가 'result'인 <div>태그에 표로 출력하시오.
	(Ajax이용, MVC패턴 사용, 서블릿의 URL패턴 : /lprodList.do)
 -->

<form action="">
	<input type="button" id="lprodBtn" value="Lprod자료 가져오기(Ajax)">
	<input type="button" id="lprodBtn2" value="Lprod자료 가져오기(Non Ajax)">
</form>
<h3>Lprod 자료 목록</h3>
<div id="result"></div>
</body>
</html>