var userName =  null
var realName = null ;
var password_temp = null ;
var password = null ;
var gender = $("#input_gender").val();
var tel = null ;
var checkCode = null ;
/**
 * 1.检查用户名是否已经被占用，以及长度，长度还没检查
 * @returns
 */
$("#input_userName").blur(function(){
			//alert("进入检查");
			//1.临时的姓名变量，如果全部符合要求才会被赋值到userName
			userName_temp = $("#input_userName").val() ;
			//alert(userName.length) ;
			//2.判断是否为空
			if("" == userName_temp){
				$("#userName_flag").html("用户名不能为空") ;
				return ;
			}
			//3.判断长度
			if(userName_temp.length < 5 || userName_temp.length >= 15){
				$("#userName_flag").html("用户名字数要在5~15个字符之间") ;
				return ;
			}
			//4.判断是否为汉字数字字母下划线
			 username_regex = /^[A-Za-z0-9_]{4,14}$/;
			if(username_regex.test(userName_temp) == false){
				$("#userName_flag").html("用户名格式错误，用户名由英文、数字、下划线组成") ;
				return ;
			}
			var user_name = {
					"userName" : userName_temp
			} ;
			$.ajax({
				//检查登录的时候也有相同的根据用户名查看是否已存在用户，直接使用
				url: "../Login/verifyUsername.action",
				type : 'get',
				data : user_name,
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
						//全部通过，将临时变量的username赋给username
						userName = userName_temp ;
					}	
					if("true" == flag){
						$("#userName_flag").html("用户名已存在") ;
						 }			
				}
				
			}) ;
			
		}) ;


/**
 * 2.检查密码的格式
 */
$("#input_password").blur(function(){
	//alert(userName) ;
	password_temp = $("#input_password").val() ;
	//alert(password_temp) ;
	if("" == password_temp){
		$("#password_flag").html("密码不能为空") ;
		return ;
	}
	//密码，字母开头，包含字母数组下划线
	password_regex = /^[a-zA-Z]\w{5,17}$/ ;
	
	//密码格式
	if(password_regex.test(password_temp) == false){
		$("#password_flag").html("密码格式错误，密码以字母开头，包含字母、数字、下划线，长度为6~18个字符") ;
		return ;
	}
	
	$("#password_flag").html("<img src='../img/ticket.png'/>") ;
	
	
	
	
}) ;
/**
 * 3.检查密码输入的长度以及两次密码输入是否一致
 */
$("#ensure_password").blur(function(){
	
	ensure_password_temp = $("#ensure_password").val() ;
	//salert(password_temp) ;
	
	if(password_temp != ensure_password_temp){
		$("#ensure_password_flag").html("两次密码输入不一致") ;
		return ;
	}
	$("#ensure_password_flag").html("<img src='../img/ticket.png'/>") ;
	password = password_temp ;
	//alert(password) ;
});
/**
 * 4.检查真实姓名的输入，只能是汉字或英文
 */
$("#input_realName").blur(function(){
	
	realName_temp = $("#input_realName").val() ;
	
	realName_regex = /^[\u4E00-\u9FA5A-Za-z]{1,11}$/ ;
	
	if(realName_temp == ""){
		$("#realName_flag").html("") ;
		return ;
	}
	if(realName_regex.test(realName_temp) == false){
		$("#realName_flag").html("只能输入汉字和字母,长度为2~12个字符") ;
		return ;
	}	
	realName_temp = realName ;
	
	
	
}) ;

/**
 * 5.检查手机号码的输入
 */

$("#input_tel").blur(function(){
	
	tel_temp = $("#input_tel").val() ;
	tel_regex = /^1[3,5,8]\d{9}/ ;

	if(tel_temp == ""){
		$("#tel_flag").html("") ;
		return ;
	}
	if(tel_regex.test(tel_temp) == false){
		$("#tel_flag").html("请输入正确的手机号码格式") ;
		return ;
	}
	tel = tel_temp ;
	
	
}) ;


/**
 * 6.点击提交按钮后，检查验证码，提交到action
 * 验证码暂无
 */
$("#submit").click(function(){
	alert("提交请求") ;
	if(userName == null || password==null || gender == null){
		alert("请完整输入必填信息") ;
		return ;
	}
	
	var newUser = {
			"user.userName" : userName ,
			"user.realName" : realName ,
			"user.password" : password ,
			"user.tel"      : tel ,
			"user.gender"   : gender 
	};
	alert(JSON.stringify(newUser)) ;
	
	$.ajax({
		url : "../User/registerAction.action" ,
		type : "POST" ,
		data : newUser ,
		dataType: "json" ,
		success :function(data){
			
			alert("用户：\""+userName+"\"注册成功！") ;
			alert("注册成功，点击跳转至登录界面") ;
			//ajax请求action，跳转到登录页面
			$(location).attr("href","../jsp/login.jsp")
			
		}
	}) ;

}) ;
