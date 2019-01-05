//全局
	//当前时间now
	var date = new Date() ;
	var year = date.getFullYear() ;
	var month = date.getMonth()+1 ;
	var day = date.getDate() ;
	var now = year + "-" + month + "-" + day ;
//创建编辑器
	var E = window.wangEditor ;
	var editor = new E('#editor') ;
	// 或者 var editor = new E( document.getElementById('editor') )
	editor.create() ;
	
//点击事件-发布
	document.getElementById('btn1').addEventListener('click',publish , false);
   
//点击事件-保存  
	document.getElementById('btn2').addEventListener('click',saveAsDraft, false) ;

    //发布文章
	function publish(){
		
			//读取标题与编辑器中的html 
			var article_title = $('#medium-input').val() ;
			var article_content = editor.txt.html()
	        alert("标题："+article_title) ;
			alert("内容："+article_content) ;
			
			//当前的时间
			var date = new Date() ;
			var year = date.getFullYear() ;
			var month = date.getMonth()+1 ;
			var day = date.getDate() ;
			var now = year + "-" + month + "-" + day ;
			
			if(editor.txt.text() == "" || article_title == ""){
				alert("文章标题与内容不能为空");
				return ;
			}
			
			//json对象
			var content = {
					"artTitle" : article_title ,
					"pubTime"  : now ,
					"artContent" : article_content
			} ;
			alert("要发送的内容"+JSON.stringify(content)) ;
			
			$.ajax({
			//注意url的写法
			url : '../Article/articleHndler.action' ,
			type : 'post',
			data : content,
			dataType : 'json',
			success:function(data){
				//alert(JSON.stringify(data)) ;
				//取出保存到数据库后返回的文章ID
				var article_id = data.artId ;
				alert("发布成功") ;
				alert(article_id) ;
				//用get方式直接给另一个页面传文章id
				window.location = "../userPubSuccess.jsp?artId="+article_id;
				
				}
			});
			
			
		
	}   
  
	//存为草稿
  
	function saveAsDraft(){
		//读取标题与编辑器中的html 
		var article_title = $('#medium-input').val() ;
		var article_content = editor.txt.html() ;
        //alert("标题："+article_title) ;
		//alert("内容："+article_content) ;
		
		//发布的时间
		var date = new Date() ;
		var year = date.getFullYear() ;
		var month = date.getMonth()+1 ;
		var day = date.getDate() ;
		var now = year + "-" + month + "-" + day ;
		

		if(editor.txt.text() == "" || article_title == ""){
			alert("文章标题与内容不能为空");
			return ;
		}
		
		//json对象
		var content = {
				"artTitle" : article_title ,
				"lastMod"  : now ,
				"artContent" : article_content
		} ;
		alert(JSON.stringify(content)) ;
		$.ajax({
		//注意url的写法
		url : '../Article/saveTemp.action' ,
		type : 'post',
		data : content,
		dataType : 'json',
		//async:false,
		success:function(data){
			//alert(JSON.stringify(data)) ;
			//取出保存到数据库后返回的文章ID
			data = JSON.parse(data) ;
//			var dra_id = data.draId ;
//			var user_id = data.userId ;
//			alert(dra_id+"-"+author_id) ;
			alert("保存成功") ;
			//保存成功后前往草稿箱
			
			
			}
		});
		//跳转到草稿箱
		draft() ;
		
		
    }
    
    
//-----------------------------------编辑文章-------------------------------------------------------
		
    //草稿箱的编辑是修改，update；文章的修改也是update
		function reEdit(id){
			var flag =  $("#column1").text() ;
			$("#ul1 a").removeClass("current") ;
			$("#editArt").addClass("current") ;
			$("#tab1").hide() ;
			$("#tab2").show() ;
			var content = {
					"jsonId" : id ,
					"jsonFlag" : flag  
			}
			alert(flag+"-"+id) ;
			$("#id").val(id) ;
			//需要返回标题和内容
			$.ajax({
				url: "../Persional/edit.action",
				type: "post",
				data: content,
				dataType: "json" ,
				success : function(data){
					var backdata = JSON.parse(data) ;
					//alert(typeof(backdata)) ;
					var title = backdata.artTitle ;
					var content = backdata.artContent ;
					//alert(title) ;
					$("#medium-input").val(title) ;
					editor.txt.html(content) ;
					
				}
				
			});
			//草稿的修改，草稿发布的时候要删除原来的草稿
			//alert(flag == "draft") ;
			if(flag == "draft"){
				document.getElementById('btn2').removeEventListener('click',saveAsDraft, false) ;
				document.getElementById('btn2').addEventListener('click',updateDraft, false) ;
				document.getElementById('btn1').addEventListener('click',pubtodeldra, false) ;
				$("#btn1").val("发布文章");
				$("#btn2").val("修改草稿");
				
				return ;	
			}
			//文章的修改，
			//alert(flag == "draft") ;
			if(flag == "article"){
				document.getElementById('btn2').removeEventListener('click',saveAsDraft, false) ;
				document.getElementById('btn2').addEventListener('click',updateArticle, false) ;
				$("#btn1").val("作为新文章发布");
				$("#btn2").val("修改文章");
				return ;
			}
			//回收站的修改，回收站的文章作为新文章发布的时候要删除原来的
			if(flag == "dustbin"){
				document.getElementById('btn1').addEventListener('click',pubtodeldust, false) ;
				$("#btn1").val("作为新文章发布");
				$("#btn2").val("存为草稿");
				return ;
			}
		}
		
	
		
		
	//修改草稿	
	function updateDraft(){
		var id =$("#id").val() ;
		var article_title = $('#medium-input').val() ;
		var article_content = editor.txt.html() ;

		if(editor.txt.text() == "" || article_title == ""){
			alert("文章标题与内容不能为空");
			return ;
		}
		
		//json对象
		var content = {
				"artId" : id ,
				"artTitle" : article_title ,
				"lastMod"  : now ,
				"artContent" : article_content
		} ;
		//alert(JSON.stringify(content)) ;
		$.ajax({
		//注意url的写法
		url : '../Article/updateDra.action' ,
		type : 'post',
		data : content,
		dataType : 'json',
		//async:false,
		success:function(data){
			alert("修改成功") ;
			}
		});
		//跳转到草稿箱
		draft() ;
		
	}
		
	//修改已发布的文章
	function updateArticle(){
		var article_title = $('#medium-input').val() ;
		var article_content = editor.txt.html() ;
		var id =$("#id").val() ;
		if(editor.txt.text() == "" || article_title == ""){
			alert("文章标题与内容不能为空");
			return ;
		}
		
		//json对象
		var content = {
				"artId" : id ,
				"artTitle" : article_title ,
				"lastMod"  : now ,
				"artContent" : article_content
		} ;
		//alert(JSON.stringify(content)) ;
		$.ajax({
		//注意url的写法
		url : '../Article/updateArt.action' ,
		type : 'post',
		data : content,
		dataType : 'json',
		//async:false,
		success:function(data){
			alert("修改成功") ;
			}
		});
		//跳转到列表
		article() ;
	}
		
	//发布后删除回收站
	function pubtodeldust(){
		var id =$("#id").val() ;
		var content = {
				"artId" : id 
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
			}
		});
	}
	//发布后删除草稿
	function pubtodeldra(){
		var id =$("#id").val() ;
		var content = {
				"artId" : id 
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
			}
		});
	}
		