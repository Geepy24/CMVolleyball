$(document).ready(article) ;
$(document).ready(getUser) ;

function getUser(){
	$.ajax({
		url : "../Persional/sendUser.action",
		type : "get",
		success: function(data){
			$("#userName").text(data) ;
		}
	}) ;
}

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
			$("#n1").html("1") ;
		
		$.ajax({
			type : "GET" ,
			url : "../Persional/indexArticles.action",
			data : {
				
			}, 
			dataType : "JSON" ,
			success:function(data){
				//alert(data) ;
				//要两次转换才能将传回的json字符串变成json对象
				//alert(typeof(backdata)) ;
				//alert(backdata.artTitle) ;
				$("#rows").html(1) ;
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
								+"<td><a href='../Article/showArticle?artId="+artId+"' title='title'>"+artTitle+"</a></td>"
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
		}) ;
	}
	
	
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
				$("#n1").html(1) ;
			$.ajax({
				type : "GET" ,
				url : "../Persional/dustList.action",
				data : {
					
				}, 
				dataType : "JSON" ,
				success:function(data){
					//alert(data) ;
					//要两次转换才能将传回的json字符串变成json对象
					//alert(typeof(backdata)) ;
					//alert(backdata.artTitle) ;
					$("#rows").html("") ;
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
			}) ;
	
	}
	
	
	
	
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
				$("#n1").html(1) ;
			
			$.ajax({
				type : "GET" ,
				url : "../Persional/draList.action",
				data : {
					
				}, 
				dataType : "JSON" ,
				success:function(data){
					//alert(data) ;
					//要两次转换才能将传回的json字符串变成json对象
					//alert(typeof(backdata)) ;
					//alert(backdata.artTitle) ;
					$("#rows").html("") ;
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
			}) ;
	
	} 	
	function edit(){
		$("#tab1").hide() ;
		$("#tab3").hide() ;
		$("#tab2").show() ;
		$("#ul1 a").removeClass("current") ;
		$("#editArt").addClass("current") ;
		
		
	}
	//上传资源
	function resource(data){
		$("#tab1").hide() ;
		$("#tab2").hide() ;
		$("#tab3").show() ;
		
		$("#resTag").val(data) ;
		//alert($("#resTag").val() );
	}