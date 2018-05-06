<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Struts Kadai Search</title>
<script src="/StrutsKadai/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#btnLogout").on('click', function(){
			location.href="/StrutsKadai/logout.do";
		});
		
		$("#btnJoin").on('click', function(){
			location.href="/StrutsKadai/user/join.jsp";
		});
		
		$("#btnSearch").on('click', function(){
			if($("#id").val() == "" && $("#name").val() == "" && $("#kana").val() == ""){
				alert("どれかひとつの項目は入力してください。");
				return false;
			}
			
			if ($("#id").val().match(/[^A-Za-z0-9]+/)) {
				alert("ユーザーIDは半角英数字で入力してください。");
				return false;
			}
			
			if (!$("#name").val().match(/[^A-Za-z0-9]+/) && $("#name").val() != "") {
				alert("名前は全角で入力してください。");
				return false;
			}
			
			if ($("#kana").val().match(/[^A-Za-z0-9]+/)) {
				alert("カナは半角で入力してください。");
				return false;
			}
			
			$("#cmd").val("search");
			$("#frm").submit();
		});
		
		$("#btnUpdate").on('click', function(){
			var id = $("#btnUpdate").attr("data-value");
			location.href="search.do?id=" + id + "&cmd=update";
		});
		
		$("#btnDelete").on('click', function(){
			var id = $("#btnDelete").attr("data-value");
			location.href="search.do?id=" + id + "&cmd=delete";
		});

	});
</script>
</head>
<body>

<div>前方一致で検索します。</div>

<h1>検索画面</h1>

<form action="/StrutsKadai/search.do" id="frm" method="post">

	<table border="1">
	
		<tr>
			<th>ID</th><td><input type="text" name="id" id="id" value="${requestScope.id }"></td>
		</tr>
		
		<tr>
			<th>名前</th><td><input type="text" name="name" id="name" value="${name }"></td>
		</tr>
		
		<tr>
			<th>カナ</th><td><input type="text" name="kana" id="kana" value="${kana }"></td>
		</tr>
		
	</table>
	
	<input type="hidden" name="cmd" id="cmd">
	
</form>

<button type="button" id="btnSearch">検索</button>
<button type="button" id="btnJoin">新規登録</button>
<button type="button" id="btnLogout">ログアウト</button>



<table border="1">
	<tr>
		<th>ID</th>
		<th>名前</th>
		<th>カナ</th>
		<th>生年月日</th>
		<th>委員会</th>
		<th>操作</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.id }</td>
			<td>${vo.name }</td>
			<td>${vo.kana }</td>
			<td>${vo.birth }</td>
			<td>${vo.club }</td>
			<td><button type="button" id="btnUpdate" data-value="${vo.id }">更新</button><button type="button" id="btnDelete" data-value="${vo.id }">削除</button></td>
		</tr>
	</c:forEach>
</table>


</body>
</html>