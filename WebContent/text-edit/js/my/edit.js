/**
 * 编辑
 */
$(document).ready(function(){
	//获取页面来源，如果是菜单进来的，就直接放
	var ref = document.referrer ;
	alert(ref) ;
	if(ref.indexOf("DustEdit") != -1){
		$.ajax({
			url : "../Article/dustEdit.action" ,
			success : function(data){
				//alert(data) ;
				
				if(data != null){
					//alert(data) ;
					
					var backdata = JSON.parse(data) ;
					$("#title").html(backdata.artTitle) ;
					$("#content").html(backdata.artContent) ;
				}
				
			}
		}) ;
	}
	
	
	if(ref.indexOf("DraEdit") != -1){
		$.ajax({
			url : "../Article/draEdit.action" ,
			success : function(data){
				//alert(data) ;
				
				if(data != null){
					//alert(data) ;
					
					var backdata = JSON.parse(data) ;
					$("#title").html(backdata.artTitle) ;
					$("#content").html(backdata.artContent) ;
				}
				
			}
		}) ;
	}
	
	
	
	
});