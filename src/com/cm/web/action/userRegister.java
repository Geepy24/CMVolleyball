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
import com.sun.xml.internal.bind.v2.runtime.Name;

import net.sf.json.JSONObject;
@ParentPackage("json-default")
@Namespace("/User")
@Results({
	@Result(name="success",location="/jsp/login.jsp")
})
public class userRegister extends ActionSupport implements Serializable ,ModelDriven<User>{
//	private String userName ;
	private User user  ;
	@Autowired
	private IUserService userService ; 
	//返回的json数据
	private JSONObject returndata ; 
	

	//模型驱动
	@Override
	public User getModel() {
		return user;
	}
	
//----------getter and setter-------------------------
//	public String getUserName() {
//		return userName;
//	}
//
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
	public JSONObject getReturndata() {
		return returndata;
	}

	public void setReturndata(JSONObject returndata) {
		this.returndata = returndata;
	}
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



//--------------Action------------------------------------


	@Action(value="registerAction",results= {@Result(name="success",type="json")},
				params={"root","returndata"})
	public String register() {
		System.out.println("注册接收请求");
		System.out.println("前端传回："+user);
		//保存正常
		//userService.saveUser(user);
		
		//传回json对象
		String str = "{\"returndata\" : \"registerSuccess\"}" ;
		returndata = JSONObject.fromObject(str) ;
		return SUCCESS ;
	}

}
