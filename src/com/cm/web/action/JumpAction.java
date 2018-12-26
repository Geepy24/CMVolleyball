package com.cm.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
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
		//从不同地方进入的用户，登陆完要去到不同地方，需要检测用户来源页面的url，存放标记到session
		HttpServletRequest request = ServletActionContext.getRequest() ; 
		System.out.println(request.getRequestURL());
		
		//把来源URI放在session中，等登陆成功后就可以取出来跳转到相应的action
		request.getSession().setAttribute("login-ref", request.getRequestURL());
		
		
		
		return "login" ; 
			
	}
	@Action("register")
	public String register() {
		return "register" ;
	}
	
}
