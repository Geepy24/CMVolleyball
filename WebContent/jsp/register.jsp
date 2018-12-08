<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册界面</title>
</head>
<body>
	<h1>用户注册</h1>
	<form action="${pageContext.request.contextPath}/Register/registerAction" method="post">
		用户名：<input type="text" name="userName"><br>
		真实姓名：<input type="text" name="realName">(选填)<br>
		密码：<input type="password" name="password"><br>
		确认密码：<input type="password" name="password"><br>
		性别：<input type="radio" name="gender" checked="checked">男
			<input type="radio" name="gender">女<br>
		联系方式：<input type="number" name="tel"><br>
		验证码：<input type="text" name="check"><br>
		<input type="submit" value="确认提交">
	</form>
</body>
</html>