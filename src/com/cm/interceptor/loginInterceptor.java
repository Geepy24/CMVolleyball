package com.cm.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 	登陆拦截器，只有登录了之后才能查看内容
 * @author Huangjiping
 *
 */
public class loginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//数据在session域中
		Object object = ServletActionContext.getRequest().getSession().getAttribute("loginInfo") ;
		
		if(object == null) {
			return "login" ;//没登录，去登录
		}
		System.out.println("当前用户："+object.toString());
		
		return invocation.invoke() ;  //已登录，放行
	}

}
