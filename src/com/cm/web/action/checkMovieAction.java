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
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.MediaPreview;
import com.cm.domain.Movie;
import com.cm.domain.MovieCheck;
import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.service.IResourceService;
import com.cm.service.IUserService;
import com.cm.utils.WebUtils;
import com.cm.utils.movieUtils;
import com.cm.utils.pathUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 将用户的上传添加到审核
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
	@Autowired
	private IUserService userService ;
	private HttpServletRequest request = ServletActionContext.getRequest() ;
	private User user =  (User) request.getSession().getAttribute("loginInfo")  ;
	
	private MovieCheck movieCheck = new MovieCheck() ;
	
	private String returndata ;
	private File upload ;
	private String uploadFileName ;
	private List<MovieCheck> movieChecks ;
	private static int MAXRESULTS = 10  ;
	private int currentPage ;
	private Integer mpId ;
	private int toPage ;
	
	
	
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



	public Integer getMpId() {
		return mpId;
	}



	public void setMpId(Integer mpId) {
		this.mpId = mpId;
	}



	public int getToPage() {
		return toPage;
	}



	public void setToPage(int toPage) {
		this.toPage = toPage;
	}



	//---------------------------------用户上传视频等待审核------------------------------------------------
	@Action(value="movToCheck",results= {
			@Result(name="success",location="/UI2/addSuccess.html")
			
	})
	public String addCheck() {
		//上传的文件名uploadFileName上传的文件upload保存的路径filePath
		//需要的信息uri,name,com,tag,user_id,movCom	
		//保存的视频路径
		String filePath =  pathUtils.moviePath() ;
		System.out.println(filePath);
		
		File file = new File(filePath); 
        if(!file.exists()){ 
            file.mkdir(); 
        } 
		System.out.println("文件路径-文件名-文件:"+filePath+"-"+uploadFileName+"-"+upload);
		//缩略图
		MediaPreview mediaPreview = new MediaPreview() ;
		
        
		movieCheck.setCheckCom("等待审核");
		//设置为未审核
		movieCheck.setCheckTag(0);
		movieCheck.setMovName(uploadFileName);
		movieCheck.setMovUri(filePath+"/"+uploadFileName);
		movieCheck.setUserId(user.getUserId());
		
		//缩略图的本地目录
		String mpPath = pathUtils.mpPath() ;
		File mpFile = new File(mpPath); 
		  if(!mpFile.exists()){ 
		      mpFile.mkdir(); 
		   }
		 //保存的jpg的uri
		String mpName = movieCheck.getMovName().split("\\.")[0] + ".jpg";
		String mpUri = mpPath+"/"+mpName;		
		mediaPreview.setMpName(mpName);
		mediaPreview.setMpUri(mpUri);
		
		movieCheck.setMediaPreview(mediaPreview);
		System.out.println(movieCheck);
		//保存到表
		resourceService.saveMovieCheck(movieCheck) ;
		
		
		//存到本地
		try {
			FileUtils.copyFile(upload, new File(file,uploadFileName));
			movieUtils.handler(movieUtils.ffmpegPath, movieCheck.getMovUri(),mpUri );
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("存到本地失败");
			return "fail" ;
		}
		
		
		return SUCCESS ;
	}



//---------------------------------管理员审核页面，jsp------------------------------------------------
	//前往审核列表
	@Action(value="toMcList",results= {
			@Result(name="success",location="/WEB-INF/jsp/management/resource/mcList.jsp")
	})
	public String showCheckList() {
		currentPage = 1 ;
		request.getSession().setAttribute("currentPage", currentPage);
		//分页查找未审核视频列表
		movieChecks = resourceService.findMCsByCheckTag(0, currentPage, MAXRESULTS) ;
		
		//加上页码

			Long totalItems = resourceService.findAllMcs() ;
			Long totalMcs ;
					
			//总页数
			if(0 == totalItems) {
				totalMcs = new Long(1);
			}else if(0 == totalItems%10) {
				totalMcs = totalItems/10 ;
			}else {
				totalMcs = (totalItems/10) + 1  ;
			}
		
			request.getSession().setAttribute("totalMcs",totalMcs );
		return SUCCESS;
	}
	//进入视频详情
		@Action(value="mcDetail",results= {
				@Result(name="success",location= "/WEB-INF/jsp/management/resource/mcDetail.jsp")
		})
		public String mcDetail() {
			System.out.println(movieCheck);

			MovieCheck movieCheckTemp = resourceService.findMCById(movieCheck.getMovId()) ;
			movieCheck.setCheckCom(movieCheckTemp.getCheckCom());
			movieCheck.setCheckTag(movieCheckTemp.getCheckTag());
			movieCheck.setMediaPreview(movieCheckTemp.getMediaPreview());
			movieCheck.setUserId(movieCheckTemp.getUserId());
			movieCheck.setMovName(movieCheckTemp.getMovName());
			movieCheck.setMovUri(movieCheckTemp.getMovUri());
			movieCheck.setResCom(movieCheckTemp.getResCom());
			
			System.out.println("2"+movieCheck);
			return SUCCESS ;
		}
		//审核视频
	@Action(value="checkMov",results= {
				@Result(name="success",type="chain",location="toMcList")
		})
		public String checkedMovie() {
			
			System.out.println(movieCheck);
			System.out.println(movieCheck.getCheckTag());
			
			if(movieCheck.getCheckTag() == -1) {
				System.err.println("审核不过");
				MediaPreview mediaPreview = resourceService.findMCById(movieCheck.getMovId()).getMediaPreview() ;
				movieCheck.setMediaPreview(mediaPreview);
				resourceService.updateMovieCheck(movieCheck);
				return SUCCESS ;
			}
			System.out.println("审核通过");
			
			Resource resource = new Resource() ;
			Movie movie = new Movie() ;
			movie.setMovName(movieCheck.getMovName());
			movie.setMovUri(movieCheck.getMovUri());
			
			resource.setPubTime(WebUtils.getTime());
			resource.setResCom(movieCheck.getResCom());
			resource.setResTag("mov");
			resource.setAdsId(user.getUserId());
			resource.setUser(userService.findUserById(movieCheck.getUserId()));
			
			MovieCheck movieCheckTemp = resourceService.findMCById(movieCheck.getMovId()) ;
			movie.setMediaPreview(movieCheckTemp.getMediaPreview());
			movieCheck.setMediaPreview(movieCheckTemp.getMediaPreview());
			resource.setMovie(movie);
			//System.out.println(resource.getMovie());
			//System.out.println(resource.getMovie().getMediaPreview());
			//System.out.println(resource);
			//更新回mc表
			resourceService.updateMovieCheck(movieCheck);
			resourceService.saveResource(resource);
			return SUCCESS ;
		}
		
	//上一页
	@Action(value="preMcPage",results= {
			@Result(name="success",location="/WEB-INF/jsp/management/resource/mcList.jsp")
			
	})
	public String previousMcPage() {
		currentPage = (int) request.getSession().getAttribute("currentPage") ;
		currentPage = currentPage - 1 ;
		if(currentPage <= 0) {
			return "fail" ;
		}
		
		movieChecks = resourceService.findMCsByCheckTag(0, currentPage, MAXRESULTS) ;
		request.getSession().setAttribute("currentPage", currentPage);
		return SUCCESS ;
	}
	//下一页
	@Action(value="nextMcPage",results= {
			@Result(name="success",location="/WEB-INF/jsp/management/resource/mcList.jsp")
			
	})
	public String nextMcPage() {
		currentPage = (int) request.getSession().getAttribute("currentPage") ;
		System.out.println("currentpage:"+currentPage);
		currentPage = currentPage + 1 ;
		
		movieChecks = resourceService.findMCsByCheckTag(0, currentPage, MAXRESULTS) ;
		System.out.println(movieChecks.size());
		if(0 == movieChecks.size()) {
			return "fail" ;
		}
		request.getSession().setAttribute("currentPage", currentPage);
		return SUCCESS ;
		
	}
	//前往某页
	@Action(value="selectMcPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/mcList.jsp")})
	public String selectPage() {
		
		System.out.println(toPage);
		currentPage = toPage ;
		movieChecks = resourceService.findMCsByCheckTag(0, currentPage, MAXRESULTS) ;
		if(0 == movieChecks.size()) {
			return "fail" ;
		}
		request.getSession().setAttribute("currentPage", currentPage);
		return SUCCESS ;
	}
	
	
}
