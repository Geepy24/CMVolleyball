package com.cm.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.domain.Movie;
import com.cm.domain.MovieCheck;
import com.cm.domain.Picture;
import com.cm.domain.PictureCheck;
import com.cm.domain.Resource;
import com.cm.domain.User;
import com.cm.service.IArticleService;
import com.cm.service.IResourceService;
import com.cm.service.IUserService;
import com.cm.utils.jsonUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
/**
 * 	用于控制个人空间模块
 * 	type使用json，注意，使用json即不进行跳转操作，进入action再回到页面中时，不会改变值栈的值！
 * 	要手动入栈
 * @author mac
 *
 */

@Namespace("/Persional")
@ParentPackage("p1")
@InterceptorRefs({ @InterceptorRef("loginDefault") })
@Results({ @Result(name = "login", type = "chain", location = "login", params = { "namespace", "/User" }),
	@Result(name = "fail", location = "/fail.jsp") })
public class persionalSpaceAction extends ActionSupport implements ModelDriven<User>{
	
	
	//	request对象
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
	private String pageRef ;
	private int currentPage ;
	private int toPage ;
	private List<Article> articles ;
	private List<Draft> drafts  ;
	private List<Dustbin> dustbins  ;
	private List<Resource> resources  ;
	private List<Picture> pictures ;
	private List<Movie> movies ;
	private String jsonFlag ;
	private int jsonId ;
	private List<PictureCheck> pictureChecks ;
	private List<MovieCheck> movieChecks ;
	
	
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

	


	

	public String getJsonFlag() {
		return jsonFlag;
	}

	public void setJsonFlag(String jsonFlag) {
		this.jsonFlag = jsonFlag;
	}

	public int getJsonId() {
		return jsonId;
	}

	public void setJsonId(int jsonId) {
		this.jsonId = jsonId;
	}

	public int getToPage() {
		return toPage;
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
	}

	public String getPageRef() {
		return pageRef;
	}

	public void setPageRef(String pageRef) {
		this.pageRef = pageRef;
	}
	
	
	
public List<PictureCheck> getPictureChecks() {
		return pictureChecks;
	}

	public void setPictureChecks(List<PictureCheck> pictureChecks) {
		this.pictureChecks = pictureChecks;
	}

	public List<MovieCheck> getMovieChecks() {
		return movieChecks;
	}

	public void setMovieChecks(List<MovieCheck> movieChecks) {
		this.movieChecks = movieChecks;
	}

/**-----------------------------动作方法----------------------------**/
	//首页跳转进入会员页面
	@Action(value="comein",results= {
			@Result(name="success",location="/UI2/index.html")
			
	})
	public String comeIn() {
		
		System.out.println("当前用户进入个人空间："+user);		
		
		
		return SUCCESS ;
		
	}
	@Action(value="sendUser",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String sendUser() {
		
		returndata = user.getUserName() ;
		
		return SUCCESS ;
	}
	//用文章填充首页的表格
	@Action(value="indexArticles",results= {
			@Result(name="success",type= "json",params= {"root","returndata"})
	})
	public String indexArticles() {
		currentPage = 1 ;
		//分页查找
		Article article = new Article() ;
		article.setUser(user);
		articles = articleService.findByUser(article, currentPage, MAXRESULTS) ;
		request.getSession().setAttribute("currentPage", currentPage);
		
		//json字符串的格式是{"i" : "artTitle+###+pubTime###lastMod###artId"}
		returndata = jsonUtils.artListToJsonString(articles) ;
		
		return SUCCESS ;
	}
//	//去管理文章
//	@Action(value="toArtList",results= {@Result(name="success",location="/UI/artList.jsp")})
//	public String toArticleList() {
//		currentPage = 1 ;
//		request.getSession().setAttribute("currentPage", currentPage);
//		return SUCCESS;
//	}
	//新增文章
	@Action(value="userAddArt",results= {
			@Result(name="success",location="/UI/editor.jsp")
	})
	public String userAddArticle() {
		return SUCCESS;
	}
//	//去草稿箱
//	@Action(value="toDraftList",results= {@Result(name="success",location="/UI/draftList.jsp")})
//	public String toDraftList() {
//		
//		
//		return SUCCESS ;
//	}
	//用草稿填充草稿箱的表格
	@Action(value="draList",results= {
			@Result(name="success",type= "json",params= {"root","returndata"})
	})
	public String draftList() {
		currentPage = 1 ;
		request.getSession().setAttribute("currentPage", currentPage);
		Draft draft = new Draft() ;
		draft.setUser(user);
		
		//分页查找
		drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
		
		//json字符串的格式是{"i" : "artTitle###lastMod###draId"}
		returndata = jsonUtils.draListToJsonString(drafts) ;
		
		return SUCCESS ;
	}
	
	
//	//前往回收站
//	@Action(value="toDustbinList",results= {@Result(name="success",location="/UI/dustbinList.jsp")})
//	public String toDustbinList() {
//		
//		return SUCCESS ;
//	}
	//用回收站填充草稿箱的表格
		@Action(value="dustList",results= {
				@Result(name="success",type= "json",params= {"root","returndata"})
		})
		public String dustbinList() {
			
			currentPage = 1 ;
			request.getSession().setAttribute("currentPage", currentPage);
			Dustbin  dustbin = new Dustbin() ;
			dustbin.setUser(user);
			
			//分页查找
			dustbins = articleService.findAllDustbinByUser(dustbin,currentPage,MAXRESULTS) ;
			
			//json字符串的格式：{"i" : "artTitle###delTime###dustId"}
			returndata = jsonUtils.dsutListToJsonString(dustbins) ;
			
			return SUCCESS ;
		}
	
	//上一页
	@Action(value="prePage",results= {
			@Result(name="success",type="json",params= {"root","returndata"}),
			@Result(name="fail",type="json",params= {"root","returndata"})
	})
	public String previousPage() {
		//currentPage,pageRef
		currentPage = (int) request.getSession().getAttribute("currentPage") ;
		currentPage = currentPage - 1 ;
		if(currentPage <=0 ) {
			returndata = "error : 没有更多了！" ;
			return "fail" ;
		}
		
		if(pageRef.equals("dustbin")) {
			Dustbin dustbin = new Dustbin() ;
			dustbin.setUser(user);
			dustbins = articleService.findAllDustbinByUser(dustbin, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.dsutListToJsonString(dustbins) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
		}
		if(pageRef.equals("article")) {
			Article article = new Article() ;
			article.setUser(user);
			articles = articleService.findByUser(article, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.artListToJsonString(articles) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
		}
		if(pageRef.equals("draft")) {
			Draft draft = new Draft() ;
			draft.setUser(user);
			drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.draListToJsonString(drafts) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		if(pageRef.equals("movies")) {
			resources = resourceService.findAllResource("mov", user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.movieListToJsonString(resources) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		if(pageRef.equals("photos")) {
			resources = resourceService.findAllResource("pic", currentPage, MAXRESULTS) ;
			returndata = jsonUtils.pictureListToJsonString(resources) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		if(pageRef.equals("MCs")) {
			movieChecks = resourceService.findMCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.mcListToJsonString(movieChecks) ;
	 		request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		if(pageRef.equals("PCs")) {
			pictureChecks = resourceService.findPCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.pcListToJsonString(pictureChecks) ;
			request.getSession().setAttribute("currentPage", currentPage);
			return SUCCESS ;
			
		}
		
		
		
		return "fail" ;
	}
		
	//下一页
	@Action(value="nPage",results= {
			@Result(name="success",type="json",params= {"root","returndata"}),
			@Result(name="fail",type="json",params= {"root","returndata"})
	})
	public String nextPage() {
		//currentPage,pageRef
			currentPage = (int) request.getSession().getAttribute("currentPage");
			System.out.println("页面标记："+pageRef);
			currentPage = currentPage + 1 ;
				
				System.out.println("下一页"+currentPage);
				
				if(pageRef.equals("dustbin")) {
				Dustbin dustbin = new Dustbin() ;
				dustbin.setUser(user);
				dustbins = articleService.findAllDustbinByUser(dustbin, currentPage, MAXRESULTS) ;
					if(dustbins.size() == 0) {
						returndata = "error : 没有更多了！" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.dsutListToJsonString(dustbins) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("article")) {
					Article article = new Article() ;
					article.setUser(user);
					articles = articleService.findByUser(article, currentPage, MAXRESULTS) ;
					if(articles.size() == 0) {
						returndata = "error : 没有更多了！" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.artListToJsonString(articles) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("draft")) {
					Draft draft = new Draft() ;
					draft.setUser(user);
					drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
					if(drafts.size() == 0) {
						returndata = "error : 没有更多了！" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.draListToJsonString(drafts) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("movies")) {
					
					resources = resourceService.findAllResource("mov", user.getUserId(), currentPage, MAXRESULTS) ;
					if(resources.size() == 0) {
						returndata = "error : 没有更多了！" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.movieListToJsonString(resources) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("photos")) {
					
					resources = resourceService.findAllResource("pic", currentPage, MAXRESULTS) ;
					if(resources.size() == 0) {
						returndata = "error : 没有更多了！" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.pictureListToJsonString(resources) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("MCs")) {
					movieChecks = resourceService.findMCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
					if(movieChecks.size() == 0) {
						returndata = "error : 没有更多了！" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.mcListToJsonString(movieChecks) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
				if(pageRef.equals("PCs")) {
					pictureChecks = resourceService.findPCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
					if(pictureChecks.size() == 0) {
						returndata = "error : 没有更多了！" ;
						return "fail" ;
					}else {
						returndata = jsonUtils.pcListToJsonString(pictureChecks) ;
						request.getSession().setAttribute("currentPage", currentPage);
						return SUCCESS ;
					}
					
				}
		return "fail" ;
	}
	
	
	//前往某页
	@Action(value="sPage",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String selectPage() {
		currentPage = toPage ;
		System.out.println(pageRef);
		request.getSession().setAttribute("currentPage", currentPage);
		System.out.println(currentPage);
		if(pageRef.equals("article")) {
			Article article = new Article() ;
			article.setUser(user);
			articles = articleService.findByUser(article, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.artListToJsonString(articles) ;
			return SUCCESS ;
		}
		if(pageRef.equals("draft")) {
			Draft draft = new Draft() ;
			draft.setUser(user);
			drafts = articleService.findAllDraft(draft, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.draListToJsonString(drafts) ;
			return SUCCESS ;
		}
		if(pageRef.equals("dustbin")) {
			Dustbin dustbin = new Dustbin() ;
			dustbin.setUser(user);
			dustbins = articleService.findAllDustbinByUser(dustbin, currentPage, MAXRESULTS) ;
			returndata = jsonUtils.dsutListToJsonString(dustbins) ;
			return SUCCESS ;
		}
		if(pageRef.equals("photos")) {
			
			resources = resourceService.findAllResource("pic", user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.pictureListToJsonString(resources) ;
			return SUCCESS ;
		}
		if(pageRef.equals("movies")) {
			resources = resourceService.findAllResource("mov", user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.movieListToJsonString(resources) ;
			return SUCCESS ;
		}
		if(pageRef.equals("PCs")) {
			
			pictureChecks = resourceService.findPCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.pcListToJsonString(pictureChecks) ;
			return SUCCESS ;
		}
		if(pageRef.equals("MCs")) {
			movieChecks = resourceService.findMCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			returndata = jsonUtils.mcListToJsonString(movieChecks) ;
			return SUCCESS ;
		}
	return "fail" ;
		
	}
		
	//全部页码 
	@Action(value="pages",results= {
			@Result(name="success",type="json",params= {"root","returndata"})

	})
	public String pages() {
		Long number = 1l ;
		Long pages ;
		System.out.println("来源："+pageRef);
		//文章
		if(pageRef.equals("article")) {
			number = articleService.AllArticleNumber(user.getUserId()) ;
		}
		//草稿
		if(pageRef.equals("draft")) {
			 number = articleService.AllDraftNumber(user.getUserId()) ;
		}
		//回收站
		if(pageRef.equals("dustbin")) {
			number = articleService.AllDustbinNumber(user.getUserId()) ;
		}
		//相册,通过外键查找总数
		if(pageRef.equals("photos")) {
			number = resourceService.AllResourceNumber(user, "pic") ;
		}
		//视频，通过外键查找总数
		if(pageRef.equals("movies")) {
			number = resourceService.AllResourceNumber(user, "mov") ;
			System.out.println("check："+number);
		}
		//相册审核列表
		if(pageRef.equals("PCs")) {
			number = resourceService.findAllPcsByUserId(user.getUserId()) ;
		}
		//视频审核列表
		if(pageRef.equals("MCs")) {
			number = resourceService.findAllMcsByUserId(user.getUserId()) ;
		}
		
		if(0 == number) {
			pages = 1l ;
		}else if( 0 == number % MAXRESULTS ) {
			pages = number / MAXRESULTS ;
		}else {
			pages = number / MAXRESULTS +1 ;
		}
		System.out.println(number);
		System.out.println("总页数："+pages);
		returndata = String.valueOf(pages) ;
		return SUCCESS ;
	}
	//当前页码
	@Deprecated
	@Action(value="thePage",results= {
			@Result(name="success",type="json",params= {"root","returndata"})

	})
	public String thePage() {
		
		System.out.println(pageRef);
		if(pageRef.equals("previous")) {
			
		}
		if(pageRef.equals("next")) {
			
		}
		
		returndata = String.valueOf((int)request.getSession().getAttribute("currentPage")+1) ;
		
		return SUCCESS ;
	}
	
	
	//编辑文章，包括文章，回收站，草稿。草稿的编辑的save应该是修改，更新操作。其他不用更改
	@Action(value="edit",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
	})
	public String Edit() {
		System.out.println("要编辑的内容:"+jsonFlag+"-"+jsonId);
		Map<String, String> map = new HashMap<String, String>() ;
		String title = "" ;
		String content = "" ; 
		if(jsonFlag.equals("article")) {
			Article article = articleService.findById(jsonId) ;
			title = article.getArtTitle() ;
			content = article.getArtContent() ;
			
		}if(jsonFlag.equals("draft")) {
			Draft draft = articleService.findDraftById(jsonId) ;
			title = draft.getArtTitle() ;
			content = draft.getArtContent() ;
		}if(jsonFlag.equals("dustbin")) {
			Dustbin dustbin = articleService.findDustbinById(jsonId) ;
			title = dustbin.getArtTitle() ;
			content = dustbin.getArtContent() ;
			
		}
		map.put("artTitle", title) ;
		map.put("artContent", content) ;
		JSONObject jsonObject = JSONObject.fromObject(map) ;
		returndata = jsonObject.toString() ;
		return SUCCESS ;
		
	}
	//----------------------------显示用户的视频，图片列表----------------------
	//显示用户的视频列表
	@Action(value="movList",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
			
	})
	public String movieList() {
		
		currentPage = 1 ;
		resources = resourceService.findAllResource("mov", user.getUserId(), currentPage, MAXRESULTS) ;
		
		returndata = jsonUtils.movieListToJsonString(resources) ;
		
		return SUCCESS ;
	}
	//显示用户的图片列表
		@Action(value="picList",results= {
				@Result(name="success",type="json",params= {"root","returndata"})
				
		})
		public String pictureList() {
			
			currentPage = 1 ;
			resources = resourceService.findAllResource("pic", user.getUserId(), currentPage, MAXRESULTS) ;
			
			returndata = jsonUtils.pictureListToJsonString(resources) ;
			
			return SUCCESS ;
		}
	
	//----------------------------显示用户的视频，图片审核列表----------------------
	//显示用户的待审核视频列表
		@Action(value="userMcList",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
				
	})
	public String userMovieCheckList() {
			
		currentPage = 1 ;
		movieChecks = resourceService.findMCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
			
		returndata = jsonUtils.mcListToJsonString(movieChecks) ;
		return SUCCESS ;
	}	
	//显示用户的待审核图片列表
	@Action(value="userPcList",results= {
			@Result(name="success",type="json",params= {"root","returndata"})
			
	})
	public String userPictureCheckList() {
		
		currentPage = 1 ;
		pictureChecks = resourceService.findPCsByUserId(user.getUserId(), currentPage, MAXRESULTS) ;
		
		returndata = jsonUtils.pcListToJsonString(pictureChecks) ;
		
		return SUCCESS ;
	}
}
