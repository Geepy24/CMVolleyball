package com.cm.utils;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;

public class WebUtils {
	 
	
	/**
	 * 	�õ�webӦ�õĸ�Ŀ¼
	 * @return
	 */
	public static String getAppPath() {
		ActionContext actionContext = ActionContext.getContext();
		 ServletContext servletContext = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
		return servletContext.getRealPath("/");
	}

	/**
	 * 	����ʱ�����������
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
