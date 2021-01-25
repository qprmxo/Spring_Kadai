<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring_Kadai_Main</title>
<script src="resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(function(){
		$("#btnSubmit").on('click', function(){
			if($("#id").val() == null || $("#id").val() == ""){
				alert("ユーザーIDを入力してください。");
				return false;
			}
			if ($("#id").val().match(/[^A-Za-z0-9]+/)) {
				alert("ユーザーIDは半角英数字で入力してください。");
				return false;
			}
			
			if($("#pass").val() == null || $("#pass").val() == ""){
				alert("パスワードを入力してください。");
				return false;
			}
			if ($("#pass").val().match(/[^A-Za-z0-9]+/)) {
				alert("パスワードは半角英数字で入力してください。");
				return false;
			}
			
			frm.submit();
		});
	});
</script>

</head>

<h1>ログイン画面</h1>

<body>

	<form action="login" method="post" id="frm">
	
		<table>
		
			<tr>
				<th>ID</th><td><input type="text" name="id" id="id"></td>
			</tr>
			
			<tr>
				<th>パスワード</th><td><input type="password" name="pass" id="pass"></td>
			</tr>
			
		</table>
		
		<button type="button" id="btnSubmit">ログイン</button>
		
	</form>
	
	<br>
	
	<a href="join">新規加入</a>
	
</body>
</html>
