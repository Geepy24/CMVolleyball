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
	<form method="post" action="${pageContext.request.contextPath }/Resource/checkPic.action">
	<img style="width:500px;height:500px;margin:0,auto;" alt="${resCom}" src="/pic/${picName}">
	<table>
		<tr>
			<td></td>
		</tr>
		
		<tr>
			<td><h4><strong>上传者id:</strong></h4></td>
			<td><input type="text" value="${userId }" name="userId"  readonly></td>
			<td><input type="hidden" value="${picId }" name="picId" ></td>
		</tr>
		<tr>
			<td><h4><strong>图片描述:</strong></h4></td>
			<td><input type="text" value="${resCom }" name="resCom" readonly></td>
		</tr>
		<tr>
			<td><h4><strong>图片名称:</strong></h4></td>
			<td><input type="text" value="${picName }" name="picName" readonly></td>
			<td><input type="hidden" value="${picUri }" name="picUri"></td>
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
		<%-- <tr>
			<td><a href="${pageContext.request.contextPath }/Resource/nextRes.action?Id=${resId}&resTag=${resTag}">下一张</a>
				<a href="${pageContext.request.contextPath }/Resource/preRes.action?resId=${resId}&resTag=${resTag}">上一张</a></td>
			<td><a href="${pageContext.request.contextPath }/Resource/resList.action">返回列表</a></td>
		</tr> --%>
		
		</table>
		</form>
	</div>
	<s:debug></s:debug>
</body>
</html>