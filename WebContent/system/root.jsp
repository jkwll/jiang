<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html style="font-size: 53.33%;">
<head>
<meta content="text/html; charset=UTF-8">
<title>酷音乐</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=240, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">

<link href="../css/base_7e1382b.css" type="text/css" rel="stylesheet">                                        
	<style>
	.logo-dom{
	background:url("");
	/*background:url(img/logo.png);这里不写有的浏览器可能不兼容*/
	height: 48px;
	width: 151px;
	}
	</style>
		<script>
	function submit_key(){
		var name = document.getElementById("login-username").value;
		var pwd = document.getElementById("login-password").value;
		if(name!=""&&pwd!=""){
		var sub = document.getElementById("login-submit");
		sub.removeAttribute('disabled');
		sub.style.color="#FFFFFF"
		}
	}
	window.onload = function(){
		var v = document.getElementById("aflag").innerText;
	}
	function showpwd(){
		var p = document.getElementById("login-password");
		if(p.type=="password"){
			p.type="text";			
		}else{
			p.type="password";						
		}
	}
	</script>

<body>
	<div id="pageWrapper">
		<header class="pass-header ">
			<a class="pass-header-back" id="goBack" onClick="javascript:history.back(-1);"></a>
			<font class="pass-header-title" Style="font-size:18px" >后台 </font>
		</header>
		<form action="${pageContext.request.contextPath }/AdminServlet?flag=users" autocomplete="off" method="POST" class="form-wrapper">
			<div class="img-div">
				<div class="logo-dom"><img height="48px" width="151px" src="../img/logo.png"/></div>
				<!-- <p class="error_area" id="error" ></p> -->
			</div>
			  <a id="aflag" Style="ccolor: red"><font color="red"> ${requestScope.PointOut}</font></a>
			<p class="form-item form-input-wrapper form-item-username form-input-mobile">
				<input type="text" name="userName" class="text-input" maxlength="20" placeholder="用户名" value="" id="login-username" oninput="submit_key()" >
				<span class="input-clearValue"></span>
				<span id="login-userlist" class="pass-input-aide pass-input-userlist"></span>
			</p>
			<p class="form-item form-input-wrapper form-item-password form-input-mobile">
				<input type="password" name = "passWord" class="text-input" id="login-password" placeholder="密码" value="" oninput="submit_key()">
				<span onclick="showpwd()" id="login-pwdToggle" class="input-aide input-pwdToggle"></span>
			</p>
			
			<input type="submit" value="登录"   id="login-submit" class="pass-button-full pass-button-full-disabled"  disabled="disabled">
			<div class="f14 clearfix login-problem">
				<a class="type-other-login"  href="index.jsp">返回主页</a>
			</div>
		</form>
		<span id = "s"></span>
	</div>                                                                                   
</body>
</html>