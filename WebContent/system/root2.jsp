<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 引入jstl核心标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>root</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  
  <body>
  <h1>登录之后查看所用用户</h1>
  <form action="${pageContext.request.contextPath }/AdminServlet?flag=users" method="post">
			用户名:<input type="text" name="userName"/>${requestScope.PointOut} 
			<br>
			密码：<input type = "password" name = "passWord">
			<br>
			<input type="submit"  value="登录" />

		</form>
     <br>
  </body>
</html>
