<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员页面</title>

<!--                       CSS                       -->

<!-- Reset Stylesheet -->

<link rel="stylesheet" href="resources/css/reset.css" type="text/css"
	media="screen" />

<!-- Main Stylesheet -->

<link rel="stylesheet" href="resources/css/menu_style.css" type="text/css"
	media="screen" />

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->

<link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
	media="screen" />

<!--                       Javascripts                       -->

<!-- jQuery -->

<script type="text/javascript"
	src="resources/scripts/jquery-1.3.2.min.js"></script>

<!-- jQuery Configuration-->

<script type="text/javascript"
	src="resources/scripts/simpla.jquery.configuration.js"></script> 

<!-- Facebox jQuery Plugin -->

<script type="text/javascript" src="resources/scripts/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->

<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>

<!-- jQuery Datepicker Plugin 

<script type="text/javascript" src="resources/scripts/jquery.datePicker.js"></script>

<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
</head>

<body>

	<div id="body-wrapper">

		<!-- Wrapper for the radial gradient background -->

		<div id="sidebar">

			<div id="sidebar-wrapper">

				<!-- Sidebar with logo and menu -->

				<h1 id="sidebar-title">
					<a href="#">个人空间</a>
				</h1>

				<!-- Logo (221px wide) -->

				<a href="#"><img id="logo" src="resources/images/logo.png"
					alt="Simpla Admin logo" /></a>

				<!-- Sidebar Profile links -->

				<div id="profile-links">
					您好, <a href="#" title="Edit your profile">${userName }</a><br /> <br /> <a
						href="#" title="View the Site">返回网站首页</a> | <a href="#"
						title="Sign Out">登出</a>
				</div>

				<ul id="main-nav">

					<!-- Accordion Menu -->

					<li><a href="#/" class="nav-top-item no-submenu"> <!-- Add the class "no-submenu" to menu items with no sub menu -->

							导航
					</a></li>

					<li><a href="#" class="nav-top-item current"> <!-- Add the class "current" to current menu item -->

							文章
					</a>

						<ul>

							<li><a href="${pageContext.request.contextPath }/Persional/userAddArt.action" target=main>编写文章</a></li>

							<li><a class="current" href="${pageContext.request.contextPath }/Persional/comein.action" >管理文章</a></li>

							<!-- Add class "current" to sub menu items also -->

							<li><a href="${pageContext.request.contextPath }/Persional/toDraftList.action" target=main>草稿箱</a></li>

							<li><a href="#">回收站</a></li>

						</ul></li>

					<!-- <li> <a href="#" class="nav-top-item"> Pages </a>

          <ul>

            <li><a href="#">Create a new Page</a></li>

            <li><a href="#">Manage Pages</a></li>

          </ul>

        </li> -->

					<li><a href="#" class="nav-top-item"> 图库 </a>

						<ul>

							<li><a href="#">上传图片</a></li>

							<li><a href="#">图片管理</a></li>

							<li><a href="#">审核列表</a></li>

							<!--  <li><a href="#">Gallery Settings</a></li> -->

						</ul></li>

					<li><a href="#" class="nav-top-item"> 视频 </a>

						<ul>

							<li><a href="#">上传视频</a></li>

							<li><a href="#">视频管理</a></li>

							<li><a href="#">审核列列表</a></li>

						</ul></li>

					<li><a href="#" class="nav-top-item"> 设置 </a>

						<ul>

							<li><a href="#">个人资料</a></li>

							<li><a href="#">个人资源总览</a></li>

							<li><a href="#">用户权限</a></li>

							<!-- <li><a href="#">Users and Permissions</a></li> -->

						</ul></li>

				</ul>

				</div>

			</div>

		</div>

		<!-- End #sidebar -->

</body>

<!-- Download From www.exet.tk-->

</html>

