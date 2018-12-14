<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<HEAD id=Head1>
<TITLE>顶部</TITLE>
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
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}

TD {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}

DIV {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}

P {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
</STYLE>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>

	<FORM id=form1 name=form1 action="" method=post>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
			<TBODY>
				<TR>
					<TD width=10><IMG src="../img/new_001.jpg" border=0></TD>
					<TD background=../img/new_002.jpg><FONT size=5><B>文章资源管理系统</B></FONT></TD>
					<TD background=../img/new_002.jpg>
						<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
							<TBODY>
								<TR>
									<TD align=right height=35></TD>
								</TR>
								<TR>
									<TD height=35 align="right">
										<span>当前用户：${loginInfo.userName}</span>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<A href="#" target=_top><FONT color=red>修改密码</FONT></A>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<A href="${pageContext.request.contextPath}/index.jsp" target=_top><FONT color=red>返回首页</FONT></A>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=10><IMG src="../img/new_003.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
