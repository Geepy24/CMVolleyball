<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<FORM id="customerForm" name="customerForm" action="${pageContext.request.contextPath }/Resource/selectPage.action" method=get>	
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
								<TD class=manageHead>当前位置：资源管理 &gt; 图片列表</TD>
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
													<TD>资源展示</TD>
													<TD>资源类别</TD>
													<TD>上传者</TD>
													<TD>描述</TD>
													<TD>发布时间</TD>
													
												</TR>
												<c:forEach items="${resources}" var="resource">
												<TR style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD><img style="width:50px;height:50px" src="/pic/${resource.picture.picName}" /></TD>
													<TD>${resource.resTag}</TD>
													<TD>${resource.user.userName}</TD>
													<TD>${resource.resCom}</TD>
													<TD>${resource.pubTime}</TD>
													
													<TD>
													<a href="${pageContext.request.contextPath }/Resource/resDetail.action?resId=${resource.resId}">查看详情</a>
													<a href="javascript:void(0)" onclick="confirmDelete('${resource.resId}')">删除</a>
													
													&nbsp;&nbsp;
													</TD>
												</TR>	
												</c:forEach>
												
											</TBODY>
											<TBODY>
												<TR>
													<TD>当前页面：${currentPage }/${totalResource}</TD>
													<TD>选择页码</TD>
													<TD><INPUT class=textbox id=sChannel2 style="WIDTH: 80px" maxLength=50 name="toPage"></TD>
													<TD><INPUT class=button id=sButton2 type=submit value=" 选择" name=sButton2></TD>
													<TD><a href="${pageContext.request.contextPath }/Resource/prePage.action?currentPage=${currentPage}">上一页</a></TD>
													<TD><a href="${pageContext.request.contextPath }/Resource/nextPage.action?currentPage=${currentPage}">下一页</a></TD>
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
		function confirmDelete(resId){
			var flag = confirm("是否要删除该张图片") ;
			//alert(flag) ;
			if(flag == false){
				return ;
			}
			//alert(resId) ;
			$.ajax({
				url : "${pageContext.request.contextPath}/Resource/delResource.action?resId="+resId ,
				success : function(data){
					alert("删除成功！");
					window.location = "${pageContext.request.contextPath}/Resource/selectPage.action?toPage=${currentPage}" ;
				}
			}) ; 
		}
	</script>
	<s:debug></s:debug>
</BODY>
</HTML>
