<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'everyone.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form >
  	<table width="40%" align="center">
	 <tr> <td>	输入id：
	  	<input type="text"/>
	  	<input type="button" value = "查询">
	  	</td>
	  	</tr>
  	</table>
  	
  	</form>
  	<table border="1" width="40%" align="center" cellpadding="5" cellspacing="0">
  		<tr>
			<td>序号</td>
  			<td>QQ</td>
  		
  			<td>结果</td>
  			<td>时间</td>
  			<td>次数</td>
  			
  			
  			
  		<!-- 迭代数据 -->
  		<c:choose>
  			<c:when test="${not empty requestScope.ListFruit}">
  				<c:forEach var="fruit" items="${requestScope.ListFruit}" varStatus="vs">
  					<tr >
  						<td>${vs.count }</td>
  						<td>${fruit.qq }</td> 
  						<td>
  						 <c:if test="${fruit.award==1}">中奖</c:if>
  						 <c:if test="${fruit.award==0}">未中</c:if>
  						</td>
  						<td>${fruit.time }</td>
  						<td>${fruit.num }</td>
  					</tr>
  				</c:forEach>
  			</c:when>
  			<c:otherwise>
  				<tr>
  					<td colspan="3">对不起，没有你要找的数据</td>
  				</tr>
  			</c:otherwise>
  		</c:choose> 
  	</table>
  	
  	
  </body>
</html>
