<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD id=Head1>
<TITLE>导航</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-TOP: 0px;
	BACKGROUND-COLOR: #2a8dc8
}

BODY {
	FONT-SIZE: 11px;
	COLOR: #003366;
	FONT-FAMILY: Verdana
}

TD {
	FONT-SIZE: 11px;
	COLOR: #003366;
	FONT-FAMILY: Verdana
}

DIV {
	FONT-SIZE: 11px;
	COLOR: #003366;
	FONT-FAMILY: Verdana
}

P {
	FONT-SIZE: 11px;
	COLOR: #003366;
	FONT-FAMILY: Verdana
}

.mainMenu {
	FONT-WEIGHT: bold;
	FONT-SIZE: 14px;
	cursor: pointer;
	COLOR: #000000
}

A.style2:link {
	PADDING-LEFT: 4px;
	COLOR: #0055bb;
	TEXT-DECORATION: none
}

A.style2:visited {
	PADDING-LEFT: 4px;
	COLOR: #0055bb;
	TEXT-DECORATION: none
}

A.style2:hover {
	PADDING-LEFT: 4px;
	COLOR: #ff0000;
	TEXT-DECORATION: none
}

A.active {
	PADDING-LEFT: 4px;
	COLOR: #ff0000;
	TEXT-DECORATION: none
}

.span {
	COLOR: #ff0000;
}
</STYLE>

<SCRIPT type="text/javascript">
	function MenuDisplay(obj_id) {
		for (var i = 1; i <= 9; i++) {
			var obj = document.getElementById('table_' + i);
			if(obj){
				document.getElementById('table_' + i).style.display = 'none';
				document.getElementById('table_' + i + 'Span').innerText = '＋';
			}
			
		}
		var obj = document.getElementById(obj_id);
		if(obj){
			if (obj.style.display == 'none') {
				obj.style.display = 'block';
				document.getElementById(obj_id + 'Span').innerText = '－';
			} else {
				obj.style.display = 'none';
				document.getElementById(obj_id + 'Span').innerText = '＋';
			}
		}
		
	}
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1 action=YHMenu.aspx method=post>
		<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath}/img/new_005.jpg" border=0></TD>
					<TD align="center" width=180 background="${pageContext.request.contextPath}/img/new_006.jpg" height=35><B>网站管理-功能菜单</B></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath}/img/new_007.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
			<TBODY>
				<TR>
					<TD width=15 background="${pageContext.request.contextPath}/img/new_008.jpg"></TD>
					<TD vAlign=top width=180 bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=3 width=165 align=center border=0>
							<TBODY>
								<TR>
									<TD class=mainMenu onClick="MenuDisplay('table_1');">
										<SPAN class=span id=table_1Span>＋</SPAN> 用户管理
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=table_1 style="DISPLAY: none" cellSpacing=0 cellPadding=2 width=155 align=center border=0>
											<TBODY>
												<TR> 
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/User/findAll.action" target=main>－ 用户列表</A>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD background=../img/new_027.jpg height=1></TD>
								</TR>
								<TR>
									<TD class=mainMenu onClick="MenuDisplay('table_2');">
										<SPAN class=span id=table_2Span>＋</SPAN> 文章管理
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=table_2 style="DISPLAY: none" cellSpacing=0
											cellPadding=2 width=155 align=center border=0>
											<TBODY>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/text-edit/index.html" target=main>－ 发布文章</A>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/Article/articleList.action" target=main>－文章列表</A>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/Article/dustbinList.action" target=main>－文章回收站</A>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/Article/toDraftList.action?authorId=${userId}" target=main>－草稿箱</A>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD background="${pageContext.request.contextPath}/img/new_027.jpg" height=1></TD>
								</TR>
								<TR>
									<TD class=mainMenu onClick="MenuDisplay('table_5');">
										<SPAN class=span id=table_5Span>＋</SPAN> 资源管理
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=table_5 style="DISPLAY: none" cellSpacing=0 cellPadding=2 width=155 align=center border=0>
											<TBODY>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/Resource/uploadRes.action" target=main>－新增资源</A></TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/Resource/list.action?resTag=pic" target=main>－图片列表</A>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/Resource/list.action?resTag=mov" target=main>－视频列表</A>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/Resource/toPcList.action" target=main>－图片审核</A>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/Resource/toMcList.action" target=main>－视频审核</A>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD background="${pageContext.request.contextPath}/img/new_027.jpg" height=1></TD>
								</TR>
								<TR>
									<TD class=mainMenu onClick="MenuDisplay('table_3');">
										<SPAN class=span id=table_3Span>＋</SPAN> 权限管理
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=table_3 style="DISPLAY: none" cellSpacing=0 cellPadding=2 width=155 align=center border=0>
											<TBODY>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/welcome.htm" target=main>－ 用户权限管理</A>
													</TD>
												</TR>
												
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD background="${pageContext.request.contextPath}/img/new_027.jpg" height=1></TD>
								</TR>
								<TR>
									<TD class=mainMenu onClick="MenuDisplay('table_4');">
										<SPAN class=span id=table_4Span>＋</SPAN> 统计分析
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=table_4 style="DISPLAY: none" cellSpacing=0 cellPadding=2 width=155 align=center border=0>
											<TBODY>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/welcome.htm" target=main>－文章统计</A>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/welcome.htm" target=main>－资源统计</A>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD background="${pageContext.request.contextPath}/img/new_027.jpg" height=1></TD>
								</TR>
								<TR>
									<TD class=mainMenu onClick="MenuDisplay('table_6');">
										<SPAN class=span id=table_6Span>＋</SPAN>系统管理
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=table_6 style="DISPLAY: none" cellSpacing=0 cellPadding=2 width=155 align=center border=0>
											<TBODY>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/welcome.htm" target=main>－xxx</A>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/welcome.htm" target=main>－xxx</A>
													</TD>
												</TR>
												<TR>
													<TD class=menuSmall>
														<A class=style2 href="${pageContext.request.contextPath}/welcome.htm" target=main>－xxx</A>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath}/img/new_009.jpg"></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath}/img/new_010.jpg" border=0></TD>
					<TD align="center" width=180 background="${pageContext.request.contextPath}/img/new_011.jpg"	height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath}/img/new_012.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
	
</BODY>
</HTML>
