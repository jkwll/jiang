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
			<font class="pass-header-title" Style="font-size:18px" >意见反馈  </font>
		</header>
		
			<div class="img-div">
				<!-- <div class="logo-dom"><img height="48px" width="151px" src="img/logo.png"/></div> -->
				<div align="center"><img height="48px" width="151px" src="img/logo.png"/></div>
				<p class="error_area" id="error" ></p>
			</div>
	<form action="${pageContext.request.contextPath }/UserServlet?flag=feedback"  autocomplete="off" method="POST" class="form-wrapper">
		<font color="red">*</font>描述
	<textarea name="content" class="form-control" rows="3" placeholder="请输入你遇到的问题或者意见"></textarea>
	邮箱/QQ/手机
	<textarea name="contact" class="form-control" rows="1" placeholder="选填，便于群主联系您"></textarea>
	<br>

  	<div  align="center">	<button style="width: 290px;" type="submit" class="btn btn-info">提交</button></div>
  	</form>
  	
  	<br>
		<span id = "s"></span>
	</div>   
	  <c:if test="${ requestScope.v==null }">
 			<div class="f14 clearfix login-problem">
				<div class="left"><a  href="index.jsp">返回主页</a></div>
			</div>	  
  		</c:if>                                                           
	  <c:if test="${ requestScope.v!=null }">
 			<div class="f14 clearfix login-problem">
				<div class="left">提交成功，感谢您的反馈！点击<a  href="index.jsp">返回主页</a></div>
			</div>	  
  		</c:if>                                                           
</body>
</html>
