$("#btn1").click(function(){
  		alert("发布文章") ;
  		//发布的时间
  		var date = new Date() ;
  		var year = date.getFullYear() ;
  		var month = date.getMonth()+1 ;
  		var day = date.getDate() ;
  		
  		var now = year + "-" + month + "-" + day ;
  		
  		//获取文本编辑器中的内容
  		var article_content = $('#edit')[0].childNodes[1].innerHTML ;
  		var article_title = $("#title").text() ;
  		if(article_content == "" || article_title == ""){
  			alert("文章标题与内容不能为空");
  			return ;
  		}
  		
  		//json对象
  		var content = {
  				"artTitle" : article_title ,
  				"pubTime"  : now ,
  				"artContent" : article_content
  		} ;
  		alert(JSON.stringify(content)) ;
  		
  		$.ajax({
			//注意url的写法
			url : '../Article/articleHndler.action' ,
			type : 'post',
			data : content,
			dataType : 'json',
			success:function(data){
				alert(JSON.stringify(data)) ;
				//取出保存到数据库后返回的文章ID
				var article_id = data.artId ;
				alert("发布成功") ;
				alert(article_id) ;
				//用get方式直接给另一个页面传文章id
				window.location = "../pubSuccess.jsp?artId="+article_id;
				
				}
			});
  		
  		
}) ;

$("#btn2").click(function(){
	alert("临时保存") ;
}) ;