<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring_Kadai_Join</title>
<script src="resources/js/jquery-3.3.1.js"></script>
<style type="text/css">
table td {
	text-align: center;
}
</style>
<script type="text/javascript">
	$(function() {
		
		var today = new Date();
		$('#birth').val(today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2));

		$("#idCheck").on('click', function() {	
			if ($("#id").val() == null || $("#id").val() == "") {
				alert("ユーザーIDを入力してください。");
				return false;
			}
			if ($("#id").val().match(/[^A-Za-z0-9]+/)) {
				alert("ユーザーIDは半角英数字で入力してください。");
				return false;
			}
			$.ajax({
				type : "post",
				url : "idCheck",
				data : "id=" + $("#id").val(),
				success : function(res){
					alert(res);
				},
				error : function(e){
					alert("Error : " + e);
				}
			});
		});
		
		$("#btnRt").on('click',function(){
			history.back();
		});

		$("#btnSubmit").on('click', function() {
			if ($("#id").val() == null || $("#id").val() == "") {
				alert("ユーザーIDを入力してください。");
				return false;
			}
			if ($("#id").val().match(/[^A-Za-z0-9]+/)) {
				alert("ユーザーIDは半角英数字で入力してください。");
				return false;
			}
			
			if ($("#pass").val() == null || $("#pass").val() == "") {
				alert("パスワードを入力してください。");
				return false;
			}
			if ($("#pass").val().match(/[^A-Za-z0-9]+/)) {
				alert("パスワードは半角英数字で入力してください。");
				return false;
			}			
			
			if ($("#passCheck").val() == null || $("#passCheck").val() == "") {
				alert("パスワード再入力を入力してください。");
				return false;
			}
			if ($("#passCheck").val().match(/[^A-Za-z0-9]+/)) {
				alert("パスワード再入力は半角英数字で入力してください。");
				return false;
			}	
			
			if ($("#pass").val() != $("#passCheck").val()) {
				alert("パスワードとパスワード再入力は同じものを入力してください。");
				return false;
			}
			
			if ($("#name").val() == null || $("#name").val() == "") {
				alert("名前を入力してください。");
				return false;
			}
			if (!$("#name").val().match(/[^A-Za-z0-9]+/) && $("#name").val() != "") {
				alert("名前は全角で入力してください。");
				return false;
			}
			
			if ($("#kana").val() == null || $("#kana").val() == "") {
				alert("カナを入力してください。");
				return false;
			}
			if ($("#kana").val().match(/[^A-Za-z0-9]+/)) {
				alert("カナは半角で入力してください。");
				return false;
			}
			
			if ($("#birth").val() == null || $("#birth").val() == "") {
				alert("生年月日を選択してください。");
				return false;
			}
			
			if ($("#club").val() == null || $("#club").val() == "") {
				alert("委員会を入力してください。");
				return false;
			}
			if (!$("#club").val().match(/[^A-Za-z0-9]+/) && $("#club").val() != "") {
				alert("委員会は全角で入力してください。");
				return false;
			}
			
			$("#frm").submit();

		});
	});
</script>
</head>
<body>

	<h1>新規登録入力画面</h1>

	<form action="" id="frm" method="post">

		<table>

			<tr>
				<th>ID :</th>
				<td><input type="text" name="id" id="id" value="${requestScope.id }"></td>
			</tr>

			<tr>
				<th colspan="2"><button type="button" id="idCheck">使用できるか確認</button></th>
			</tr>

			<tr>
				<th>パスワード :</th>
				<td><input type="password" name="pass" id="pass"></td>
			</tr>

			<tr>
				<th>パスワード再入力 :</th>
				<td><input type="password" name="passCheck" id="passCheck"></td>
			</tr>

			<tr>
				<th>名前 :</th>
				<td><input type="text" name="name" id="name" value="${name }"></td>
			</tr>

			<tr>
				<th>カナ :</th>
				<td><input type="text" name="kana" id="kana" value="${kana }"></td>
			</tr>

			<tr>
				<th>生年月日（yyyy/mm/dd) :</th>
				<td><input type="date" name="birth" id="birth" value="${birth }"></td>
			</tr>

			<tr>
				<th>委員会 :</th>
				<td><input type="text" name="club" id="club" value="${club }"></td>
			</tr>

		</table>

	</form>
	
	<button type="button" id="btnSubmit">登録します</button>
	<button type="button" id="btnRt">戻る</button>

</body>
</html>