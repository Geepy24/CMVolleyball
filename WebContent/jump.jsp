<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>页面正在跳转</title>
</head>
<body>
	<!-- 使用专门的跳转页，如果直接跳转，会导致刷新重复提交表单 -->
	<script type="text/javascript">
		alert(页面正在跳转) ;
		window.location.href="${pageContext.request.contextPath}/";
	</script>
	
</body>
</html>