function uploadResource(){
	var resTag = $("#resTag").val() ;
	if(resTag == "pic"){
		alert(resTag) ;
		document.forms[0].action = "../Resource/picToCheck.action" ;
			
		
	}
	if(resTag == "mov"){
		alert(resTag) ;
		document.forms[0].action = "../Resource/movToCheck.action" ;
	}
	
	
}

