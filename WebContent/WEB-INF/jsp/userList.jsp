<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:debug></s:debug>
	<c:set var="users" value="${users}"/>
	<c:set var="user" value="${user}"/>
	<c:if test="${null != users}">
		<h3>所有用户</h3>
		<br>
<!-- cnm的form标签要放在循环里面 -->
	<table>
		<c:forEach items="${users}" var="user">
			<form action="${pageContext.request.contextPath}/User/edit.action" method="post">
					<tr>
						<td>${user.userId} - ${user.userName} - ${user.realName} - ${user.gender} - ${user.tel} - ${user.password}</td>
						<td><input type="submit" value="编辑用户"></td>
						<td><a href="${pageContext.request.contextPath}/User/delete.action?userId=${user.userId}"><input type="button" value="删除"></a></td>
					</tr>
				 <!-- 将值用模型驱动传给Action 	 -->
				<!-- 隐藏域带值 -->
				<input type="hidden" name="userId" value="${user.userId}"> 
				<input type="hidden" name="userName" value="${user.userName}">
				<input type="hidden" name="realName" value="${user.realName}">
				<input type="hidden" name="gender" value="${user.gender}">
				<input type="hidden" name="tel" value="${user.tel}">
				<input type="hidden" name="password" value="${user.password}"> 
			</form>
		</c:forEach>
	</table>
		
	</c:if>
	
	
	<c:if test="${null != user}">
		<h3>查询一个</h3>
		查询结果 = ${user}<input type="submit" value="编辑用户">
	</c:if>
	

	
</body>
</html>