﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>文章列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		if(page){
			$("#page").val(page);
		}
		document.customerForm.submit();	
	}
</SCRIPT>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm" action="${pageContext.request.contextPath }/Article/selectPage.action" method=get>	
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/img/new_019.jpg" border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/img/new_020.jpg" height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/img/new_021.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath }/img/new_022.jpg">
						<IMG src="${pageContext.request.contextPath }/img/new_022.jpg" border=0>
					</TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：文章管理 &gt; 文章列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											
										</TABLE>
										
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=grid style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc" cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>文章id</TD>
													<TD>文章标题</TD>
													<TD>作者</TD>
													<TD>作者Id</TD>
													<TD>发布时间</TD>
													<TD>最后一次修改时间</TD>
													
													
												</TR>
												<c:forEach items="${articles}" var="article">
												<TR style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${article.artId }</TD>
													<TD>${article.artTitle}</TD>
													 <TD>${article.user.userName}</TD>
													<TD>${article.user.userId}</TD>  
													<TD>${article.pubTime}</TD>
													<TD>${article.lastMod}</TD>
													
													<TD>
													<a href="${pageContext.request.contextPath }/Article/artDetail.action?artId=${article.artId}">文章详情</a>
													<a href="javascript:void(0)" onclick="deleteArticle('${article.artId}')">删除</a> 
													
													&nbsp;&nbsp;
													</TD>
												</TR>	
												</c:forEach>
												
											</TBODY>
											<TBODY>
												<TR>
													<TD>当前页面：${currentPage }/${totalArticles}</TD>
													<TD>选择页码</TD>
													<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="toPage"></TD>
													<TD><INPUT class=button id=sButton2 type=submit value=" 选择" name=sButton2></TD>
													<TD><a href="${pageContext.request.contextPath }/Article/prePage.action?currentPage=${currentPage}">上一页</a></TD>
													<TD><a href="${pageContext.request.contextPath }/Article/nextPage.action?currentPage=${currentPage}">下一页</a></TD>
												</TR>
												
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<%-- <TR>
									<TD>
										<SPAN id=pagelink>
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${total}</B>]条记录,[<B>${totalPage}</B>]页
												,每页显示
												<select name="pageSize">
												
												<option value="15" <c:if test="${pageSize==1 }">selected</c:if>>1</option>
												<option value="30" <c:if test="${pageSize==30 }">selected</c:if>>30</option>
												</select>
												条
												[<A href="javascript:to_page(${page-1})">前一页</A>]
												<B>${page}</B>
												[<A href="javascript:to_page(${page+1})">后一页</A>] 
												到
												<input type="text" size="3" id="page" name="page" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
										</SPAN>
									</TD>
								</TR> --%>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/img/new_023.jpg">
						<IMG src="${pageContext.request.contextPath }/img/new_023.jpg" border=0>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/img/new_024.jpg" border=0></TD>
					<TD align=middle width="100%" background="${pageContext.request.contextPath }/img/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/img/new_026.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
	<script type="text/javascript">
		function deleteArticle(artId){
			//删除的时间
			var date = new Date() ;
			var year = date.getFullYear() ;
			var month = date.getMonth()+1 ;
			var day = date.getDate() ;
			var now = year + "-" + month + "-" + day ;
			
			var art_id = artId ;
			
			
			var content = {
					"artId"  :  art_id  ,
					"delTime"  : now 
			} ;
			alert(JSON.stringify(content)) ;
			
			$.ajax({
				url : "${pageContext.request.contextPath}/Article/deleteArticle.action" ,
				type : "post" ,
				data : content ,
				dataType : "json",
				success : function(data){
					alert("删除成功！您可以前往回收站查看");
					window.location = "${pageContext.request.contextPath}/Article/articleList.action" ;
				}
			}) ;
		}
	</script>
	<s:debug></s:debug>
</BODY>
</HTML>
