
/**
 * 1.检查用户名是否已经被占用，以及长度，长度还没检查
 * @returns
 */
$("#input_userName").blur(function(){
			//alert("进入检查");
			if("" == $("#input_userName").val()){
				$("#userName_flag").html("用户名不能为空") ;
				return ;
			}
			var username = {
					"userName" : $("#input_userName").val()
			} ;
			$.ajax({
				//检查登录的时候也有相同的根据用户名查看是否已存在用户，直接使用
				url: "../Login/verifyUsername.action",
				type : 'get',
				data : username,
				dataType : 'json',
				success: function(data){
					//data是json对象
				//	alert(JSON.stringify(data)) ;
					//isExist是JSON字符串
					var isExist = data.returndata;
				//	alert(isExist) ;//{"xxx" : "xxx"}
					//json字符串转成json对象
					var obj = eval('('+isExist+')') ;
					//取出value ，true/false
					var flag = (obj.backdata);
					if("false" == flag ){
						//创建节点，插入到指定的标签的子标签最后面
						$("#userName_flag").html("<img src='../img/ticket.png'/>") ;
					}	
					if("true" == flag){
						$("#userName_flag").html("用户名已存在") ;
						 }			
				}
				
			}) ;
			
		}) ;



/**
 * 2.检查密码输入的长度以及两次密码输入是否一致
 */
$("#ensure_password").blur(function(){
	var realName = $("#input_realName").val();
	var password = $("#input_password").val();
});
/**
 * 3.点击提交按钮后，检查真实姓名长度，联系方式长度
 */
$("#submit").blur(function(){
	var userName =  $("#input_userName").val();
	var realName = $("#input_realName").val();
	var password = $("#input_password").val();
	//var ensurePassword = $("#ensure_password").val();
	var gender = $("#input_gender").val();
	var tel = $("#input_tel").val();
	var checkCode = $("#input_checkCode").val();

}) ;
