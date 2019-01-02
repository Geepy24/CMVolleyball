package com.cm.web.action;

import static org.junit.Assert.fail;

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
 * �û�����Դ���ʶ�����
 * 
 * @author mac
 *
 */

@Namespace("/userResource")
@ParentPackage("p1")
@InterceptorRefs({ @InterceptorRef("loginDefault") })
@Results({ @Result(name = "login", type = "chain", location = "login", params = { "namespace", "/User" }),
		@Result(name = "fail", location = "/fail.jsp") })
public class userResourceAction extends ActionSupport implements Serializable, ModelDriven<Resource> {

	@Autowired
	IResourceService resourceService;
	Resource resource = new Resource();
	private File upload;
	private String uploadFileName;
	private List<Resource> resources;
	private int currentPage;
	private int toPage;
	// ����ͼ������
	private String mpName;

	// ��ҳ����Ŀ��
	private static int MAXRESULTS = 10;
	private String tag;

	HttpSession session = ServletActionContext.getRequest().getSession();
	HttpServletRequest request = ServletActionContext.getRequest();
	private User user = (User) session.getAttribute("loginInfo");

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
	 * ��ҳ��תͼƬ����Ƶ�б�
	 * 
	 * @return
	 */
	@Action(value = "indexresource", results = { @Result(name = "picture", location = "/WEB-INF/jsp/picture.jsp"),
			@Result(name = "movie", location = "/WEB-INF/jsp/movie.jsp") })
	public String indexResource() {
		System.out.println("�����û���Դ��ͼ");
		tag = resource.getResTag();
		System.out.println(tag);
		currentPage = 1;
		session.setAttribute("picOrmov", tag);
		resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS * 2);

		// ����ҳ��
		// ������Դ
		Long totalItems = resourceService.AllResourceNumber(tag);
		Long totalResource;

		// ��ҳ��
		if (0 == totalItems) {
			totalResource = new Long(1);
		} else if (0 == totalItems % 10) {
			totalResource = totalItems / 10;
		} else {
			totalResource = (totalItems / 10) + 1;
		}
		session.setAttribute("totalResource", totalResource);

		if (tag.equals("mov")) {
			// ͨ��mpIdȡ������ͼ�����֣��������ٸ���resourceName������ǰ�˾Ͳ��øĶ���
			// ��ʱresName��������ʾ����ͼ

			for (Resource r : resources) {
				MediaPreview m = r.getMediaPreview();
				if (m == null) {
					continue;
				}
				String mpName = m.getMpName();
				r.setResName(mpName);

			}

			return "movie";
		}
		return "picture";

	}

	/**
	 * ͼƬ����Ƶ��Դ����
	 * 
	 * @return
	 */
	@Action(value = "resDetail", results = {
			@Result(name = "picture", location = "/WEB-INF/jsp/resource/userPicDetail.jsp"),
			@Result(name = "movie", location = "/WEB-INF/jsp/resource/userMovDetail.jsp") })
	public String resourceDetail() {

		if (request.getAttribute("resId") != null) {
			resource.setResId((Integer) request.getAttribute("resId"));
		}

		System.out.println("��user��Ҫ��ʾ����Դ��id" + resource.getResId());
		Resource resourceTemp = new Resource();
		resourceTemp = resourceService.findResourceById(resource.getResId());
		resource.setAdsName(resourceTemp.getUserName());
		resource.setPubTime(resourceTemp.getPubTime());
		resource.setResCom(resourceTemp.getResCom());
		resource.setResName(resourceTemp.getResName());
		resource.setUserName(resourceTemp.getUserName());
		resource.setResTag(resourceTemp.getResTag());
		resource.setResUri(resourceTemp.getResUri());
		resource.setUserId(resourceTemp.getUserId());
		resource.setMediaPreview(resourceTemp.getMediaPreview());

		if (resource.getResTag().equals("mov")) {
			mpName = resource.getMediaPreview().getMpName();
			
			for (Resource r : resources) {
				MediaPreview m = r.getMediaPreview();
				if (m == null) {
					continue;
				}
				String mpName = m.getMpName();
				r.setResName(mpName);
				
				return "movie";
			}
		}

		return "picture";
	}

	/**
	 * ��Դ�б��ҳ��ѡ��ǰ�˲�ͬҳ��ʾ���صĲ�ͬҳ������
	 * @return
	 */
	@Action(value = "selectPage", results = {
			@Result(name="picture", location = "/WEB-INF/jsp/picture.jsp"),
			@Result(name="movie",location="/WEB-INF/jsp/movie.jsp")
	})
	public String selectPage() {
		tag = request.getSession().getAttribute("picOrmov").toString();
		System.out.println(toPage);
		currentPage = toPage;
		resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
		if("mov".equals(tag)) {
			mpName = resource.getMediaPreview().getMpName();
			
			for (Resource r : resources) {
				MediaPreview m = r.getMediaPreview();
				if (m == null) {
					continue;
				}
				String mpName = m.getMpName();
				r.setResName(mpName);
			}
			return "movie" ;
		}else {
			return "picture";
		}
		
	}

	/**
	 * ��һҳ��Դ�б�
	 * @return
	 */
	@Action(value = "nextPage", results = {
			@Result(name="picture", location = "/WEB-INF/jsp/picture.jsp"),
			@Result(name="movie",location="/WEB-INF/jsp/movie.jsp"),
			@Result(name = "fail", location = "/fail.jsp") })
	public String nPage() {
		tag = request.getSession().getAttribute("picOrmov").toString();
		// ���ܸı�currentPage�ĵ�ַ����Ȼ�����������Ž�ֵջ����Զ���ʼ���Ǹ���ַ
		int temp = currentPage;
		temp = temp + 1;
		currentPage = temp;
//		try {
//			
//		}catch(Exception e){
//			
//		}
		resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
		System.out.println(resources.size());
		if (0 == resources.size()) {
			return "fail";
		}
		if("mov".equals(tag)) {
			mpName = resource.getMediaPreview().getMpName();
			
			for (Resource r : resources) {
				MediaPreview m = r.getMediaPreview();
				if (m == null) {
					continue;
				}
				String mpName = m.getMpName();
				r.setResName(mpName);
			}
			return "movie" ;
		}else {
			return "picture";
		}
		
		
	}

	/**
	 * ��һҳ��Դ�б�
	 * @return
	 */
	@Action(value = "prePage", results = {
			@Result(name="picture", location = "/WEB-INF/jsp/picture.jsp"),
			@Result(name="movie",location="/WEB-INF/jsp/movie.jsp"),
			@Result(name = "fail", location = "/fail.jsp") 
		})
	public String pPage() {
		tag = request.getSession().getAttribute("picOrmov").toString();
		int temp = currentPage;
		temp = temp - 1;
		currentPage = temp;
		if(currentPage == -1) {
			return "fail" ;
		}
		resources = resourceService.findAllResource(tag, currentPage, MAXRESULTS);
		if("mov".equals(tag)) {
			mpName = resource.getMediaPreview().getMpName();
			
			for (Resource r : resources) {
				MediaPreview m = r.getMediaPreview();
				if (m == null) {
					continue;
				}
				String mpName = m.getMpName();
				r.setResName(mpName);
			}
			return "movie" ;
		}else {
			return "picture";
		}
		
	}

	/**
	 * ��һ��ͼƬ����Ƶ
	 * @return
	 */
	@Action(value = "nextRes", results = { 
			@Result(name = "success", type="chain",location = "resDetail",params= {"namespace","/userResource"}),
			@Result(name="fail",location="/fail.jsp")
	})
	public String nextResource() {
		//��Ϊ��Ƶ��ͼƬ������ͬһ�ű�����ͼƬ����һ����������Ƶ
		System.out.println("��ǰ��resId��Ҫ������һ��id" + resource.getResId());
		try {
			Integer nextId = resourceService.nextResourceId(resource.getResId());
			resource.setResId(nextId);
			System.out.println("��һ��resource"+resource);
			//����ͨ��result�Ĳ��������ݷ��͸�����һ��action
			
			request.setAttribute("resId", nextId);
			
			return SUCCESS;
		}catch (Exception e) {
			System.out.println("��������һ����Դ");
			return "fail" ;
		}
		
	}

	/**
	 * ��һ��ͼƬ����Ƶ
	 * @return
	 */
	@Action(value = "preRes", results = { 
			@Result(name = "success", type = "chain", location = "resDetail",params= {"namespace","/userResource"}),
			@Result(name="fail",location="/fail.jsp")
	})
	public String preResource() {
		//��Ϊ��Ƶ��ͼƬ������ͬһ�ű�����ͼƬ��һ����������Ƶ
		System.out.println("��ǰ��resId��Ҫ������һ��id" + resource.getResId());
		try {
			Integer preId = resourceService.preResourceId(resource.getResId());
			System.out.println(preId);
			request.setAttribute("resId", preId);
			return SUCCESS;
		}catch(Exception e){
			System.out.println("��������һ����Դid");
			return "fail" ;
		}
		

		
	}

//	@Action(value = "delResource", results = { @Result(name = "success", type = "json") })
//	public String deleteResource() {
//
//		System.out.println("Ҫɾ����id" + resource.getResId());
//		resourceService.deleteResource(resource.getResId());
//
//		return SUCCESS;
//	}

}
