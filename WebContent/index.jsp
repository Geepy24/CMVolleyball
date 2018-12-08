<%@page import="com.cm.domain.User"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome to CMVolleyball</title>
</head>
<body>

	
	
	<s:debug></s:debug>
	<c:set var="userName" value="${session.loginInfo.userName}"/>
	<c:if test="${null == userName}">
		<a href="${pageContext.request.contextPath}/User/login.action"><span>登录</span></a>
		<a href="${pageContext.request.contextPath}/User/register.action"><span>注册</span></a>
		<br>
	</c:if>

	
	
	<hr>
	只有登录了才能点进去：（拦截器）
	<a href="${pageContext.request.contextPath}/privateSource/management.action"><span>management</span></a>
	
	<c:set var="AuthorityError" value="${AuthorityError}"/>
	<c:if test="${null != AuthorityError}"  >
		<h3>${AuthorityError}</h3>
	<br>
	</c:if>

	
	
	<hr>
	登录状态：<br>
	
	<c:if test="${null != userName}"  >
		当前登录的用户为：${userName}<a href="${pageContext.request.contextPath}/Logout/logout.action">点击注销</a>
		<br>
	</c:if>
	<c:if test="${null == userName}">
		未登录<br>
	</c:if>
	
	
	
</body>
</html>