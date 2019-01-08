<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<title>审核视频	</title>
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
		  poster="/movpre/${mediaPreview.mpName }" data-setup="{}">
        <source src="/mov/${movName }" type="video/mp4">
      </video>

	</div>
	
	
	<div id="show_pic" style="margin:0,auto;width:100%;height:800px;">
	<form method="post" action="${pageContext.request.contextPath }/Resource/checkMov.action"
	onsubmit="return verify() ;">
	<table>
		<tr>
			<td></td>
			<td><h4><strong>视频id:</strong></h4></td>
			<td><input type="text" name="movId" id="movId" value="${movId }" readonly></td>
		</tr>
		<tr>
			<td><h4><strong>资源描述:</strong></h4></td>
			<td><input type="text" name="resCom" id="resCom" value="${resCom }" readonly></td>
			
		</tr>
		<tr>
			<td><h4><strong>上传者id:</strong></h4></td>
			<td><input type="text" name="userId" id="userId" value="${userId }" readonly></td>
		</tr>
		<tr>
			<td>视频名称</td>
			<td><input type="text" name="movName" id="movName" value="${movName }" readonly></td>
			<td><input type="hidden" name="movUri" id="movUri" value="${movUri }" ></td>
			</tr>
		<tr>
			<td><h4><strong>是否审核通过:</strong></h4></td>
			<td><input type='radio' name='checkTag' id='checkTag' value=1 />通过</td>
			<td><input type='radio' name='checkTag' id='checkTag' value=-1 />不通过</td>	
		</tr>
		<tr>
			<td><h4><strong>审核注释:</strong></h4></td>
			<td><input type="text" value="" name="checkCom"></td>
		</tr>
		<tr>
			<td><input type="submit"  value="提交"></td>
		</tr>
		<tr>
			<td><a href="${pageContext.request.contextPath }/Resource/toMcList.action">返回列表</a></td>
		</tr> 
		
		</table>
		</form>
	</div>
	<hr>
	<script type="text/javascript">
		function verify(){
			var tag = $('input:radio[name="checkTag"]:checked').val();
			//alert(tag) ;
			//未选择是否通过审核
			if(typeof(tag) == "undefined"){
				alert("请选择是否通过审核") ;
				return false ;
			}
			return true ;
		}
	</script>
	
	<s:debug></s:debug>
</body>
</html>