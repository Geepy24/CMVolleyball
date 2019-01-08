package com.cm.web.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.cm.domain.Picture;
import com.cm.domain.PictureCheck;
import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.service.IResourceService;
import com.cm.service.IUserService;
import com.cm.utils.WebUtils;
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
	@Autowired
	private IUserService userService ;
	
	private HttpServletRequest request = ServletActionContext.getRequest() ;
	private User user =  (User) request.getSession().getAttribute("loginInfo")  ;
	private PictureCheck pictureCheck = new PictureCheck() ;
	private String returndata ;
	private File upload ;
	private String uploadFileName ;
	private List<PictureCheck> pictureChecks ;
	private static int MAXRESULTS = 10  ;
	private int currentPage ;
	private int toPage ;
	
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
	
	public int getToPage() {
		return toPage;
	}


	public void setToPage(int toPage) {
		this.toPage = toPage;
	}


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
		pictureCheck.setCheckTag(0);
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
		pictureChecks = resourceService.findPCsByCheckTag(0, currentPage, MAXRESULTS) ;
		//加上页码

		Long totalItems = resourceService.findAllPcs() ;
		Long totalPcs ;
				
		//总页数
		if(0 == totalItems) {
			totalPcs = new Long(1);
		}else if(0 == totalItems%10) {
			totalPcs = totalItems/10 ;
		}else {
			totalPcs = (totalItems/10) + 1  ;
		}
	
		request.getSession().setAttribute("totalPcs",totalPcs );
		
		return SUCCESS;
	}
	
	
	//审核查看图片详情
	@Action(value="pcDetail",results= {
			@Result(name="success",location= "/WEB-INF/jsp/management/resource/pcDetail.jsp")
	})
	public String pcDetail() {
		System.out.println(pictureCheck);
		
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
	//审核图片
	@Action(value="checkPic",results= {
			@Result(name="success",type="chain",location="toPcList")
	})
	public String checkedPicture() {
				
		System.out.println(pictureCheck);
		
		
		//审核不通过
		if(pictureCheck.getCheckTag() == -1) {
			resourceService.updatePictureCheck(pictureCheck);
			System.err.println("审核不通过");
			return SUCCESS ;
		}
		System.out.println("审核通过");
		
		Picture picture = new Picture() ;
		Resource resource = new Resource() ;
		
		
		picture.setPicName(pictureCheck.getPicName());
		picture.setPicUri(pictureCheck.getPicUri());
		resource.setPicture(picture);
		resource.setPubTime(WebUtils.getTime());
		resource.setResCom(pictureCheck.getResCom());
		resource.setUser(userService.findUserById(pictureCheck.getUserId()));
		resource.setResTag("pic");
		resource.setAdsId(user.getUserId());
		resourceService.saveResource(resource);
		resourceService.updatePictureCheck(pictureCheck);
		
		
		return SUCCESS ;
	}
	
	//上一页
		@Action(value="prePcPage",results= {
				@Result(name="success",location="/WEB-INF/jsp/management/resource/pcList.jsp")
				
		})
		public String previousMcPage() {
			currentPage = (int) request.getSession().getAttribute("currentPage") ;
			currentPage = currentPage - 1 ;
			if(currentPage <= 0) {
				return "fail" ;
			}
			
			pictureChecks = resourceService.findPCsByCheckTag(0, currentPage, MAXRESULTS) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
		}
		//下一页
		@Action(value="nextPcPage",results= {
				@Result(name="success",location="/WEB-INF/jsp/management/resource/pcList.jsp")
				
		})
		public String nextPcPage() {
			currentPage = (int) request.getSession().getAttribute("currentPage") ;
			System.out.println("currentpage:"+currentPage);
			currentPage = currentPage + 1 ;
			
			pictureChecks = resourceService.findPCsByCheckTag(0, currentPage, MAXRESULTS) ;
			System.out.println(pictureChecks.size());
			if(0 == pictureChecks.size()) {
				return "fail" ;
			}
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		//前往某页
		@Action(value="selectPcPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/pcList.jsp")})
		public String selectPage() {
			
			System.out.println(toPage);
			currentPage = toPage ;
			pictureChecks = resourceService.findPCsByCheckTag(0, currentPage, MAXRESULTS) ;
			if(0 == pictureChecks.size()) {
				return "fail" ;
			}
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
		}
}
