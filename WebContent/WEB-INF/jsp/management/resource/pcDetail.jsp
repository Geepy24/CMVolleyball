<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图片详情	</title>
</head>
<body style="text-align:center;">
	<div id="show_pic" style="margin:0,auto;width:100%;height:800px;">
	<img style="width:500px;height:500px;margin:0,auto;" alt="${resCom}" src="/pic/${picName}">
	<table>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td><h4><strong>图片id:</strong></h4></td>
			<td><input type="text" value="${picId }" name="picId" disabled="disabled"></td>
		</tr>
		<tr>
			<td><h4><strong>上传者id:</strong></h4></td>
			<td><input type="text" value="${userId }" name="userId" disabled="disabled"></td>
		</tr>
		<tr>
			<td><h4><strong>图片描述:</strong></h4></td>
			<td><input type="text" value="${resCom }" name="resCom" disabled="disabled"></td>
		</tr>
		<tr>
			<td><h4><strong>图片地址与名称:</strong></h4></td>
			<td><input type="text" value="${picUri }" name="picUri" disabled="disabled"></td>
			<td><input type="text" value="${picName }" name="picName" disabled="disabled"></td>
		</tr>
		<tr>
			<td><h4><strong>审核标记:</strong></h4></td>
			<td><input type="text" value="${checkTag }" name="checkTag" disabled="disabled"></td>
		</tr>
		<tr>
			<td><h4><strong>审核注释（不通过必填）:</strong></h4></td>
			<td><input type="text" value="${checkCom}" name="checkCom"></td>
		</tr>
		<tr>
			<td><a href="#">审核通过</a></td>
			<td><a href="#">审核不通过</a></td>
		</tr>
		<%-- <tr>
			<td><a href="${pageContext.request.contextPath }/Resource/nextRes.action?Id=${resId}&resTag=${resTag}">下一张</a>
				<a href="${pageContext.request.contextPath }/Resource/preRes.action?resId=${resId}&resTag=${resTag}">上一张</a></td>
			<td><a href="${pageContext.request.contextPath }/Resource/resList.action">返回列表</a></td>
		</tr> --%>
		
		</table>
	</div>
	<s:debug></s:debug>
</body>
</html>