package com.cm.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletConfigAware;

import com.cm.domain.User;
import com.cm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 	一些私有资源需要登录了之后才能访问
 * 	这个动作类用拦截器来判断是否有登录，登陆了就放行，未登录去登录
 * 	登录与否查看
 * @author Huangjiping
 *
 */
@ParentPackage("p1")
@Namespace("/privateSource")
@Results({
	@Result(name="manager",location="/WEB-INF/jsp/manager.jsp"),
	@Result(name="login",location="/jsp/login.jsp"),
	@Result(name="AuthorityError",location="/index.jsp")
})
@InterceptorRefs({@InterceptorRef("loginDefault")})
public class privateSourceAction extends ActionSupport {

	@Autowired
	private IUserService userService ;
	
	/**
	 * 	登录后才能查看到的页面，否则去登录
	 * 	登录与否查看session中的loginInfo
	 * @return
	 */
	
	@Action("management")
	public String management() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("loginInfo") ;
		int userId = user.getUserId() ;
		boolean isAds = userService.isAds(userId) ;
		System.out.println("isAds="+isAds);
		if(false == isAds) {
			ServletActionContext.getRequest().setAttribute("AuthorityError","当前用户无访问权限");
			return "AuthorityError" ;
		}
		
		return "manager"; 
	}
	
	
	
	
}
