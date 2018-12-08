package com.cm.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.ResultSetReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.cm.domain.User;
import com.cm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * ��֤��¼
 * @author Huangjiping
 *
 */
@ParentPackage("json-default")
@Namespace("/Login")
@Results({
	@Result(name="success",location="/index.jsp"),
	@Result(name="login",location="/jsp/login.jsp"),
	@Result(name="fail",location="/fail.jsp")
})

public class loginAction extends ActionSupport {
	@Autowired
	private IUserService userService ;
	private String userName ;
	private String password ;
	private String returndata ; 
	
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
	 * 	�����û����ж��û��Ƿ���ڣ��½�һ���û������Ƿ�����
	 */
	@Action(value="verifyUsername",results= {@Result(name="success",type="json")},
			params= {"root","returndata"})
	public String verifyUsername() {
		System.out.println("������");
		System.out.println("ǰ�˴���"+userName);
		//�õ����true��false
		returndata = String.valueOf(userService.isUserExist(userName)) ;
		//ƴ�ӳ�json�ַ���������
		returndata = "{\"backdata\" : \""+returndata+"\"}"   ;
		System.out.println("���ص�json�ַ�����"+returndata);
	
		return SUCCESS ;
	}
	
	
	/**
	 * ��֤��¼
	 * @return
	 */
	@Action("verifyLogin")
	public  String Login() {
		
			
			User user = userService.findUserByName(userName) ;
			
			if (user == null) {
				session.setAttribute("loginFail", "�û�������");
				return "fail" ;
			}
			
			if(!user.getPassword().equals(password)) {
				System.out.println(user.getPassword());
				session.setAttribute("loginFail", "�������");
				return "fail" ;
				
			}
			
			//��¼�ɹ����ѵ�¼��ǡ�loginInfo���浽session����
			
			session.setAttribute("loginInfo", user);
			
			return SUCCESS ;
		
		

	}
	
	
	
}
