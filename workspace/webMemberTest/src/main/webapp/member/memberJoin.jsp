<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
$(function(){

	$('#idCheck').on('click',function(){
		// 입력한 값을 가져온다.
		idvalue = $('#uid').val().trim();
		
		if(idvalue.length < 1 || idvalue == null) {
			alert("id를 입력해주세요.");
			return false;
		}
		
		$.ajax({
			url: '<%=request.getContextPath()%>/idCheck.do',
			type: 'get',
			data: {"id": idvalue},
			success: function(res){
				alert(res.flag);
// 				$('#idspan').text(res.flag).css({'color': 'blue', 'font-size': '0.8em'});
// 				chk = 1;
			},
			error: function(xhr){
				alert("상태: " + xhr.status + "\ncode : " + xhr.statusText);
			},
			dataType: 'json'
		})
	})
	
// 	$('#register').on('click',function(){
// 		pass1 = $('#pwd').val().trim();
// 		pass2 = $('#pass2').val().trim();
		
// 		if(pass1 != pass2) {
// 			alert("비밀번호가 일치하지 않습니다! 다시 입력해주세요!")
// 			return false;
// 		}
		
// // 		fdata3 = $('form').serializeJSON();

// 		idval = $('#uid').val().trim();
// // 		passval = $('#pwd').val().trim();
// 		nameval = $('#name').val().trim();
// 		telval = $('#tel').val().trim();
// 		addrval = $('#addr').val().trim();
// 		photoval = $('#photo').val().trim();
		
// 		fdata = {"id": idval,
// 				 "pass": pass1,
// 				 "name": nameval,
// 				 "tel": telval,
// 				 "addr": addrval, 
// 				 "photo": photoval};
		
// 		$.ajax({
<%-- 			url: '<%= request.getContextPath()%>/insert.do', --%>
// 			data: fdata,
// 			type: 'post',
// 			success: function(res){
// // 				alert("가입성공");
// 				alert(res.cnt);
				
// 			},
// 			error: function(xhr){
// 				alert("상태: " + xhr.status + "\ncode : " + xhr.statusText);
// 			},
// 			dataType: 'json'
// 		})
// 	})
})
</script>
<style type="text/css">
table{
	border: 1px solid blue;
	border-collapse: collapse;
}
</style>
</head>
<body>
<h3>회원 정보 입력 폼</h3>
<br>
<form action="<%= request.getContextPath()%>/insert.do" method="post" enctype="multipart/form-data">
<table border="1">
<tr>
	<td>회원ID</td>
	<td><input type="text" name="id" id="uid"><button type="button" id="idCheck">중복확인</button></td>
</tr>
<tr>
	<td>비밀번호</td>
	<td><input type="password" id="pwd" name="pwd"></td>
</tr>
<tr>
	<td>비밀번호확인</td>
	<td><input type="password" id="pass2" name="pass2"></td>
</tr>
<tr>
	<td>회원이름</td>
	<td><input type="text" name="name" id="name"></td>
</tr>
<tr>
	<td>전화번호</td>
	<td><input type="text" name="tel" id="tel"></td>
</tr>
<tr>
	<td>회원주소</td>
	<td><input type="text" name="addr" id="addr"></td>
</tr>
<tr>
	<td>프로필사진</td>
	<td><input type="file" name="photo" value="파일선택" id="photo"></td>
<!-- 	<td><input type="text" name="photo" value="파일선택" id="photo"></td> -->
</tr>
<tr>
	<td colspan="2" style="text-align: center">
		<input type="submit" value="저장" id="register">
		<input type="reset" value="취소">
		<a href="<%=request.getContextPath()%>/mymemberList.do"><input type="button" value="회원목록"></a>
	</td>
</tr>
</table>
</form>
</body>
</html>