<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html style="font-size: 53.33%;">
<head>
<meta content="text/html; charset=UTF-8">
<title>酷音乐</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=240, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<link href="css/bootstrap.css" type="text/css" rel="stylesheet">
<link href="css/base_7e1382b.css" type="text/css" rel="stylesheet">                                        
                                        
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
	</script>
<body>
	<div id="pageWrapper">
		<header class="pass-header ">
			<a class="pass-header-back" id="goBack" onClick="javascript:history.back(-1);"></a>
			<font class="pass-header-title" Style="font-size:18px" >注册资料  </font>
		</header>
		<form action="${pageContext.request.contextPath }/LoginServlet"  autocomplete="off" method="POST" class="form-wrapper">
			<div class="img-div">
				<div class="logo-dom"><img height="48px" width="151px" src="img/logo.png"/></div>
			</div>
		
		<table class="table table-striped" >
  		<tr>
			<td>ID</td>
			<td>用户名</td>
  			<td>QQ</td>
  			
  		<tr>

  	 <c:if test="${ not empty requestScope.User}">
  		<tr>
			<td>${requestScope.User.id}</td>
			<td>${requestScope.User.userName}</td>
  			<td>${requestScope.User.qq}</td>	
  		<tr>
     </c:if>	
  	</table>
  	
	<div class="f14 clearfix login-problem">
				<a class="left" href="index.jsp">返回主页</a>
			
			</div>
		</form>
		<span id = "s"></span>
	</div>                                                                                   
</body>
</html>
