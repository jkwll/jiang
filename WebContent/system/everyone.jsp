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
	margin:0 auto;
		height: 48px;
	width: 151px;
	background:url('img/logo.png');
	/*background-size:100% 100%*/
}
	</style>
<body>
	<div id="pageWrapper">
		<header class="pass-header ">
			<a class="pass-header-back" id="goBack" onClick="javascript:history.back(-1);"></a>
			<font class="pass-header-title" Style="font-size:18px" >所有用户 </font>
		</header>
		<form action="${pageContext.request.contextPath }/LoginServlet"  autocomplete="off" method="POST" class="form-wrapper">
			<div class="img-div">
				<div class="logo-dom"><img height="48px" width="151px" src="img/logo.png"/></div>
			</div>
17
		
	<table class="table table-striped">
	  		<tr>
				<td>NAME</td>
	  			<td>QQ</td>
	  		</tr>
  		 	<!-- 迭代数据 -->
	  		<c:choose>
	  			<c:when test="${not empty requestScope.ListUser}">
	  			<c:forEach var="user" items="${requestScope.ListUser}" varStatus="vs">
			    	
					<c:if test="${user.flag =='1'}">
		 			<tr  >		
						<td><a href="${pageContext.request.contextPath }/AdminServlet?flag=userflag&id=${user.id}" style="color:#EFEFEF">${user.id }_${user.userName }</a></td>
						<td><a href="${pageContext.request.contextPath }/AdminServlet?flag=show&id=${user.id}" style="color:#EFEFEF">${user.qq }</a></td>
					</tr>
	  				</c:if>
					<c:if test="${user.flag !='1'}">
		 			<tr   >		
						<td><a href="${pageContext.request.contextPath }/AdminServlet?flag=userflag&id=${user.id}" style="color:#333333">${user.id }_${user.userName }</a></td>
						<td><a href="${pageContext.request.contextPath }/AdminServlet?flag=show&id=${user.id}" style="color:#333333">${user.qq }</a></td>
						<!-- <td>
						
						<a href="${pageContext.request.contextPath }/AdminServlet?flag=userflag&id=${user.id}">标记</a>
						<a href="${pageContext.request.contextPath }/AdminServlet?flag=show&id=${user.id}">查看</a>
	
						</td>
						-->
						</tr>
	  				</c:if>
					
				</c:forEach>
	  			</c:when>
	  			<c:otherwise>
	  				<tr>
	  					<td colspan="3">对不起，没有你要找的数据</td>
	  				</tr>
	  			</c:otherwise>
	  		</c:choose>  		

  		
  	</table>
			<div class="f14 clearfix login-problem">
				<a href="${pageContext.request.contextPath}/AdminServlet?flag=showfilter">查看所有</a>
			</div>
			</form>
	</div>                                                                                   
</body>
</html>