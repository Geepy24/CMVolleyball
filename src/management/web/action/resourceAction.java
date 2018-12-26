package management.web.action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ObjectUtils.Null;
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
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
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
	//��ҳ����Ŀ��
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
		System.out.println("�ϴ�ͼƬ");
		//��ǰ��Ŀ·��
		String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(uploadFileName);
		System.out.println(upload.toString());
		//�ϴ����ļ�·��
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		String filePath = temp[0]+"pic" ;
		
		
		File file = new File(filePath); 
        if(!file.exists()){ 
            file.mkdir(); 
        } 
		//��ҳ�洫����������ͨ��FileUtils�������ļ�·����,�ļ�����ǰ�˴����uploadFileName
       
//      //��õ�ǰʱ��ĺ���������Ϊ���������뵽Random�Ĺ�������
//        Random rd = new Random(System.currentTimeMillis());
//        int rand = rd.nextInt(1000)+1;//�����������
//        
        FileUtils.copyFile(upload, new File(file,uploadFileName));
        System.out.println(filePath);
        //chrome�ϴ�ʧ�ܣ�����Provisional headers are shown,�����������
		resource.setResName(uploadFileName);
        resource.setAdsName(user.getUserName());
        resource.setUserId(user.getUserId());
        resource.setUserName(user.getUserName());
        resource.setResUri(filePath+uploadFileName);
        
        System.out.println(resource);
		resourceService.saveResource(resource);
        return SUCCESS ; 
		
	}
	
	/**
	 * 	��Դ�б�
	 * @return
	 */
	@Action(value="resList",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
	public String resourceList() {
		
		currentPage = 1 ;
		tag = "pic" ;
		
		
		 //����ҳ��
		//��������
			Long totalItems = resourceService.AllResourceNumber(tag) ;
			Long totalResource ;
			
			//��ҳ��
			if(0 == totalItems) {
				totalResource = new Long(1);
			}else if(0 == totalItems%10) {
				totalResource = totalItems/10 ;
			}else {
				totalResource = (totalItems/10) + 1  ;
			}
			
			//�Ž�session
			
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			//System.out.println(resources.get(0));
			session.setAttribute("totalResource",totalResource );
			
			
		return SUCCESS ;
	}
	//�����б��ҳ��ѡ��ǰ�˲�ͬҳ��ʾ���صĲ�ͬҳ������
		@Action(value="selectPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
		public String selectPage() {
			tag = "pic" ;
			System.out.println(toPage);
			currentPage = toPage ;
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			
			
			return SUCCESS ;
		}
	//��һҳ��Դ�б�
		@Action(value="nextPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
		public String nPage() {
			tag = "pic" ;
			//���ܸı�currentPage�ĵ�ַ����Ȼ�����������Ž�ֵջ����Զ���ʼ���Ǹ���ַ
			int temp = currentPage ;
			temp = temp + 1 ;
			currentPage = temp ;
			
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			
			return SUCCESS ;
		}
		//��һҳ��Դ�б�
		@Action(value="prePage",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resList.jsp")})
		public String pPage() {
			tag = "pic" ;
			int temp = currentPage ;
			temp = temp - 1 ;
			currentPage = temp ;
			
			
			resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
			
			return SUCCESS ;
		}
		//��Դ����
		@Action(value="resDetail",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resDetail.jsp")})
		public String resourceDetail() {
			
			if(request.getAttribute("resId") != null) {
				resource.setResId((Integer)request.getAttribute("resId"));
			}
			
			
			System.out.println("Ҫ��ʾ����Դ��id"+resource.getResId());
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
			
			
			
			return SUCCESS ;
		}
		//��һ��ͼƬ
		@Action(value="nextRes",results= {@Result(name="success",type="chain",location="resDetail")})
		public String nextResource() {
		
			System.out.println("��ǰ��resId��Ҫ������һ��id"+resource.getResId()); ;
			Integer nextId = resourceService.nextResourceId(resource.getResId()) ;
			resource.setResId(nextId);
			request.setAttribute("resId", nextId);
			return SUCCESS ;
		}
		//��һ��ͼƬ
		@Action(value="preRes",results= {@Result(name="success",type="chain",location="resDetail")})
		public String preResource() {
				
			System.out.println("��ǰ��resId��Ҫ������һ��id"+resource.getResId()); ;
			Integer preId = resourceService.preResourceId(resource.getResId()) ;
					
			System.out.println(preId);
			request.setAttribute("resId", preId);
			return SUCCESS ;
		}
		@Action(value="delResource",results= {@Result(name="success",type="json")})
		public String deleteResource() {
			
			System.out.println("Ҫɾ����id"+resource.getResId());
			resourceService.deleteResource(resource.getResId());
			
			
			return SUCCESS ;
		}

}
