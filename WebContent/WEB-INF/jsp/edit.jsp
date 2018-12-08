<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑用户</title>
</head>
<body>
	<s:debug></s:debug>
	<h2>编辑用户信息</h2>
		如果有没输入的项目，则该项是空值，暂时无法用js验证
	<form action="${pageContext.request.contextPath}/User/editUser.action" method="post">
		<table>
			<tr><!-- 隐藏域存id -->
				<td><input type="hidden" name="userId" value="${userId}"></td>
			</tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userName" placeholder="${userName}"></td>
			</tr>
			<tr>
				<td>真实姓名：	</td>
				<td><input type="text" name="realName" placeholder="${realName}"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" placeholder="${password}"></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" placeholder="${password}"></td>
			</tr>
			<tr>
				<td>手机号码：</td>
				<td><input type="text" name="tel" placeholder="${tel}"></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<input type="radio"name="gender" value="m">男
					<input type="radio"name="gender" value="f">女
				
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="确认修改"></td>
				
			</tr>
		</table>
		
		
					   	

	</form>
	
</body>
</html>