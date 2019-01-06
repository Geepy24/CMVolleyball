package com.cm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.tribes.membership.McastService;
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

import com.cm.domain.MediaPreview;
import com.cm.domain.MovieCheck;
import com.cm.domain.PictureCheck;
import com.cm.domain.User;
import com.cm.service.IResourceService;
import com.cm.utils.movieUtils;
import com.cm.utils.pathUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.mock.MockActionInvocation;
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
public class checkMovieAction extends ActionSupport implements ModelDriven<MovieCheck> {

	@Autowired
	private IResourceService resourceService ;
	private HttpServletRequest request = ServletActionContext.getRequest() ;
	private User user =  (User) request.getSession().getAttribute("loginInfo")  ;
	
	private MovieCheck movieCheck = new MovieCheck() ;
	
	private String returndata ;
	private File upload ;
	private String uploadFileName ;
	private List<MovieCheck> movieChecks ;
	private static int MAXRESULTS = 10  ;
	private int currentPage ;
	
	
	
	
	
	@Override
	public MovieCheck getModel() {
		return movieCheck;
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

	
	public List<MovieCheck> getMovieChecks() {
		return movieChecks;
	}



	public void setMovieChecks(List<MovieCheck> movieChecks) {
		this.movieChecks = movieChecks;
	}



	//---------------------------------用户提交，等待审核------------------------------------------------
	@Action(value="movToCheck",results= {
			@Result(name="success",location="/UI2/addSuccess.html")
			
	})
	public String addCheck() {
		//需要的信息：视频名称：uploadFileName，视频文件：upload，图片存储文件夹位置：filePath
		//保存到数据表的信息：uri,name,com,tag,user_id,movCom	
		//图片存储文件夹位置
		String filePath =  pathUtils.moviePath() ;
		System.out.println(filePath);
		
		File file = new File(filePath); 
        if(!file.exists()){ 
            file.mkdir(); 
        } 
		System.out.println("文件路径-文件名-文件:"+filePath+"-"+uploadFileName+"-"+upload);
		//缩略图对象
		MediaPreview mediaPreview = new MediaPreview() ;
		
        
		movieCheck.setCheckCom("等待审核");
		//y就是已审核，n是未审核，f是审核不通过
		movieCheck.setCheckTag("n");
		movieCheck.setMovName(uploadFileName);
		movieCheck.setMovUri(filePath+"/"+uploadFileName);
		movieCheck.setUserId(user.getUserId());
		
		//缩略图的文件夹
		String mpPath = pathUtils.mpPath() ;
		File mpFile = new File(mpPath); 
		  if(!mpFile.exists()){ 
		      mpFile.mkdir(); 
		   }
		 //输出的jpg的完整uri
		String mpName = movieCheck.getMovName().split("\\.")[0] + ".jpg";
		String mpUri = mpPath+"/"+mpName;		
		mediaPreview.setMpName(mpName);
		mediaPreview.setMpUri(mpUri);
		
		movieCheck.setMediaPreview(mediaPreview);
		System.out.println(movieCheck);
		//保存到表
		resourceService.saveMovieCheck(movieCheck) ;
		
		
		//保存视频及缩略图到本地
		try {
			FileUtils.copyFile(upload, new File(file,uploadFileName));
			movieUtils.handler(movieUtils.ffmpegPath, movieCheck.getMovUri(),mpUri );
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("保存到本地文件夹失败");
			return "fail" ;
		}
		
		
		return SUCCESS ;
	}



//---------------------------------管理员审核，这部分是jsp------------------------------------------------
	//显示待审核资源列表
	@Action(value="toMcList",results= {
			@Result(name="success",location="/WEB-INF/jsp/management/resource/mcList.jsp")
	})
	public String showCheckList() {
		currentPage = 1 ;
		request.getSession().setAttribute("currentPage", currentPage);
		//查找未审核
		movieChecks = resourceService.findMCsByCheckTag("n", currentPage, MAXRESULTS) ;
		
		return SUCCESS;
	}
	//审核查看视频详情
		@Action(value="mcDetail",results= {
				@Result(name="success",location= "/WEB-INF/jsp/management/resource/mcDetail.jsp")
		})
		public String mcDetail() {
			System.out.println(movieCheck);

			MovieCheck movieCheckTemp = resourceService.findMCById(movieCheck.getMovId()) ;
			
			System.out.println("2"+movieCheck);
			return SUCCESS ;
		}

	
}
