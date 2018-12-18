package com.cm.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.cm.domain.User;
import com.cm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("struts-default")
@Namespace("/User")
@Results({
		@Result(name="hello",location="/success.jsp"),
		@Result(name="findAllUser",location="/WEB-INF/jsp/management/user/list.jsp"),
		@Result(name="findOne",location="/WEB-INF/jsp/management/user/edit.jsp"),
		@Result(name="fail",location="/fail.jsp"),
		@Result(name="deleteSuccess",location="/WEB-INF/jsp/management/user/list.jsp"),
		@Result(name="success",location="/WEB-INF/jsp/management/user/edit.jsp")
})
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	@Resource(name="userService")
	private IUserService userService ; 
	//--------模型驱动--------
	private User user =  new User();  
	private List<User> users ; 
	
	//--------有实体类的属性驱动--------
	private String userName ;  //属性驱动
	private int userId;
	
	
	//--------getter/setter--------
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public User getModel() {
		return user;
	}

//------------------动作方法-------------------------
	
	@Action("hello")
	public String hello() {
		return "hello" ;
	}
	/**
	 * 查找所有用户
	 * @return
	 */
	@Action("findAll")
	public String findAll() {
		
		users = userService.findAllUsers() ;
		
		return "findAllUser" ;
	}
	/**
	 * 	根据名字查找用户
	 * @return
	 */
	@Action("findByName")
	public String findByName() {  //Action中的方法不能有参数
		
		user = userService.findUserByName(user.getUserName()) ;
		System.out.println(user);
//		if(user == null) {
//			return "fail" ;
//		}
		return "findOne" ;  //查询不到，返回错误页面
	}
	/**
	 * 根据id查找用户
	 * 
	 */
	@Action("findById")
	public String findById() {  //Action中的方法不能有参数
		
		System.out.println("前端传递:"+user.getUserId());
		user = userService.findUserById(user.getUserId());
		System.out.println(user);
		
		return SUCCESS ;
	}
	
	/**
	 * 保存用户
	 */
	@Action("save")
	public String save() {
		
		userService.saveUser(user);
		return SUCCESS ;
	}
	/**
	 * 	删除用户
	 */
	@Action("delete")
	public String delete() {
		//根据id或者用户名查找到用户之后再将其删除
		System.out.println(userId);
		if(0 != userId) {
			user = userService.findUserById(userId) ;
			userService.deleteUser(user);
			return "deleteSuccess" ;
		}
		if(null != userName){
			user = userService.findUserByName(userName) ;
			userService.deleteUser(user);
			return "deleteSuccess" ;
		}
		return "fail" ;
		
	}
	
	
	
	
}
