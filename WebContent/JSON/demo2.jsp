<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Struts2整合JSON</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" ></script>
</head>
<body>
	<h1>Struts2整合JSON</h1>
	username:<input type="text" name="userName" id="userName"><br>
	realname:<input type="text" name="realName" id="realName"><br>
	password:<input type="text" name="password" id="password"><br>
	gender:<input type="text" name="gender" id="gender"><br>
	tel:<input type="text" name="tel" id="tel"><br>
	<input type="button" name="test" value="test" onclick="test()" >
	<script type="text/javascript">
		function test(){
			var user_data={
				'user.userName' : $('#userName').val(),	
				'user.realName' : $('#realName').val(),	
				'user.password' : $('#password').val(),	
				'user.gender' : $('#gender').val(),	
				'user.tel' : $('#tel').val()	
			};
			$.ajax({
				url: 'jqueryJson.action' ,
				type:'post',
	            data:user_data,
	            dataType:"json",
				success:function(data){
					alert("1回传的数据："+data) ;
					//得到传回来的对象
					var backdata = data.returndata ;
					//alert("2,字符串"+JSON.stringify(backdata)) ;
					//得到对象的value
					alert(backdata.backusername) ;
					//回显
					$('#back_data').html("username="+backdata.backusername) ;
				}
			}) ;
		}
	</script>
	<h2>显示回传的数据</h2>
	<div id="back_data">
		
		
	
	</div>
	
</body>
</html>