package com.cm.utils;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;

public class WebUtils {
	 
	
	/**
	 * 	得到web应用的根目录
	 * @return
	 */
	public static String getAppPath() {
		ActionContext actionContext = ActionContext.getContext();
		 ServletContext servletContext = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
		return servletContext.getRealPath("/");
	}

	/**
	 * 	根据时间生成随机数
	 */
	
	public static String random() {
		String f ;
		long l =System.currentTimeMillis()%1000 ;
		f = String.valueOf(l) ;
		return f ;
	}
	@Test
	public void test() {
		System.out.println(random());
	}

	
}
