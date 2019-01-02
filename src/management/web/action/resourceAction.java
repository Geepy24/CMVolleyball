package management.web.action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.service.IResourceService;
import com.cm.utils.movieUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 管理系统的资源访问动作类
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
public class resourceAction extends ActionSupport implements Serializable, ModelDriven<Resource> {

	@Autowired
	IResourceService resourceService ;
	Resource resource = new Resource() ;
	private File upload ;
	private String uploadFileName ;
	private List<Resource> resources ;
	private int currentPage ;
	private int toPage ;
	//缩略图的名字
	private String mpName ;
	
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




	public String getMpName() {
		return mpName;
	}




	public void setMpName(String mpName) {
		this.mpName = mpName;
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

	/**
	 * 转到新增资源视图
	 * @return
	 */
	@Action(value="uploadRes",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resUpload.jsp")})
	public String uploadResource() {
		
		return SUCCESS ;
	}
	/**
	 * 增加资源：
	 * 	对于图片文件，直接保存即可
	 * 	对于视频文件，要生成视频缩略图，并保存缩略图信息到缩略图的表中
	 * @return
	 * @throws IOException
	 */
	@Action(value="addRes",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/addSuccess.jsp")})
	public String addResource() throws IOException {
		
		
		
		System.out.println("上传图片");
		//当前项目路径
		String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(uploadFileName);
		System.out.println(upload.toString());
		//上传的文件路径
		String filePath = "" ;
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		System.out.println("资源类别"+resource.getResTag());
		
		
		if(resource.getResTag().equals("mov")) {
			//存到视频文件夹
			filePath = temp[0]+"mov" ;
			//存入标记
			request.getSession().setAttribute("picOrmov","mov");
		}else {
			//存到图片文件夹
			 filePath = temp[0]+"pic" ;
			 request.getSession().setAttribute("picOrmov","mov");
		}
		
		System.out.println(filePath);
		
		File file = new File(filePath); 
        if(!file.exists()){ 
            file.mkdir(); 
        } 
        
		//将页面传过来的数据通过FileUtils拷贝到文件路径下,文件名是前端传入的uploadFileName
        FileUtils.copyFile(upload, new File(file,uploadFileName));
        System.out.println(filePath);

        //保存到数据库中
        //chrome上传失败，出现Provisional headers are shown,设置最大下载
		resource.setResName(uploadFileName);
        resource.setAdsName(user.getUserName());
        resource.setUserId(user.getUserId());
        resource.setUserName(user.getUserName());
        resource.setResUri(filePath+"/"+uploadFileName);
        //是图片，则直接保存
        if(resource.getResTag().equals("piv")) {
        	resourceService.saveResource(resource);
            System.out.println("保存了图片："+resource);
        	return SUCCESS ; 
        }else {
        	//否则是视频，就要生成缩略图，同时将缩略图id作为外键保存
    		//设置缩略图表，并级联保存
			
			String mpPath = temp[0]+"media_preview" ;
			File mpFile = new File(mpPath); 
	        if(!mpFile.exists()){ 
	            mpFile.mkdir(); 
	            }
	        //要输出的jpg文件的uri
	        String mpName = resource.getResName().split("\\.")[0] + ".jpg";
	        String mpUri = mpPath+"/"+mpName;
	        
	        System.out.println(mpUri+"---"+mpName);
			movieUtils.handler(movieUtils.ffmpegPath, resource.getResUri(),mpUri );
			
			MediaPreview mediaPreview = new MediaPreview() ;
			mediaPreview.setMpName(mpName);
			mediaPreview.setMpUri(mpUri);
			//与resource对象关联，然后级联保存
			resource.setMediaPreview(mediaPreview);
			System.out.println("保存了视频："+resource);
			System.out.println("保存了缩略图："+mediaPreview);
    		
			resourceService.saveResource(resource);
			return SUCCESS ; 
        }
		
	}
	
	/**
	 * 菜单的图片/视频列表选择
	 */
	@Action(value="list",results= {@Result(name="success",type="chain",location="resList")})
	public String toList() {
		System.out.println(resource.getResTag());
		if (resource.getResTag().equals("pic")) {
			request.getSession().setAttribute("picOrmov", "pic");
		}else {
			request.getSession().setAttribute("picOrmov", "mov");
			
		}
		
		
		return SUCCESS ;
		
	}
	
	/**
	 * 	资源列表
	 * @return
	 */
	@Action(value="resList",results= {
			@Result(name="success",location="/WEB-INF/jsp/management/resource/picList.jsp"),
			@Result(name="movie",location="/WEB-INF/jsp/management/resource/movList.jsp")
	})
	public String resourceList() {
		
		currentPage = 1 ;
		try {
			tag = request.getSession().getAttribute("picOrmov").toString();
		}catch(NullPointerException e){
			tag = resource.getResTag() ;
		}
		

		
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
			if(tag.equals("mov")) {
				for (Resource r : resources) {
					MediaPreview m = r.getMediaPreview();
					if(m == null) {
						continue ;
					}
					String mpName =	m.getMpName() ;
					r.setResName(mpName);
					
				}
				return "movie" ;
			}
			
			//System.out.println(resources.get(0));
			session.setAttribute("totalResource",totalResource );
			
			
		return SUCCESS ;
	}
	//文章列表的页码选择，前端不同页显示返回的不同页的数据
		@Action(value="selectPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
		public String selectPage() {
			tag = request.getSession().getAttribute("picOrmov").toString();
			System.out.println(toPage);
			currentPage = toPage ;
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			
			
			return SUCCESS ;
		}
	//下一页资源列表
		@Action(value="nextPage",results= {
				@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp"),
				@Result(name="fail",location="/fail.jsp")
		})
		public String nPage() {
			tag = request.getSession().getAttribute("picOrmov").toString();
			//不能改变currentPage的地址，不然属性驱动被放进值栈的永远是最开始的那个地址
			int temp = currentPage ;
			temp = temp + 1 ;
			currentPage = temp ;
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			System.out.println(resources.size());
			if(0 == resources.size()) {
				return "fail" ;
			}
			return SUCCESS ;
		}
		//上一页资源列表
		@Action(value="prePage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
		public String pPage() {
			tag = request.getSession().getAttribute("picOrmov").toString();
			int temp = currentPage ;
			temp = temp - 1 ;
			currentPage = temp ;
			
			
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			
			return SUCCESS ;
		}
		//资源详情
		@Action(value="resDetail",results= {
				@Result(name="success",location="/WEB-INF/jsp/management/resource/picDetail.jsp"),
				@Result(name="movie",location="/WEB-INF/jsp/management/resource/movDetail.jsp")
		})
		public String resourceDetail() {
			
			
			if(request.getAttribute("resId") != null) {
				resource.setResId((Integer)request.getAttribute("resId"));
			}
			
			System.out.println("要显示的资源的id"+resource.getResId());
			Resource resourceTemp = new Resource() ;
			resourceTemp = resourceService.findResourceById(resource.getResId()) ;
			resource.setAdsName(resourceTemp.getUserName());
			resource.setPubTime(resourceTemp.getPubTime());
			resource.setResCom(resourceTemp.getResCom());
			resource.setResName(resourceTemp.getResName());
			resource.setUserName(resourceTemp.getUserName());
			resource.setResTag(resourceTemp.getResTag());
			resource.setResUri(resourceTemp.getResUri());
			resource.setUserId(resourceTemp.getUserId());
			resource.setMediaPreview(resourceTemp.getMediaPreview());
			if(resource.getResTag().equals("mov")) {
				mpName = resource.getMediaPreview().getMpName() ;
				return "movie" ;
			}
			
			return SUCCESS ;
		}
		//下一张图片或视频
		@Action(value="nextRes",results= {@Result(name="success",type="chain",location="resDetail")})
		public String nextResource() {
		
			System.out.println("当前的resId，要查找下一个id"+resource.getResId()); ;
			Integer nextId = resourceService.nextResourceId(resource.getResId()) ;
			resource.setResId(nextId);
			//图片和视频保存在一起，导致下一条id是mov，要再加判断条件，或者重构数据库表
			request.setAttribute("resId", nextId);
			
			return SUCCESS ;
		}
		//上一张图片或视频
		@Action(value="preRes",results= {@Result(name="success",type="chain",location="resDetail")})
		public String preResource() {
				
			System.out.println("当前的resId，要查找上一个id"+resource.getResId()); ;
			Integer preId = resourceService.preResourceId(resource.getResId()) ;
					
			System.out.println(preId);
			request.setAttribute("resId", preId);
			return SUCCESS ;
		}
		@Action(value="delResource",results= {@Result(name="success",type="json")})
		public String deleteResource() {
			
			System.out.println("要删除的id"+resource.getResId());
			resourceService.deleteResource(resource.getResId());
			
			
			return SUCCESS ;
		}
		
		
		
}
