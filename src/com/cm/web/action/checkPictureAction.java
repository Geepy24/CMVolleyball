package com.cm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.PictureCheck;
import com.cm.domain.User;
import com.cm.service.IResourceService;
import com.cm.utils.pathUtils;
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
	
	private HttpServletRequest request = ServletActionContext.getRequest() ;
	private User user =  (User) request.getSession().getAttribute("loginInfo")  ;
	private PictureCheck pictureCheck = new PictureCheck() ;
	private String returndata ;
	private File upload ;
	private String uploadFileName ;
	private List<PictureCheck> pictureChecks ;
	private static int MAXRESULTS = 10  ;
	private int currentPage ;
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

	
	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	
	

	//---------------------------------用户提交，等待审核------------------------------------------------
	
	public List<PictureCheck> getPictureChecks() {
		return pictureChecks;
	}


	public void setPictureChecks(List<PictureCheck> pictureChecks) {
		this.pictureChecks = pictureChecks;
	}


	@Action(value="picToCheck",results= {
			@Result(name="success",location= "/UI2/addSuccess.html")
			
	})
	public String addCheck() {
		//需要的信息：图片名称：uploadFileName，图片文件：upload，图片存储文件夹位置：filePath
		//保存到数据表的信息：uri,name,com,tag,user_id
		//图片存储文件夹位置
		String filePath =  pathUtils.picturePath() ;
		System.out.println(filePath);
		
		File file = new File(filePath); 
        if(!file.exists()){ 
            file.mkdir(); 
        } 
		System.out.println("文件路径-文件名-文件:"+filePath+"-"+uploadFileName+"-"+upload);
		
		
		pictureCheck.setCheckCom("等待审核");
		//y就是已审核，n是未审核，f是审核不通过
		pictureCheck.setCheckTag("n");
		pictureCheck.setPicName(uploadFileName);
		pictureCheck.setPicUri(filePath+"/"+uploadFileName);
		pictureCheck.setUserId(user.getUserId());
		
		System.out.println(pictureCheck);
		//保存到表
		resourceService.savePictureCheck(pictureCheck) ;
		
		
		//保存到本地
		try {
			FileUtils.copyFile(upload, new File(file,uploadFileName));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("保存到本地文件夹失败");
			return "fail" ;
		}
		
		
		
		
		//要改成返回一个页面，而不是json
		return SUCCESS ;
	}
	//---------------------------------管理员审核，这部分是jsp------------------------------------------------
	
	//显示待审核资源列表
	
	@Action(value="toPcList",results= {
			@Result(name="success",location="/WEB-INF/jsp/management/resource/pcList.jsp")
	})
	public String showCheckList() {
		
		currentPage = 1 ;
		request.getSession().setAttribute("currentPage", currentPage);
		//查找未审核
		pictureChecks = resourceService.findPCsByCheckTag("n", currentPage, MAXRESULTS) ;
		
		
		return SUCCESS;
	}
	
	
	//审核查看图片详情
	@Action(value="pcDetail",results= {
			@Result(name="success",location= "/WEB-INF/jsp/management/resource/pcDetail.jsp")
	})
	public String pcDetail() {
		System.out.println(pictureCheck);
		//这里有问题，没查出来
		PictureCheck pictureCheckTemp = resourceService.findPCById(pictureCheck.getPicId()) ;
		pictureCheck.setCheckCom(pictureCheckTemp.getCheckCom());
		pictureCheck.setPicName(pictureCheckTemp.getPicName());
		pictureCheck.setPicUri(pictureCheckTemp.getPicUri());
		pictureCheck.setResCom(pictureCheckTemp.getResCom());
		pictureCheck.setCheckTag(pictureCheckTemp.getCheckTag());
		pictureCheck.setUserId(pictureCheckTemp.getUserId());
		System.out.println("2"+pictureCheck);
		return SUCCESS ;
	}
}
