<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>json传输数据</title>
<script type="text/javascript" src="/js/json2.js"></script>
<script type="text/javascript">
	var myAjaxObject ;
	function ajaxTransferText(){
		var BigText = document.getElementById("BigText").value ;
		//创建ajax对象
		var ajaxTransferObjectRef = new ajaxTransferObjectRef("username","password",10,BigText) ;
		//把ajax对象转成JSON字符串
		var JSONString = JSON.stringify(ajaxTransferObjectRef) ;
		//创建XMLHttpRequest
		if(window.ActiveXObject){
			myAjaxObject = new ActiveXObject("Microsoft.XMLHTTP") ;
		}else{
			myAjaxObject = new XMLHttpRequest() ;
		}
		//这是要发送到动作类的url，前面的jsonString要和动作类中的属性名一致
		//动作类中的属性要有getter、setter
		var urlString = "jsonString="+JSONString ;
		alert(urlString) ;
		
		//POST向getJSON.action发送数据。没设置onreadychanged，不对服务器过来的数据做反映
		myAjaxObject.open("POST","getJSON.action"+"?timestamp"+new Date().getTime(),true) ;
		myAjaxObject.setRequestHeader("Content-Type","application/x-www-form-urlencoded") ;
		myAjaxObject.send(urlString) ;
		
		function ajaxTransferObjectRef(username,password,age,BigText){
			this.username = username ;
			this.password = password ;
			this.age = age ; 
			this.BigText = BigText ;
		}
	}
</script>
</head>
<body>
	<textarea rows="5" cols="45" id="BigText"></textarea>
	<br>
	<input type="button" value="test" onclick="ajaxTransferText()" />
</body>
</html>