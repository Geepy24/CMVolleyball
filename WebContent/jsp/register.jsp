<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册界面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

</head>
<body>

	<h1>用户注册</h1>
	<form action="${pageContext.request.contextPath}/User/registerAction.action" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="userName" id="input_userName"></td>
				<td id="userName_flag"></td>
			</tr>
			<tr>
				<td>真实姓名：</td>
				<td><input type="text" name="realName" id="input_realName">(选填)</td>
				<td id="realName_flag"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" id="input_password"></td>
				<td id="password_flag"></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="password" id="ensure_password"></td>
				<td id="ensure_password_flag"></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td><input type="radio" name="gender" checked="checked" id="input_gender"
						value="m">男
				<input type="radio" name="gender" value="f">女</td>
			</tr>
			<tr>
				<td>联系方式：</td>
				<td><input type="number" name="tel" id="input_tel">(选填)</td>
				<td id="tel_flag"></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><input type="text" name="checkCode" id="input_checkCode"></td>
				<td id="checkCode_flag"></td>
			</tr>
			<tr>
				<td><input type="button" value="确认提交" id="submit"></td>
			</tr>
		</table>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>


		
	</form>
</body>
</html>