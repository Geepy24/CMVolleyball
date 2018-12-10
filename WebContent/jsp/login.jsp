<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录界面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/Login/verifyLogin.action" method="post"> 
		<table>
			<tr id=username_row>
			<td>用户名：</td>
			<td><input type="text" name="userName" id="input_username"></td>
			<td id="username_flag"></td>  <!-- 用来存放用户是否存在的标志 -->
			</tr>
			<tr>
			<td>密码：</td>
			<td><input type="password" name="password"></td>
			</tr>
			<tr>
			<td><input type="submit" value="确定"></td>
			</tr>
			
		</table>
		
	</form>
	<script type="text/javascript">
		$('#input_username').blur(function(){
		//	alert("失去焦点") ;
			var user_name = {
					//JSON对象里面的key要和action中的属性名对应，
					//而不是JSON对象的名字跟action中的属性名对应
					'userName' : $('#input_username').val() 
			} ;
			$.ajax({
				//注意url的写法
				url : '../Login/verifyUsername.action' ,
				type : 'post',
				data : user_name,
				dataType : 'json',
				success:function(data){
					
					//data是json对象
				//	alert(JSON.stringify(data)) ;
					//isExist是JSON字符串
					var isExist = data.returndata;
				//	alert(isExist) ;//{"xxx" : "xxx"}
					//json字符串转成json对象
					var obj = eval('('+isExist+')') ;
					//取出value ，true/false
					var flag = (obj.backdata);
					if(flag == "false"){
						//创建节点，插入到指定的标签的子标签最后面
						$("#username_flag").html("用户不存在") ;
						return ;
					}	
					if(flag =="true"){
						$("#username_flag").html("<img src='../img/ticket.png'/>") ;
						 }
						
					}
					
				});
			}) ;
			
	
	
	</script>
	
</body>
</html>