<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>视频播放模板页面</title>
<link href="${pageContext.request.contextPath }/css/video-js.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/video.min.js"></script>
</head>
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
<body>
	<div class="m">
      <video id="my-video" class="video-js" controls preload="auto" width="960" height="400"
		  poster="/movpre/${resName }" data-setup="{}">
        <source src="/mov/6.mp4" type="video/mp4">
      </video>

	</div>
</body>
</html>