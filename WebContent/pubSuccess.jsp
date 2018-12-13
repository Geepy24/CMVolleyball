<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script>
<title>文章发布成功！</title>
</head>
<body>
	<script type="text/javascript">
		
			
		
	
		
	</script>
	<h1>文章发布成功！</h1>
	<br>
	<h3><a id="a">点击查看</a></h3>
	<h3><a href="${pageContext.request.contextPath}/text-edit/index.html">再写一篇</a></h3>
	
	<script type="text/javascript">
		$("#a").click(function(){
			var thisURL = document.URL ;  //xxxxxx.jsp?artId=xxx
			var get = thisURL.split("?")[1] ;//artId=xxx
			var artId = get.split("=")[1] ;//xxx
			
			//js里能用el表达式，但是不能直接访问web-inf中的内容
			$("#a").attr('href','${pageContext.request.contextPath}/Article/showArticle.action?artId='+artId);
			
			
			
		}) ;
	</script>
</body>
</html>