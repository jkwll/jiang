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

	</script>
<body>
	<div >
		<header class="pass-header ">
			<a class="pass-header-back" id="goBack" onClick="javascript:history.back(-1);"></a>
			<font class="pass-header-title" Style="font-size:18px" >购买中心  </font>
		</header>
			<div class="img-div">
				<!-- <div class="logo-dom"><img height="48px" width="151px" src="img/logo.png"/></div> -->
				<div align="center"><img height="48px" width="151px" src="img/logo.png"/></div>
				<p class="error_area" id="error" ></p>
			</div>
  	<br>
  	</div>
 	<table align="center">
  	<tr>
  	<td><font size="5px">&nbsp;&nbsp;抽</font>奖活动正在进行中，参与抽奖可以有机会</td>
  	</tr>
  	<tr>
  	<td>获得年费半价优惠！可以点击<a href="${pageContext.request.contextPath }/UserServlet?flag=fruit">抽奖记录</a>，查看抽</td>
  	</tr>
  	<tr>
  	<td>奖情况。购买请联系QQ或者微信：1622913887</td>
  	</tr>
  	</table>	
 			<div class="f14 clearfix login-problem" style="margin:100px auto;" >
				<div class="left"><a  href="index.jsp">返回主页</a></div>
			</div>	
                                                                                                             
</body>
</html>
