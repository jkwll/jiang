<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html style="font-size: 53.33%;">
<head>
<meta content="text/html; charset=UTF-8">
<title>注册酷音乐账号</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=240, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">

<link href="css/base_7e1382b.css" type="text/css" rel="stylesheet">                                        
	<style>
	.logo-dom{
	background:url();
	/*height: 48px;
	width: 151px;*/
	}
	</style>
	<script>
	function submit_key(){
		var name = document.getElementById("userName").value;
		var pwd = document.getElementById("passWord").value;
		var qq = document.getElementById("qq").value;
		if(name!=""&&pwd!=""&&qq!=""){
		var sub = document.getElementById("login-submit");
		sub.removeAttribute('disabled');
		sub.style.color="#FFFFFF"
		}
	}
	function showpwd(){
		var p = document.getElementById("passWord");
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
			<font class="pass-header-title" Style="font-size:18px" >酷音乐帐号注册  </font>
		</header>
	
			<form action="${pageContext.request.contextPath }/SignupServlet" autocomplete="off" method="POST" class="form-wrapper">
				<div class="img-div">
					<div class="logo-dom"><img height="48px" width="151px" src="img/logo.png"/></div>
				</div>
				<p class="error_area" id="error" ></p>
					   <a id="aflag" Style="ccolor: red"><font color="red">${requestScope.UserError}</font></a> <!-- 这里暂时用这个做标记 -->
					<p class="form-item form-input-wrapper form-item-username form-input-mobile">
					<input type="text" name="userName" class="text-input" maxlength="18" placeholder="手机号/邮箱/用户名" value="" id="userName" oninput="submit_key()" >
					<span class="input-clearValue"></span>
					<span id="login-userlist" class="pass-input-aide pass-input-userlist"></span>
				</p>
				
				<p class="form-item form-input-wrapper form-item-username form-input-mobile">
					<input type="text" class="text-input" id="qq" name="qq" maxlength="11" placeholder="QQ" value="" oninput="submit_key()">
					<span class="input-clearValue"></span>
				</p>
				
			
				
				
				<p class="form-item form-input-wrapper form-item-password form-input-mobile">
					<input type="password" name="passWord" class="text-input" id="passWord" maxlength="18" placeholder="密码" value="" oninput="submit_key()">
					<span onclick="showpwd()" id="login-pwdToggle" class="input-aide input-pwdToggle"></span>
					</p>
				<input type="submit" value="注册"   id="login-submit" class="pass-button-full pass-button-full-disabled"  disabled="disabled">
				<div class="f14 clearfix login-problem">
					<a class="left" href="index.jsp">返回主页</a>
				</div>
	
			</form>
		<span id = "s"></span>
	</div>                                                                                   

</body>
</html>