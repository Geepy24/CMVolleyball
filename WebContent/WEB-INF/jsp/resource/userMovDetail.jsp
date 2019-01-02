<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看视频	</title>
<link href="${pageContext.request.contextPath }/css/video-js.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/video.min.js"></script>
<style>
	body {
		background-color: #191919
	}
	.m {
		width: 960px;
		height: 400px;
		margin-left: auto;
		margin-right: auto;
		margin-top: 100px;
	}
</style>

</head>
<body style="text-align:center;">
	 <div class="m">
      <video id="my-video" class="video-js" controls preload="auto" width="960" height="400"
		  poster="/movpre/${movie.mediaPreview.mpName }" data-setup="{}">
        <source src="/mov/${movie.movName }" type="video/mp4">
      </video>

	</div>
	
	
	<div id="show_pic" style="margin:0,auto;width:100%;height:800px;">
	<table>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td><h4><strong>资源描述:</strong></h4></td>
			<td><span>${resCom}</span></td>
		</tr>
		<tr>
			<td></td>
			<td><h4><strong>资源类别:</strong></h4></td>
			<td><span>${resTag}</span></td>
		</tr>
		<tr>
			<td></td>
			<td><a href="${pageContext.request.contextPath }/userResource/nextRes.action?resId=${resId}&resTag=${resTag}">下一张</a>
				<a href="${pageContext.request.contextPath }/userResource/preRes.action?resId=${resId}&resTag=${resTag}">上一张</a></td>
			<td><a href="${pageContext.request.contextPath }/userResource/indexresource.action?resTag=${resTag}">返回列表</a></td>
		</tr>
		
		</table>
	</div>
	<hr>
	
	
	<s:debug></s:debug>
</body>
</html>