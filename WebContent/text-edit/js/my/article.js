$("#btn1").click(function(){
  		alert("发布文章") ;
  		//发布的时间
  		var date = new Date() ;
  		var year = date.getFullYear() ;
  		var month = date.getMonth()+1 ;
  		var day = date.getDate() ;
  		
  		var now = year + "-" + month + "-" + day ;
  		
  		//获取文本编辑器中的内容
  		var article_main = $('#edit')[0].childNodes[1].innerHTML ;
  		//json对象
  		var content = {
  				"articleTitle" : $("#title").text() ,
  				"publishTime"  : now ,
  				"articleMain" : article_main
  		} ;
  		alert(JSON.stringify(content)) ;
  		
  		$.ajax({
			//注意url的写法
			url : '../Article/articleHndler.action' ,
			type : 'post',
			data : content,
			dataType : 'json',
			success:function(data){
				var uri = JSON.stringify(data.artUri);
				alert(uri) ;
				
				}
			});
  		
  		
}) ;

$("#btn2").click(function(){
	alert("临时保存") ;
}) ;