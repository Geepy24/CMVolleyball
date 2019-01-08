$(document).ready(article) ; //页面开始加载文章
$(document).ready(getUser) ; //页面开始加载用户
$(document).ready(toFlag) ;	//页面开始判断前往指定状态

function toFlag(){
	var url = window.location.href ; //得到当前url
	//alert(url) ;
	if(url.indexOf("goaddpic") != -1){
		resource('pic') ;
	}
	if(url.indexOf("goaddmov") != -1){
		resource('mov') ;
	}
	
	
}
function getUser(){
	$.ajax({
		url : "../Persional/sendUser.action",
		type : "get",
		success: function(data){
			$("#userName").text(data) ;
		}
	}) ;
}
//----------------------------------文章列表----------------------------------
//每次点击要把页码置为1	
function article(){
		//alert("页面来源"+document.referrer) ;
			$("#tab2").hide() ;
			$("#tab3").hide() ;
			$("#tab1").show() ;
			$("#ul1 a").removeClass("current") ;
			$("#art").addClass("current") ;
			$("#column1").html("article") ;
			$("#column2").html("Title") ;
			$("#column3").html("Publish Time") ;
			$("#column4").html("Last Modify") ;
			$("#rows").html("") ;		
		$.ajax({
			type : "GET" ,
			url : "../Persional/indexArticles.action",
			data : {
				
			}, 
			dataType : "JSON" ,
			success:function(data){
				var data = JSON.parse(data) ;
				articleContent(data) ;
				$("#n1").html(1) ;
				
			}
		}) ;
	}
	
	//----------------------------------回收站列表----------------------------------
	function dustbin(){
			$("#tab2").hide() ;
			$("#tab3").hide() ;
			$("#tab1").show() ;
			//alert("回收站") ;
			//alert(document.referrer) ;
			$("#ul1 a").removeClass("current") ;
			$("#dust").addClass("current") ;
			$("#column1").html("dustbin") ;
			$("#column2").html("Title") ;
			$("#column3").html("Delete Time") ;
			$("#column4").hide() ;
			$("#rows").html("") ;
			$.ajax({
				type : "GET" ,
				url : "../Persional/dustList.action",
				data : {
					
				}, 
				dataType : "JSON" ,
				success:function(data){
					//alert(data) ;
					//要两次转换才能将传回的json字符串变成json对象
					var data = JSON.parse(data) ;
					dustbinContent(data) ;
					$("#n1").html(1) ;

					
				}
			}) ;
	
	}
	
	
	//----------------------------------草稿箱列表----------------------------------
	
	function draft(){
		
			//alert("草稿箱") ;
			
				$("#tab2").hide() ;
				$("#tab3").hide() ;
				$("#tab1").show() ;
				$("#ul1 a").removeClass("current") ;
				$("#dra").addClass("current") ;
				
				$("#column1").html("draft") ;
				$("#column2").html("Title") ;
				$("#column3").html("Last Modify") ;
				$("#column4").hide() ;
				$("#rows").html("") ;
			
			$.ajax({
				type : "GET" ,
				url : "../Persional/draList.action",
				data : {
					
				}, 
				dataType : "JSON" ,
				success:function(data){
					//alert(data) ;
					//要两次转换才能将传回的json字符串变成json对象
					var data = JSON.parse(data) ;
					draftContent(data)
					$("#n1").html(1) ;

				}
			}) ;
	
	} 	
	function edit(){
		$("#tab1").hide() ;
		$("#tab3").hide() ;
		$("#tab2").show() ;
		$("#ul1 a").removeClass("current") ;
		$("#editArt").addClass("current") ;
		
		
	}
	//----------------------------------上传资源----------------------------------
	function resource(data){
		$("#tab1").hide() ;
		$("#tab2").hide() ;
		$("#tab3").show() ;
		
		$("#resTag").val(data) ;
		
		if(data == "pic"){
			$("#t").text("上传图片") ;
		}
		if(data == "mov"){
			$("#t").text("上传视频") ;
		}
		//alert($("#resTag").val() );
	}
	
	
	
	//----------------------------------填充相册列表----------------------------------
	function photos(){
		$("#tab2").hide() ;
		$("#tab3").hide() ;
		$("#tab1").show() ;
		
		$("#column1").html("photos") ;
		$("#column2").html("Descript") ;
		$("#column3").html("Publish Time") ;
		$("#column4").hide() ;
		$("#rows").html("") ;
		
		
		//查找的表是resource表
		$.ajax({
			type : "GET" ,
			url : "../Persional/picList.action",
			data : {
				
			}, 
			dataType : "JSON" ,
			success:function(data){
				//要两次转换才能将传回的json字符串变成json对象
				var data = JSON.parse(data) ;
				photosContent(data) ;
				$("#n1").html(1) ;

			}
		}) ;
		
	}
	//----------------------------------填充视频列表----------------------------------
	function movies(){
		$("#tab2").hide() ;
		$("#tab3").hide() ;
		$("#tab1").show() ;
		
		$("#column1").html("movies") ;
		$("#column2").html("Descript") ;
		$("#column3").html("Publish Time") ;
		$("#column4").hide() ;
		$("#rows").html("") ;
		
		
		//查找的表是resource表
		$.ajax({
			type : "GET" ,
			url : "../Persional/movList.action",
			data : {
				
			}, 
			dataType : "JSON" ,
			success:function(data){
				//要两次转换才能将传回的json字符串变成json对象
				var data = JSON.parse(data) ;
				moviesContent(data) ;
				$("#n1").html(1) ;
				
			}
		}) ;	
	}
	
	//----------------------------------审核视频列表----------------------------------	
		function movieChecks(){
			$("#tab2").hide() ;
			$("#tab3").hide() ;
			$("#tab1").show() ;
			
			$("#column1").html("MCs") ;
			$("#column2").html("Tag") ; //审核标记
			$("#column3").html("Comment") ; //审核注释
			$("#column4").hide() ;
			$("#rows").html("") ;
			
			
			$.ajax({
				type : "GET" ,
				url : "../Persional/userMcList.action",
				data : {
					
				}, 
				dataType : "JSON" ,
				success:function(data){
					//要两次转换才能将传回的json字符串变成json对象
					var data = JSON.parse(data) ;
					mcsContent(data) ;
					$("#n1").html(1) ;
					
				}
			}) ;
	
		
		}		
	//----------------------------------审核图片列表----------------------------------	
		function photoChecks(){
			$("#tab2").hide() ;
			$("#tab3").hide() ;
			$("#tab1").show() ;
			
			$("#column1").html("PCs") ;
			$("#column2").html("Tag") ;
			$("#column3").html("Comment") ;
			$("#column4").hide() ;
			$("#rows").html("") ;
			
			
			$.ajax({
				type : "GET" ,
				url : "../Persional/userPcList.action",
				data : {
					
				}, 
				dataType : "JSON" ,
				success:function(data){
					//要两次转换才能将传回的json字符串变成json对象
					var data = JSON.parse(data) ;
					pcsContent(data) ;
					$("#n1").html(1) ;
					
				}
			}) ;
			
		}		
		
//--------------------根据不同的点击内容改变不同的表格样式和内容填充(用于ajax的success)--------------------------
function articleContent(data){
	var backdata = JSON.parse(data) ;
	$("#rows").html("") ;
	
	var i =1 ;
	 for(var item in backdata){
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
					+"<td><a href='javascript:void(0)' onclick='reEdit("+artId+")' title='Edit'>"
					+"<img src='../UI2/resources/images/icons/pencil.png' alt='编辑' /></a>"
					+"<a href='javascript:void(0)' onclick='deleteArticle("+artId+")' title='Delete'><img src='../UI2/resources/images/icons/cross.png' alt='删除' /></a>"
					+"</td>"
				+"</tr>") ;
		
			i++ ;
	 	} 
}
function draftContent(data){
	
	var backdata = JSON.parse(data) ;
	//alert(JSON.stringify(backdata)) ;
	//遍历
	var i =1 ;
	 for(var item in backdata){
		//alert(backdata[item]) ;
		//var num = item ;
		var content = backdata[item].split("###") ; 
		var artTitle = content[0] ;
		var lastMod = content[1] ;
		var draId = content[2] ; //id在后面会有用
		$("#rows").prepend(
				"<tr>"
					+"<td><input type='checkbox' /></td>"
					+"<td>"+i+"</td>"
					+"<td><a href='javascript:void(0)' onclick='reEdit("+draId+")' title='title'>"+artTitle+"</a></td>"
					+"<td>"+lastMod+"</td>"
					+"<td><a href='javascript:void(0)' onclick='reEdit("+draId+")' title='Edit'>"
					+"<img src='../UI2/resources/images/icons/pencil.png' alt='编辑' /></a>"
					+"<a href='javascript:void(0)' onclick='deleteDraft("+draId+")' title='Delete'><img src='../UI2/resources/images/icons/cross.png' alt='删除' /></a>"
					+"</td>"
				+"</tr>") ;
		
		i++ ;
	 } 
				
}
function dustbinContent(data){
	var backdata = JSON.parse(data) ;
	//alert(JSON.stringify(backdata)) ;
	//遍历
	var i =1 ;
	 for(var item in backdata){
		//alert(backdata[item]) ;
		//var num = item ;
		var content = backdata[item].split("###") ; 
		var artTitle = content[0] ;
		var delTime = content[1] ;
		var dustId = content[2] ; //id在后面会有用
		$("#rows").prepend(
				"<tr>"
					+"<td><input type='checkbox' /></td>"
					+"<td>"+i+"</td>"
					+"<td><a href='javascript:void(0)' onclick='reEdit("+dustId+")' title='title'>"+artTitle+"</a></td>"
					+"<td>"+delTime+"</td>"
					+"<td><a href='javascript:void(0)' onclick='reEdit("+dustId+")' title='Edit'>"
					+"<img src='../UI2/resources/images/icons/pencil.png' alt='编辑' /></a>"
					+"<a href='javascript:void(0)' onclick='deleteDustbin("+dustId+")' title='Delete'><img src='../UI2/resources/images/icons/cross.png' alt='删除' /></a>"
					+"</td>"
				+"</tr>") ;
		
		i++ ;
	 } 
	
}
function photosContent(data){
	var backdata = JSON.parse(data) ;
	//alert(JSON.stringify(backdata)) ;
	
	//遍历
	var i =1 ;
	 for(var item in backdata){
		//alert(backdata[item]) ;
		//var num = item ;
		var content = backdata[item].split("###") ; 
		var picName = content[0]
		var resCom = content[1] ;
		var pubTime= content[2] ;
		var resId = content[3] ; //id在后面会有用
		$("#rows").prepend(
				"<tr>"
					+"<td><input type='checkbox' /></td>"
					+"<td><a href='javascript:void(0)' onclick='' title='title'><img style='width:50px;height:50px' src='/pic/"+picName+"' /></a></td>"
					+"<td>"+resCom+"</td>"
					+"<td>"+pubTime+"</td>"
					+"<td><a href='javascript:void(0)' onclick='' title='Edit'>"
					+"<img src='../UI2/resources/images/icons/pencil.png' alt='编辑' /></a>"
					+"<a href='javascript:void(0)' onclick='' title='Delete'><img src='../UI2/resources/images/icons/cross.png' alt='删除' /></a>"
					+"</td>"
				+"</tr>") ;
		
		i++ ;
	 } 
		
}
function moviesContent(data){
	var backdata = JSON.parse(data) ;
	//alert(JSON.stringify(backdata)) ;
	//遍历
	var i =1 ;
	 for(var item in backdata){
		//alert(backdata[item]) ;
		//var num = item ;
		var content = backdata[item].split("###") ; 
		var  mpName = content[0] ;
		var resCom = content[1] ;
		var pubTime= content[2] ;
		var resId = content[3] ; //id在后面会有用
		$("#rows").prepend(
				"<tr>"
					+"<td><input type='checkbox' /></td>"
					+"<td><a href='javascript:void(0)' onclick='' title='title'><img style='width:50px;height:50px' src='/movpre/"+mpName+"' /></a></td>"
					+"<td>"+resCom+"</td>"
					+"<td>"+pubTime+"</td>"
					+"<td><a href='javascript:void(0)' onclick='' title='Edit'>"
					+"<img src='../UI2/resources/images/icons/pencil.png' alt='编辑' /></a>"
					+"<a href='javascript:void(0)' onclick='' title='Delete'><img src='../UI2/resources/images/icons/cross.png' alt='删除' /></a>"
					+"</td>"
				+"</tr>") ;
		
		i++ ;
	 } 
	
}
function pcsContent(data){
	var backdata = JSON.parse(data) ;
	//alert(JSON.stringify(backdata)) ;
	//遍历
	var i =1 ;
	 for(var item in backdata){
		//alert(backdata[item]) ;
		//var num = item ;
		var content = backdata[item].split("###") ; 
		var picName = content[0]
		var checkTag= content[1] ;
		var checkCom= content[2] ;
		var resId = content[3] ; //id在后面会有用
		if(checkTag == "0"){
			checkTag = "等待审核" ;
		}
		if(checkTag == "-1"){
			checkTag = "审核不通过" ;
		}
		if(checkTag == "1"){
			checkTag = "审核通过" ;
		}
		$("#rows").prepend(
				"<tr>"
					+"<td><input type='checkbox' /></td>"
					+"<td><a href='javascript:void(0)' onclick='' title='title'><img style='width:50px;height:50px' src='/pic/"+picName+"' /></a></td>"
					+"<td>"+checkTag+"</td>"
					+"<td>"+checkCom+"</td>"
					+"<td><a href='javascript:void(0)' onclick='' title='Edit'>"
					+"<img src='../UI2/resources/images/icons/pencil.png' alt='编辑' /></a>"
					+"<a href='javascript:void(0)' onclick='' title='Delete'><img src='../UI2/resources/images/icons/cross.png' alt='删除' /></a>"
					+"</td>"
				+"</tr>") ;
		
		i++ ;
	 } 
	
}
function mcsContent(data){
	var backdata = JSON.parse(data) ;
	//alert(JSON.stringify(backdata)) ;
	//遍历
	var i =1 ;
	 for(var item in backdata){
		//alert(backdata[item]) ;
		//var num = item ;
		var content = backdata[item].split("###") ; 
		var  mpName = content[0] ;
		var checkTag= content[1] ;
		var checkCom= content[2] ;
		var resId = content[3] ; //id在后面会有用
		if(checkTag == "0"){
			checkTag = "等待审核" ;
		}
		if(checkTag == "-1"){
			checkTag = "审核不通过" ;
		}
		if(checkTag == "1"){
			checkTag = "审核通过" ;
		}
		$("#rows").prepend(
				"<tr>"
					+"<td><input type='checkbox' /></td>"
					+"<td><a href='javascript:void(0)' onclick='' title='title'><img style='width:50px;height:50px' src='/movpre/"+mpName+"' /></a></td>"
					+"<td>"+checkTag+"</td>"
					+"<td>"+checkCom+"</td>"
					+"<td><a href='javascript:void(0)' onclick='' title='Edit'>"
					+"<img src='../UI2/resources/images/icons/pencil.png' alt='编辑' /></a>"
					+"<a href='javascript:void(0)' onclick='' title='Delete'><img src='../UI2/resources/images/icons/cross.png' alt='删除' /></a>"
					+"</td>"
				+"</tr>") ;
		
		i++ ;
	 } 
	
			
}









