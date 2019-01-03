
//页面加载时根据页面来源往内容栏中填充内容

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