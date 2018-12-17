/**
 * 编辑
 */
$(document).ready(function(){
	
	
	
	$.ajax({
		url : "../Article/dustEdit.action" ,
		success : function(data){
			alert(data) ;
			
			if(data != null){
				//alert(data) ;
				
				var backdata = JSON.parse(data) ;
				$("#title").html(backdata.artTitle) ;
				$("#content").html(backdata.artContent) ;
			}
			
			
			
		}
	}) ;
	
	
});