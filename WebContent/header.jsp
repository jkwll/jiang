<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:if test="${ sessionScope.user==null }">
 	<section class="user-profile">
		<div class="user-login clearfix"><div class="fl">未登录</div>
		<a class="primary-btn fr" href="login.jsp">登录</a>
		<a class="primary-btn fr" href="signup.jsp"  id="ff" rel="a">注册</a>
		</div>
	</section>
  </c:if>
  
	 <c:if test="${ sessionScope.user!=null }">
	 	<section class="user-profile">
		<div class="user-login clearfix" ><div class="fl">Hello ${sessionScope.user.userName}</div>
		<a class="primary-btn fr" href="${pageContext.request.contextPath }/OFF"  id="ff" rel="b">退出</a>		
		</section>
  	</c:if>

