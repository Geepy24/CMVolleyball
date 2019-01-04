		//删除文章
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
				//外链的js不能用el表达式
				url : "../Article/deleteArticle.action" ,
				type : "post" ,
				data : content ,
				dataType : "json",
				success : function(data){
					alert("删除成功！您可以前往回收站查看");
					//window.location = "${pageContext.request.contextPath}/Article/articleList.action" ;
				}
			}) ;
		}
		
		
//---------------------------处理页码事件--------------------------------------
		//获取总页码
		//使用flag作为标记，#column1的内容是article或者draft或者dustbin
		//但是如果页面被自动翻译就gg
		function pages(){
			var flag = $("#column1").text() ;
			
		//	alert(flag) ;
			var content = {
					"pageRef"	  :	flag 
			} ;
			$.ajax({
				url : "../Persional/pages.action" ,
				type : "get" ,
				data : content ,
				dataType : "json",
				success : function(data){
					var pages = parseInt(data) ;
					$("#selectPage").html("") ;
					for(i = 1 ; i <= pages ; i++){
						$("#selectPage").append(
								"<option value="+i+">"+i+"</option>"
						) ;
					}
				}
				
			}) ;
			
		}
		
	
		
		
		
		//上一页
		function prePage(){
			
			var flag = $("#column1").text() ;
			var content = {
					"pageRef"	  :	flag 
			} ;
			
			$.ajax({
				
				url : "../Persional/prePage.action" ,
				type : "post" ,
				data : content ,
				dataType : "json",
				success : function(data){
					
					
					if(data.indexOf("error") != -1){
						alert(data) ;
						return ;
					}else{
						var backdata = JSON.parse(data) ;
						$("#rows").html("") ;
						var nowPage = parseInt($("#n1").text())-1 ;
						$("#n1").html(nowPage) ;
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
									+"<td><a href='#' title='Edit'>"
									+"<img src='../UI/resources/images/icons/pencil.png' alt='编辑' /></a>"
									+"<a href='javascript:void(0)' onclick='deleteArticle("+artId+")' title='Delete'><img src='../UI/resources/images/icons/cross.png' alt='删除' /></a>"
									+"</td>"
								+"</tr>") ;
						
							i++ ;	
					 	} 
					  }
					}
				});
			}
		
		//	下一页
		function nextPage(){
			//alert("当前页数"+currentPage) ;
			
			var flag = $("#column1").text() ;
			var content = {
					"pageRef"	  :	flag
			} ;
			
			$.ajax({
				url : "../Persional/nPage.action" ,
				type : "post" ,
				data : content ,
				dataType : "json",
				success : function(data){
					
				if(data.indexOf("error") != -1){
						alert(data) ;
						return ;
				}else{
					var backdata = JSON.parse(data) ;
					$("#rows").html("") ;
					var nowPage = parseInt($("#n1").text())+1 ;
					$("#n1").html(nowPage) ;
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
									+"<td><a href='#' title='Edit'>"
									+"<img src='../UI/resources/images/icons/pencil.png' alt='编辑' /></a>"
									+"<a href='javascript:void(0)' onclick='deleteArticle("+artId+")' title='Delete'><img src='../UI/resources/images/icons/cross.png' alt='删除' /></a>"
									+"</td>"
								+"</tr>") ;
						
							i++ ;
					 	} 
					}
				}
			});
		}
		
		
		
		//前往某页
		function toPage(){
			
			var option=$("#selectPage option:selected"); //获取选中的项
			var page = option.val() ;
			var flag = $("#column1").text() ;
			var content = {
					"toPage" : page ,	
					"pageRef" : flag 
			}
			
			
			$.ajax({
				url : "../Persional/sPage.action" ,
				type : "post" ,
				data : content ,
				dataType : "json",
				success : function(data){
					var backdata = JSON.parse(data) ;
					$("#rows").html("") ;
					$("#n1").html(page) ;
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
								+"<td><a href='#' title='Edit'>"
								+"<img src='../UI/resources/images/icons/pencil.png' alt='编辑' /></a>"
								+"<a href='javascript:void(0)' onclick='deleteArticle("+artId+")' title='Delete'><img src='../UI/resources/images/icons/cross.png' alt='删除' /></a>"
								+"</td>"
							+"</tr>") ;
					
						i++ ;	
				 	} 
				}
			}) ;
			
		}
		
	
		
		
		
