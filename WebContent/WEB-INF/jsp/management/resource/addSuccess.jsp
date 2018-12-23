<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上传成功</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
</head>
<body>
	<h2>资源上传成功！</h2>
	<a href="${pageContext.request.contextPath}/Resource/resList.action"><input type="button" id="show" value="点击查看"></a>
	<a href="${pageContext.request.contextPath}/Resource/uploadRes.action"><input type="button" id="continue" value="继续上传"></a>
	
</body>
</html>