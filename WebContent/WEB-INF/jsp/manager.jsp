<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理页面</title>
</head>
<body>
	<h1>管理员可见</h1>
	<a href="${pageContext.request.contextPath}/User/hello.action">点我</a>
	<a href="${pageContext.request.contextPath}/User/findAll.action">查询所有</a>
	
	<form action="${pageContext.request.contextPath}/User/findByName.action" method="get">
	  	根据用户名查询: <input type="text" name="userName" />	
	  	<input type="submit" value="submit" />
	</form>
	<hr>
	<a href="">文章管理系统</a>
	
</body>
</html>