package com.cm.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 	此动作类负责跳转
 * 	
 * @author Huangjiping
 *
 */
@Controller("jumpAction")
@ParentPackage("struts-default")
@Namespace("/User")
@Results({
		@Result(name="login",location="/jsp/login.jsp"),
		@Result(name="register",location="/jsp/register.jsp")

})
public class JumpAction extends ActionSupport  {
	
	@Action("login")
	public String login() {
		
		return "login" ; 
			
	}
	@Action
	public String register() {
		return "register" ;
	}
	
}
