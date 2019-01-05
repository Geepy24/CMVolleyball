package com.cm.web.action;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.MovieCheck;
import com.cm.domain.PictureCheck;
import com.cm.service.IResourceService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 处理待审核的图片和视频以及审核图片和视频
 * @author mac
 *
 */
@Namespace("/Resource")
@ParentPackage("p1")
@InterceptorRefs({@InterceptorRef("loginDefault")})
@Results({
	@Result(name="login",type="chain",location="login",params= {"namespace","/User"}),
	@Result(name="fail",location="/fail.jsp")
})
public class checkPictureAction extends ActionSupport implements ModelDriven<PictureCheck> {

	@Autowired
	private IResourceService resourceService ;
	
	private PictureCheck pictureCheck = new PictureCheck() ;
	private String returndata ;
	
	

	@Override
	public PictureCheck getModel() {
		return pictureCheck;
	}







	public String getReturndata() {
		return returndata;
	}


	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}








}
