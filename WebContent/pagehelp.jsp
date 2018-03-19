<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html style="font-size: 53.33%;">
<head>

<title>酷音乐</title>
<meta content="text/html; charset=UTF-8">
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
			<font class="pass-header-title" Style="font-size:18px" > 使用说明  </font>
		</header>
  	<br>
  	</div>
  	<table align="center" width="90%">
  	<tr>
  		<td><h2>一、本次抽奖条件</h2></td>
  	</tr> 	
  	<tr>
  		<td>&nbsp;&nbsp;1.注册资料填写的QQ号在酷狗群内</td>
  	</tr>
  	<tr>
  		<td> &nbsp;</td>
  	</tr>
  	
  	<tr>
  		<td><h2>二、功能介绍</h2></td>
  	</tr>
  	<tr>
  		<td>&nbsp;&nbsp;1.开始抽奖：每人每天仅限一次，抽中的概率为1/2</td>
  	</tr>
  	<tr>
  		<td>&nbsp;&nbsp;2.抽奖测试：这个是用来抽奖测试的，程序与"开始抽奖"是一样的</td>
  	</tr>
  	<tr>
  		<td>&nbsp;&nbsp;3.建议先使用抽奖测试，稍加练习可以提高抽奖概率</td>
  	</tr>
  	<tr>
  		<td>&nbsp;&nbsp;4.可以在我的资料中查看注册信息和抽奖信息</td>
  	</tr>
  	<tr>
  		<td> &nbsp;</td>
  	</tr>
  	
  	<tr>
  		<td><h2>三、帮助</h2></td>
  	</tr>
  	<tr>
  		<td>&nbsp;&nbsp;1.小站由我一人开发，难免会有bug,如果网站程序有问题，请联系本人QQ1622913887，十分感谢</td>
  	</tr>
  	<tr>
  		<td>&nbsp;&nbsp;2.用户有任何问题和意见和建议，都可以在主页"意见反馈"里面给我提供，谢谢大家的帮助</td>
  	</tr>
  	<tr>
  		<td>&nbsp;</td>
  	</tr>
  	<tr>
  		<td> 
  		<div class="left"><a  href="index.jsp">返回主页</a></div>
  		</td>
  	</tr>
  	
  	<tr>
  	

  	</tr>
  	</table>
 			
                                                                                                             
</body>
</html>
