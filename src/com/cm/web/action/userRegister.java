package com.cm.web.action;

import java.io.Serializable;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.User;
import com.cm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@ParentPackage("json-default")
@Namespace("/User")
@Results({
	@Result(name="success",location="index.jsp")
})
public class userRegister extends ActionSupport implements Serializable ,ModelDriven<User>{
	private String userName ;
	private User user  ;
	@Autowired
	private IUserService userService ; 
	//Ä£ÐÍÇý¶¯
	@Override
	public User getModel() {
		return user;
	}
	
//----------getter and setter-------------------------
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



//--------------Action------------------------------------


	@Action("register")
	public String register() {
		
		userService.saveUser(user);
		return SUCCESS ;
	}

}
