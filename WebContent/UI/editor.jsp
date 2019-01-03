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

<link rel="stylesheet" href="${pageContext.request.contextPath }/UI/resources/css/reset.css" type="text/css"
	media="screen" />

<!-- Main Stylesheet -->

<link rel="stylesheet" href="${pageContext.request.contextPath }/UI/resources/css/main_style.css" type="text/css"
	media="screen" />

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid 

<link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
	media="screen" />-->

<!--                       Javascripts                       -->

<!-- jQuery -->

<script type="text/javascript"
	src="${pageContext.request.contextPath }/UI/resources/scripts/jquery-1.3.2.min.js"></script>

<!-- jQuery Configuration -->

<script type="text/javascript"
	src="${pageContext.request.contextPath }/UI/resources/scripts/simpla.jquery.configuration.js"></script>

<!-- Facebox jQuery Plugin -->

<script type="text/javascript" src="${pageContext.request.contextPath }/UI/resources/scripts/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->

<script type="text/javascript" src="${pageContext.request.contextPath }/UI/resources/scripts/jquery.wysiwyg.js"></script>

<!-- jQuery Datepicker Plugin 

<script type="text/javascript" src="resources/scripts/jquery.datePicker.js"></script>

<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/UI/resource/js/fill.js"></script>--> 

<!-- <script type="text/javascript">
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
				var i =10 ;
				 for(var item in backdata){
					//alert(backdata[item]) ;
					var num = item ;
					var content = backdata[item].split("###") ; 
					var artTitle = content[0] ;
					var pubTime = content[1] ;
					var lastMod = content[2] ;
					
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
					
					i-- ;
				 } 
				
			}
		}) ;
	}); 
	 

</script>
-->


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
							src="${pageContext.request.contextPath }/UI/resources/images/icons/pencil_48.png" alt="icon" /><br />

							编写文章
					</span></a></li>

				

				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath }/UI/resources/images/icons/image_add_48.png" alt="icon" /><br />
							上传图片
							
					</span></a></li>

				<li><a class="shortcut-button" href="#"><span> <img
							src="${pageContext.request.contextPath }/UI/resources/images/icons/clock_48.png" alt="icon" /><br />

							上传视频
					</span></a></li>


			</ul>

			<!-- End .shortcut-buttons-set -->

			<div class="clear"></div>

			<!-- End .clear -->

			<div class="content-box">

				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>编辑器</h3>

					<ul class="content-box-tabs">


						<!-- href must be unique and match the id of target div -->

						<li><a href="#tab2" class="default-tab">编辑</a></li>

					</ul>

					<div class="clear"></div>

				</div>

				<!-- End .content-box-header -->
	<!-- 内容栏 -->
				<div class="content-box-content">

					

					<!-- End #tab1 -->
			<!-- 新增栏，文本框 -->
					<div class="tab" id="tab">


							<fieldset>

								<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns 

								<p>

									<label>Small form input</label> <input
										class="text-input small-input" type="text" id="small-input"
										name="small-input" /> 
										<!-- 正确提示 
										<span class="input-notification success png_bg">Successful message</span>

									<!-- Classes for input-notification: success, error, information, attention

									<br /> <small>A small description of the field</small>
								</p>
								-->
								
								<p>
	
									<label>文章标题</label> 
									<input class="text-input medium-input datepicker" type="text"
										id="medium-input" name="medium-input" /> 
									
										<!-- 错误提示 
										<span class="input-notification error png_bg">Error message</span> -->
								</p>

								<!-- <p>

									<label>Large form input</label> <input
										class="text-input large-input" type="text" id="large-input"
										name="large-input" />

								</p> -->

								<!-- <p>

									<label>Checkboxes</label> <input type="checkbox"
										name="checkbox1" /> This is a checkbox <input type="checkbox"
										name="checkbox2" /> And this is another checkbox
								</p> -->

								<!-- <p>

									<label>Radio buttons</label> <input type="radio" name="radio1" />

									This is a radio button<br /> <input type="radio" name="radio2" />

									This is another radio button
								</p> -->

								<%-- <p>

									<label>This is a drop down list</label> <select name="dropdown"
										class="small-input">

										<option value="option1">Option 1</option>

										<option value="option2">Option 2</option>

										<option value="option3">Option 3</option>

										<option value="option4">Option 4</option>

									</select>

								</p> --%>
								<p>
									<label>文章正文</label>
								</p>

								<div id="editor">

								</div>

								    

								<p>

									<input class="button" type="button" id="btn1" value="Submit" />
									<input class="button" type="button" id="btn2" value="Save" />
									<!-- 创建编辑器，控制发布与保存  -->
									<script type="text/javascript" src="${pageContext.request.contextPath }/UI/resources/js/wangEditor.min.js"></script>
									<script type="text/javascript" src="${pageContext.request.contextPath }/UI/resources/js/userArticle.js"></script>
									
									
									
								</p>
								
								
								    
								
									
							</fieldset>

							<div class="clear"></div>

							<!-- End .clear -->


					</div>

					<!-- End #tab2 -->

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

