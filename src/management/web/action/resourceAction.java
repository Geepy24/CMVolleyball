package management.web.action;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.result.ResultSetOutput;

import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.web.action.userRegister;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Namespace("/Resource")
@ParentPackage("json-default")
public class resourceAction extends ActionSupport implements Serializable, ModelDriven<Resource> {

	Resource resource = new Resource() ;
	private File upload ;
	private String uploadFileName ;
	
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	private User user =(User) session.getAttribute("loginInfo")  ;
	@Override
	public Resource getModel() {
		return resource;
	}
	
	
	@Action(value="uploadRes",results= {@Result(name="success",location="/WEB-INF/jsp/management/resource/resUpload.jsp")})
	public String uploadResource() {
		
		return SUCCESS ;
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


	@Action(value="addRes",results= {@Result(name="success",location="/index.jsp")})
	public String addResource() throws IOException {
		System.out.println("�ϴ�ͼƬ");
		//��ǰ��Ŀ·��
		String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(uploadFileName);
		System.out.println(upload.toString());
		//�ϴ����ļ�·��
		String filePath = "//usr//local//Cellar//tomcat@7//7.0.92//libexec//pic//" ;
		File file = new File(filePath); 
        if(!file.exists()){ 
            file.mkdir(); 
        } 
		//��ҳ�洫����������ͨ��FileUtils�������ļ�·����,�ļ�����ǰ�˴����uploadFileName
        FileUtils.copyFile(upload, new File(file,uploadFileName));
        System.out.println(filePath);
        //chrome�ϴ�ʧ�ܣ�����Provisional headers are shown
		
        resource.setAdsName(user.getUserName());
        resource.setUserId(user.getUserId());
        resource.setUserName(user.getUserName());
        resource.setResUri(filePath+uploadFileName);
        System.out.println(resource);
		return SUCCESS ; 
		
	}

}
