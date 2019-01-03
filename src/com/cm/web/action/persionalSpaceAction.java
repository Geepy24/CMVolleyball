package com.cm.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.domain.Movie;
import com.cm.domain.Picture;
import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.service.IArticleService;
import com.cm.service.IResourceService;
import com.cm.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
/**
 * 	用于控制个人空间模块
 * @author mac
 *
 */

@Namespace("/Persional")
@ParentPackage("p1")
@InterceptorRefs({ @InterceptorRef("loginDefault") })
@Results({ @Result(name = "login", type = "chain", location = "login", params = { "namespace", "/User" }),
	@Result(name = "fail", location = "/fail.jsp") })
public class persionalSpaceAction extends ActionSupport implements ModelDriven<User>{

	private HttpServletRequest request = ServletActionContext.getRequest() ;
	
	private String returndata ;
	private User user =  (User) request.getSession().getAttribute("loginInfo")  ;
	@Autowired
	IUserService userService ;
	@Autowired
	IArticleService articleService ;
	@Autowired
	IResourceService resourceService ;
	

	private  static final Integer MAXRESULTS = 10 ;
	private Integer currentPage  ;
	private List<Article> articles ;
	private List<Draft> drafts  ;
	private List<Dustbin> dustbins  ;
	private List<Resource> resources  ;
	private List<Picture> pictures ;
	private List<Movie> movies ;
	
	@Override
	public User getModel() {
		return user;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Draft> getDrafts() {
		return drafts;
	}

	public void setDrafts(List<Draft> drafts) {
		this.drafts = drafts;
	}

	public List<Dustbin> getDustbins() {
		return dustbins;
	}

	public void setDustbins(List<Dustbin> dustbins) {
		this.dustbins = dustbins;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	//首页跳转进入会员页面
	@Action(value="comein",results= {
			@Result(name="success",location="/UI/user.jsp")
			
	})
	public String comeIn() {
		
		System.out.println("当前用户进入个人空间："+user);		
		
		
		return SUCCESS ;
		
	}
	//用文章填充首页的表格
	@Action(value="indexArticles",results= {
			@Result(name="success",type= "json",params= {"root","returndata"})
	})
	public String indexArticles() {
		currentPage = 1 ;
		//分页查找
		articles = articleService.findByUserId(user, currentPage, MAXRESULTS) ;
		
		
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<Article> iterator = articles.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Article article = iterator.next() ;
			String artTitle = article.getArtTitle() ;
			String pubTime = article.getPubTime() ;
			String lastMod = article.getLastMod() ;
			String artId = String.valueOf(article.getArtId()) ;
			System.out.println(i+"-"+artTitle);
			//合并在一个String中，通过###分割
			String content = artTitle+"###"+pubTime+"###"+lastMod+"###"+artId ;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(map) ;
		
		returndata = jsonObject.toString() ;
		
		return SUCCESS ;
	}
	//新增文章
	@Action(value="userAddArt",results= {
			@Result(name="success",location="/UI/editor.jsp")
	})
	public String userAddArticle() {
		return SUCCESS;
	}
	//去草稿箱
	@Action(value="toDraftList",results= {@Result(name="success",location="/UI/draftList.jsp")})
	public String toDraftList() {
		
		
		return SUCCESS ;
	}//用草稿填充草稿箱的表格
	@Action(value="draList",results= {
			@Result(name="success",type= "json",params= {"root","returndata"})
	})
	public String draftList() {
		currentPage = 1 ;
		Draft draft = new Draft() ;
		draft.setUser(user);
		
		//分页查找
		drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
		
		
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<Draft> iterator = drafts.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Draft draft2 = iterator.next() ;
			String artTitle = draft2.getArtTitle() ;
			String lastMod = draft2.getLastMod() ;
			String draId = String.valueOf(draft2.getDraId()) ;
			System.out.println(i+"-"+artTitle);
			//合并在一个String中，通过###分割
			String content = artTitle+"###"+lastMod+"###"+ draId;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
		
		
		JSONObject jsonObject = JSONObject.fromObject(map) ;
		
		returndata = jsonObject.toString() ;
		
		return SUCCESS ;
	}
	
}
