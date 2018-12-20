<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上传资源</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
</head>
<body>

	<s:debug></s:debug>
	<h2>编辑资源信息</h2>
		
	<form name="uploadForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/Resource/addRes.action"> 
		<input type="hidden" id="pubTime" name="pubTime" value="" >
		<table>
			<tr>
				<td><h4>上传资源:</h4></td>
			</tr>
			<tr>
				<td><input type="file" name="upload" id="upload"></td>
			</tr>
			<tr>
				<td><h4>资源类别:</h4></td>
	
			</tr>
			<tr>
				<td>
					<input type="radio"name="resTag" value="pic">图片
					<input type="radio"name="resTag" value="mov">视频
				</td>
			</tr>
			<tr>
				<td><h4>添加描述:</h4></td>
				
			</tr>
			<tr>
				<td><input type="text" name="resCom" ></td>
			</tr>
			<tr>
				<td><input type="submit" value="确认提交"></td>
				
			</tr>
		</table>
		

	</form>
	<script type="text/javascript">
	function getNow(){
		var date = new Date() ;
  		var year = date.getFullYear() ;
  		var month = date.getMonth()+1 ;
  		var day = date.getDate() ;
  		
  		var now = year + "-" + month + "-" + day ;
		alert(now) ;
  		document.getElementById("pubTime").value = now ;
  		alert($("#pubTime").val()) ;
	}
	</script>
	
</body>
</html>