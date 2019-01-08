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
			//alert(JSON.stringify(content)) ;
			
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
			dustbin();
		}
	//删除回收站
		function deleteDustbin(dustId){
			var content = {
					"artId" :	dustId 
			} ;
			//alert(JSON.stringify(content)) ;
			$.ajax({
			//注意url的写法
			url : '../Article/delDustbin.action' ,
			type : 'post',
			data : content,
			dataType : 'json',
			//async:false,
			success:function(data){
				dustbin();
			}
			});
			
		}
	//删除草稿，不进入回收站
		function deleteDraft(draId){
			var content = {
					"artId" : draId 
			} ;
			//alert(JSON.stringify(content)) ;
			$.ajax({
			//注意url的写法
			url : '../Article/delDraft.action' ,
			type : 'post',
			data : content,
			dataType : 'json',
			//async:false,
			success:function(data){
				draft() ;
				}
			});
			
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
			//alert(flag) ;
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
						if(flag == "article"){
							articleContent(data) ;
						}
						if(flag == "draft"){
							draftContent(data) ;
						}
						if(flag == "dustbin"){
							dustbinContent(data) ;
						}
						if(flag == "photos"){
							photosContent(data) ;
						}
						if(flag == "movies"){
							moviesContent(data) ;
						}
						if(flag == "PCs"){
							pcsContent(data) ;
						}
						if(flag == "MCs"){
							mcsContent(data) ;
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
					//var backdata = JSON.parse(data) ;
					$("#rows").html("") ;
					var nowPage = parseInt($("#n1").text())+1 ;
					$("#n1").html(nowPage) ;
					if(flag == "article"){
						articleContent(data) ;
					}
					if(flag == "draft"){
						draftContent(data) ;
					}
					if(flag == "dustbin"){
						dustbinContent(data) ;
					}
					if(flag == "photos"){
						photosContent(data) ;
					}
					if(flag == "movies"){
						moviesContent(data) ;
					}
					if(flag == "PCs"){
						pcsContent(data) ;
					}
					if(flag == "MCs"){
						mcsContent(data) ;
					}
					
					
//					var i =1 ;
//					 for(var item in backdata){
//						var num = item ;
//						var content = backdata[item].split("###") ; 
//						var artTitle = content[0] ;
//						var pubTime = content[1] ;
//						var lastMod = content[2] ;
//						var artId = content[3] ;//artId
//						$("#rows").prepend(
//								"<tr>"
//									+"<td><input type='checkbox' /></td>"
//									+"<td>"+i+"</td>"
//									+"<td><a href='#' title='title'>"+artTitle+"</a></td>"
//									+"<td>"+pubTime+"</td>"
//									+"<td>"+lastMod+"</td>"
//									+"<td><a href='javascript:void(0)' onclick='reEdit("+artId+")' title='Edit'>"
//									+"<img src='../UI2/resources/images/icons/pencil.png' alt='编辑' /></a>"
//									+"<a href='javascript:void(0)' onclick='deleteArticle("+artId+")' title='Delete'><img src='../UI2/resources/images/icons/cross.png' alt='删除' /></a>"
//									+"</td>"
//								+"</tr>") ;
//						
//							i++ ;
//					 	} 
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
					
					if(flag == "article"){
						articleContent(data) ;
					}
					if(flag == "draft"){
						draftContent(data) ;
					}
					if(flag == "dustbin"){
						dustbinContent(data) ;
					}
					if(flag == "photos"){
						photosContent(data) ;
					}
					if(flag == "movies"){
						moviesContent(data) ;
					}
					if(flag == "PCs"){
						pcsContent(data) ;
					}
					if(flag == "MCs"){
						mcsContent(data) ;
					}
				}
			}) ;
			
		}

		
