package management.web.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.Article;
import com.cm.domain.User;
import com.cm.service.IArticleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
/**
 * 文章的动作类
 * @author Huangjiping
 *
 */
@ParentPackage("json-default")
@Namespace("/Article")
public class articleAction extends ActionSupport implements ModelDriven<Article> {
	
	@Autowired
	IArticleService articleService ;
	
	//模型驱动
	private Article article = new Article() ;
	
	

	//属性驱动
	private List<Article> articles ;
	private int currentPage ;
	private int toPage ;
	private String returndata ;
	private JSONObject json ;
	//private int article_Id ;
	//其他
	private User user ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	//分页的条目数
	private static int MAXRESULTS = 10;
	@Override
	public Article getModel() {

		return article;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public int getToPage() {
		return toPage;
	}

	public void setToPage(int toPage) {
		this.toPage = toPage;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

//	public int getArticle_Id() {
//		return article_Id;
//	}
//
//	public void setArticle_Id(int article_Id) {
//		this.article_Id = article_Id;
//	}
	public Article getArticle() {
		return this.article;
	}

	public String getReturndata() {
		return returndata;
	}

	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

//-------------------动作方法-------------------------------





	//该动作类处理管理员的文章提交
	@Action(value="articleHndler",results= {@Result(name="handle",type="json")},
				params= {"root","article"})
	public String articleHandler() {
		//当前用户，即管理员
		user = (User) session.getAttribute("loginInfo") ;
		System.out.println("当前页面用户"+user);
		System.out.println("模型驱动进来："+article);
		
		article.setAuthorName(user.getUserName());
		article.setAuthorId(user.getUserId());
		article.setAdsName(user.getUserName());
		article.setLastMod(article.getPubTime());
		
		articleService.saveArticle(article);
		
		System.out.println(article);
		
		return "handle" ;
	}
	
	
	//在文章展示模板中显示文章，需要的参数为文章ID
	@Action(value="showArticle",results={@Result(name="success",location="/WEB-INF/jsp/management/article/show.jsp")})
	public String showPage() {
		
		//模型驱动进来，只有artId，根据id查找article
		//System.out.println(article);
		 
		article = articleService.findById(article.getArtId()) ;
		System.out.println("查询结果："+article);
		//查询结果有值，但是值栈中的article对象里没值
		
		return SUCCESS ;
	}

	//文章列表，分页查找后显示，显示的是首页
	@Action(value="articleList",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String showArticleList() {
		//第一页，每页十条
		 
		 currentPage = 1 ;
		 articles = articleService.findAllArticle(currentPage,MAXRESULTS);
		 
		return SUCCESS;
		
	}
	//文章列表的页码选择，前端不同页显示返回的不同页的数据
	@Action(value="selectPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String selectPage() {
		
		System.out.println(toPage);
		currentPage = toPage ;
		articles = articleService.findAllArticle(toPage, MAXRESULTS) ;
		
		
		return SUCCESS ;
	}
	//下一页文章列表
	@Action(value="nextPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String nPage() {
		
		//不能改变currentPage的地址，不然属性驱动被放进值栈的永远是最开始的那个地址
		int temp = currentPage ;
		temp = temp + 1 ;
		currentPage = temp ;
		
		articles = articleService.findAllArticle(currentPage,MAXRESULTS);
		
		return SUCCESS ;
	}
	//上一页文章列表
	@Action(value="prePage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String pPage() {
		
		int temp = currentPage ;
		temp = temp - 1 ;
		currentPage = temp ;
		
		
		articles = articleService.findAllArticle(currentPage,MAXRESULTS);
		
		return SUCCESS ;
	}

	/**
	 * 	页面加载时往新闻、资源栏目里填充数据
	 */
	@Action(value="indexArticle",results= {@Result(name="success",type="json",
					params= {"root","returndata"})})
	public String index() {
		System.out.println("首页请求");
		//调试后发现是query.list()卡住，没有反应
		//这里出问题，没报错没值，可能是spring事务，出错了自动回滚
		//应该不是事务的事，可能刚到首页，没有hibernate没有session
		//可能是session不够用
		articles = articleService.findAllArticle(1, MAXRESULTS) ;
		Map<String , String> map = new LinkedHashMap<>() ;
		for(int i = 0 ; i<articles.size() ; i++) {
			map.put(articles.get(i).getArtId()+"", articles.get(i).getArtTitle()+"."+articles.get(i).getPubTime()) ;  
		}
		//再加上数据条数
		JSONObject json = JSONObject.fromObject(map) ;
		System.out.println(map);
		System.out.println(json);
		returndata = json.toString() ;
		System.out.println(returndata);
		
		return SUCCESS ;
	}
	
	
	
	
}	
