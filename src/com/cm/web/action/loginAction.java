package com.cm.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.User;
import com.cm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 验证登录
 * @author Huangjiping
 *
 */
@ParentPackage("json-default")
@Namespace("/Login")
@Results({
	@Result(name="login",location="/jsp/login.jsp"),
	@Result(name="fail",location="/fail.jsp")
})

public class loginAction extends ActionSupport {
	@Autowired
	private IUserService userService ;
	private String userName ;
	private String password ;
	private String returndata ; 

	private HttpServletRequest request = ServletActionContext.getRequest() ;

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}

	HttpSession session = ServletActionContext.getRequest().getSession() ;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 	根据用户名判断用户是否存在，新建一张用户名表是否会更好
	 */
	@Action(value="verifyUsername",results= {@Result(name="success",type="json")},
			params= {"root","returndata"})
	public String verifyUsername() {
		System.out.println("请求传入");
		System.out.println("前端传回"+userName);
		//得到结果true、false
		returndata = String.valueOf(userService.isUserExist(userName)) ;
		//拼接成json字符串并返回
		returndata = "{\"backdata\" : \""+returndata+"\"}"   ;
		System.out.println("返回的json字符串："+returndata);
	
		return SUCCESS ;
	}
	
	
	/**
	 * 验证登录
	 * @return
	 */
	@Action(value="verifyLogin",results= {
			@Result(name="success",location="/index.jsp"),
			@Result(name="picture",type="redirectAction",location="indexpic",params= {"namespace","/Resource"}),
			@Result(name="manager",type="redirectAction",location="management",params= {"namespace","/privateSource"})
			//用chain到另一个action，刷新页面会导致重复提交表单，用redirectAction不会
	})
	public  String Login() {
		
			User user = userService.findUserByName(userName) ;
			
			if(!user.getPassword().equals(password)) {
				System.out.println(user.getPassword());
				session.setAttribute("loginFail", "密码错误");
				return "fail" ;
				
			}
			
			//登录成功，把登录标记“loginInfo”存到session域中
			session.setAttribute("loginInfo", user);
			
			//取出用户来源Url，实现从哪来跳转会哪去
		String ref = request.getSession().getAttribute("login-ref").toString() ;
		System.out.println("页面来源："+ref);
		
		if(-1 != ref.indexOf("indexpic")) {
			return "picture" ;
		}
		if(-1 != ref.indexOf("management")) {
			return "manager" ;
		}
		
		
			return SUCCESS ;
		
		

	}
	
	
	
}
