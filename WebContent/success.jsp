<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成功页面</title>
</head>
<body>
	success!
	<s:debug></s:debug>
	<hr>
	<h3>所有用户</h3>
	<br>
	<c:forEach items="${users}" var="user">
		${user.userId} - ${user.userName} - ${user.realName} - ${user.gender} - ${user.tel} - ${user.password}
		<input type="submit" value="编辑用户">
		<br>
		
	</c:forEach>

	 
</body>
</html>