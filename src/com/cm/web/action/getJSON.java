package com.cm.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.cm.domain.User;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
/**
 * 接收JSON数据
 * @author Huangjiping
 *
 */

@ParentPackage("json-default")
@Results({
	//@Result(name="success",type="json",params= {"root","returndata"})
})
public class getJSON extends ActionSupport {
	private User user ;
	private JSONObject returndata ;
	
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

//	@Action("getJSON")
//	public String execute() {
//		//怎么识别到前端的jsonString
//		System.out.println(jsonString);
//		//创建JSONObject对象,以下代码没有作用
//		JSONObject json = JSONObject.fromObject(jsonString) ;
//		System.out.println(json);
//		System.out.println(json.get("username"));
//		System.out.println(json.get("password"));
//		System.out.println(json.get("age"));
//		System.out.println(json.get("BigText"));
//		
//		return null ;
//	}
	
	
	@Action(value="jqueryJson",results= {@Result(name="success",type="json")},
			params= {"root","returndata"})
	public String executeJson() {
		//自动进入bean中
		System.out.println(user);
		System.out.println("前端传回name:"+user.getRealName());
		//封装成json格式
		Map<String, String> map = new HashMap<>() ;
		map.put("backusername",user.getUserName()) ;
		System.out.println("封装到map中成功"+map.get("backusername"));
		
	//不成功	returndata = "{\"backusername\":\""+map.get("backusername")+"\"}";
		
		//将map转成json对象
		JSONObject json = JSONObject.fromObject(map) ;
		System.out.println("json对象"+json);
		//不用变成字符串返回，在配置中返回这个数据
		returndata  = json ;
		System.out.println("要返回的JSONObject对象"+returndata);
		return SUCCESS ;
	}
	
}
