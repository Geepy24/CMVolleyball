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

<link rel="stylesheet" href="resources/css/main_style.css" type="text/css"
	media="screen" />

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid 

<link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
	media="screen" />-->

<!--                       Javascripts                       -->

<!-- jQuery -->

<script type="text/javascript"
	src="resources/scripts/jquery-1.3.2.min.js"></script>

<!-- jQuery Configuration -->

<script type="text/javascript"
	src="resources/scripts/simpla.jquery.configuration.js"></script>

<!-- Facebox jQuery Plugin -->

<script type="text/javascript" src="resources/scripts/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->

<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>

<!-- jQuery Datepicker Plugin 

<script type="text/javascript" src="resources/scripts/jquery.datePicker.js"></script>

<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/UI/resources/js/fill.js"></script>--> 

<script type="text/javascript">
		$(document).ready(function(){
		alert("页面来源"+document.referrer) ;
		ref = document.referrer ;
		
		if(ref.indexOf("comein") != -1){
			$("#column1").html("article") ;
			$("#column2").html("Title") ;
			$("#column3").html("Publish Time") ;
			$("#column4").html("Last Modify") ;
		}
		
		$.ajax({
			type : "GET" ,
			url : "${pageContext.request.contextPath}/Persional/indexArticles.action",
			data : {
				
			}, 
			dataType : "JSON" ,
			success:function(data){
				//alert(data) ;
				//要两次转换才能将传回的json字符串变成json对象
				//alert(typeof(backdata)) ;
				//alert(backdata.artTitle) ;
				var backdata = JSON.parse(data) ;
				var backdata = JSON.parse(backdata) ;
				//alert(JSON.stringify(backdata)) ;
				//遍历
				var i =1 ;
				 for(var item in backdata){
					//alert(backdata[item]) ;
					var num = item ;
					var content = backdata[item].split("###") ; 
					var artTitle = content[0] ;
					var pubTime = content[1] ;
					var lastMod = content[2] ;
					var artId = content[3] ;//artId
					$("#rows").prepend(
							"<tr>"
								+"<td><input type='checkbox' /></td>"
								+"<td>"+i+"</td>"
								+"<td><a href='#' title='title'>"+artTitle+"</a></td>"
								+"<td>"+pubTime+"</td>"
								+"<td>"+lastMod+"</td>"
								+"<td><a href='#' title='Edit'>"
								+"<img src='resources/images/icons/pencil.png' alt='编辑' /></a>"
								+"<a href=''#' title='Delete'><img src='resources/images/icons/cross.png' alt='删除' /></a>"
								+"</td>"
							+"</tr>") ;
					
					i++ ;
				 } 
				
			}
		}) ;
	}); 
	

</script>



</head>

<body>

		<s:debug></s:debug>
		<!-- End #sidebar -->
		<div id="main-content">

			<!-- Main Content Section with everything -->

			 

			<!-- Page Head -->

			<h2>欢迎</h2>

			<p id="page-intro">你想做什么？</p>

			<ul class="shortcut-buttons-set">

				<li><a class="shortcut-button" href="#"><span> <img
							src="resources/images/icons/pencil_48.png" alt="icon" /><br />

							编写文章
					</span></a></li>

				

				<li><a class="shortcut-button" href="#"><span> <img
							src="resources/images/icons/image_add_48.png" alt="icon" /><br />
							上传图片
							
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img
							src="resources/images/icons/clock_48.png" alt="icon" /><br />

							上传视频
					</span></a></li>


			</ul>

			<!-- End .shortcut-buttons-set -->

			<div class="clear"></div>

			<!-- End .clear -->

			<div class="content-box">

				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>内容栏</h3>

					<ul class="content-box-tabs">

						<li><a href="#tab1" class="default-tab">列表</a></li>

						<!-- href must be unique and match the id of target div -->


					</ul>

					<div class="clear"></div>

				</div>

				<!-- End .content-box-header -->
	<!-- 内容栏 -->
				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">

						<!-- This is the target div. id must match the href of this div's tab -->

						<div class="notification attention png_bg">
							<a href="#" class="close"><img
								src="resources/images/icons/cross_grey_small.png"
								title="Close this notification" alt="close" /></a>

						<div>这里是内容栏，你可以查看与修改栏中的内容。点击右上角关闭</div>
							

						</div>

						<table>

							<thead>

								<tr>

									<th><input class="check-all" type="checkbox" /></th>

									<th id="column1">Column 1</th>

									<th id="column2">Column 2</th>

									<th id="column3">Column 3</th>

									<th id="column4">Column 4</th>

									<th  id="column5">编辑选项</th>

								</tr>

							</thead>

							<tfoot>
							
								<tr>

									<td colspan="6">

										<div class="bulk-actions align-left">

											<select name="dropdown">

												<option value="option1">Choose an action...</option>

												<option value="option2">Edit</option>

												<option value="option3">Delete</option>

											</select> <a class="button" href="#">Apply to selected</a>
										</div>
								<!-- 页码选择 -->
										<div class="pagination">
											<a href="#" title="First Page">&laquo; First</a><a href="#"
												title="Previous Page">&laquo; Previous</a> <a href="#"
												class="number" title="1">1</a> <a href="#" class="number"
												title="2">2</a> <a href="#" class="number current" title="3">3</a>
											<a href="#" class="number" title="4">4</a> <a href="#"
												title="Next Page">Next &raquo;</a><a href="#"
												title="Last Page">Last &raquo;</a>
										</div> <!-- End .pagination -->

										<div class="clear"></div>

									</td>

								</tr>

							</tfoot>

							<tbody id="rows">
					<!-- 内容栏中的一行 -->
								 
								

							</tbody>

						</table>

					</div>

					<!-- End #tab1 -->
			

				</div>

				<!-- End .content-box-content -->
			</div>

			<!-- End .content-box -->

			<div id="footer">
				<small> <!-- Remove this notice or replace it with whatever you want -->

					&#169; Copyright 2010 Your Company | Powered by <a href="#">admin
						templates</a> | <a href="#">Top</a>
				</small>
			</div>

			<!-- End #footer -->

		</div>

		<!-- End #main-content -->

	</div>

</body>

<!-- Download From www.exet.tk-->

</html>

