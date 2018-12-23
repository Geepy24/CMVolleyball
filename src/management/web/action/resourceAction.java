package management.web.action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.service.IResourceService;
import com.cm.web.action.userRegister;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/Resource")
@ParentPackage("json-default")
public class resourceAction extends ActionSupport implements Serializable, ModelDriven<Resource> {

	@Autowired
	IResourceService resourceService ;
	Resource resource = new Resource() ;
	private File upload ;
	private String uploadFileName ;
	private List<Resource> resources ;
	private int currentPage ;
	private int toPage ;
	//分页的条目数
		private static int MAXRESULTS = 10;
	private String tag ;
	
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
	private User user =(User) session.getAttribute("loginInfo")  ;
	
	
	
	@Override
	public Resource getModel() {
		return resource;
	}
	
	
	

	public List<Resource> getResources() {
		return resources;
	}




	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}




	public int getCurrentPage() {
		return currentPage;
	}




	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}




	public int getToPage() {
		return toPage;
	}




	public void setToPage(int toPage) {
		this.toPage = toPage;
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
	
	@Action(value="uploadRes",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resUpload.jsp")})
	public String uploadResource() {
		
		return SUCCESS ;
	}

	@Action(value="addRes",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/addSuccess.jsp")})
	public String addResource() throws IOException {
		System.out.println("上传图片");
		//当前项目路径
		String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(uploadFileName);
		System.out.println(upload.toString());
		//上传的文件路径
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		String filePath = temp[0]+"pic\\" ;
		
		
		File file = new File(filePath); 
        if(!file.exists()){ 
            file.mkdir(); 
        } 
		//将页面传过来的数据通过FileUtils拷贝到文件路径下,文件名是前端传入的uploadFileName
        FileUtils.copyFile(upload, new File(file,uploadFileName));
        System.out.println(filePath);
        //chrome上传失败，出现Provisional headers are shown,设置最大下载
		
        resource.setAdsName(user.getUserName());
        resource.setUserId(user.getUserId());
        resource.setUserName(user.getUserName());
        resource.setResUri(filePath+uploadFileName);
        System.out.println(resource);
		return SUCCESS ; 
		
	}
	
	/**
	 * 	资源列表
	 * @return
	 */
	@Action(value="resList",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
	public String resourceList() {
		
		currentPage = 1 ;
		tag = "pic" ;
		
		
		 //加上页码
		//所有文章
			Long totalItems = resourceService.AllResourceNumber(tag) ;
			Long totalResource ;
			
			//总页数
			if(0 == totalItems) {
				totalResource = new Long(1);
			}else if(0 == totalItems%10) {
				totalResource = totalItems/10 ;
			}else {
				totalResource = (totalItems/10) + 1  ;
			}
			//放进session
			
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			//System.out.println(resources.get(0));
			
			session.setAttribute("totalResource",totalResource );
		 
		return SUCCESS ;
	}
	//文章列表的页码选择，前端不同页显示返回的不同页的数据
		@Action(value="selectPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
		public String selectPage() {
			tag = "pic" ;
			System.out.println(toPage);
			currentPage = toPage ;
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			
			
			return SUCCESS ;
		}
	//下一页资源列表
		@Action(value="nextPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
		public String nPage() {
			tag = "pic" ;
			//不能改变currentPage的地址，不然属性驱动被放进值栈的永远是最开始的那个地址
			int temp = currentPage ;
			temp = temp + 1 ;
			currentPage = temp ;
			
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			
			return SUCCESS ;
		}
		//上一页资源列表
		@Action(value="prePage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
		public String pPage() {
			tag = "pic" ;
			int temp = currentPage ;
			temp = temp - 1 ;
			currentPage = temp ;
			
			
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			
			return SUCCESS ;
		}

}
