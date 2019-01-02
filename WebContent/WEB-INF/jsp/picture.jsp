<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图集</title>
</head>
<body>
<TABLE>
		<TR style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
			<TD>资源展示</TD>
			<TD>资源类别</TD>
			<TD>上传者</TD>
			<TD>描述</TD>
			<TD>发布时间</TD>
													
		</TR>
	<c:forEach items="${resources}" var="resource">
		<TR style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
				<TD><a href="${pageContext.request.contextPath }/userResource/resDetail.action?resId=${resource.resId}"><img style="width:50px;height:50px" src="/pic/${resource.picture.picName}" /></a></TD>
				<TD>${resource.resTag}</TD>
				<TD>${resource.user.userName}</TD>
				<TD>${resource.resCom}</TD>
				<TD>${resource.pubTime}</TD>

		</TR>	
	</c:forEach>
	<TR>
		<TD>页码</TD>
		<TD>${currentPage }/${totalResource }</TD>
	</TR>
	<TR>
		<TD><a href="${pageContext.request.contextPath }/userResource/prePage.action?currentPage=${currentPage}">上一页</a></TD>
		<TD><a href="${pageContext.request.contextPath }/userResource/nextPage.action?currentPage=${currentPage}">下一页</a></TD>
	</TR>
	
</TABLE>
	
	<a href="${pageContext.request.contextPath }/index.jsp">返回首页</a>
<s:debug></s:debug>
</body>
</html>