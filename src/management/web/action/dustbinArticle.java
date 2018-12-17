package management.web.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.Article;
import com.cm.domain.Dustbin;
import com.cm.domain.User;
import com.cm.service.IArticleService;
import com.cm.web.action.privateSourceAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 操作回收站的controller
 * @author Huangjiping
 *
 */
@Namespace("/Article")
@ParentPackage("json-default")
public class dustbinArticle extends ActionSupport implements ModelDriven<Dustbin> {
	private Dustbin dustbin = new Dustbin() ;
	//模型驱动的是dustbi
	private Article article ;
	private String returndata ;
	private Integer currentPage ;
	private List<Dustbin> dustbins ;
	private static Integer MAXRESULTS = 10 ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	//当前用户
	private User user =(User) session.getAttribute("loginInfo")  ;
	@Autowired
	private IArticleService articleService ;
	
	@Override
	public Dustbin getModel() {
		return dustbin;
	}


	public Dustbin getDustbin() {
		return dustbin;
	}


	public List<Dustbin> getDustbins() {
		return dustbins;
	}


	public void setDustbins(List<Dustbin> dustbins) {
		this.dustbins = dustbins;
	}


	public Integer getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	public void setDustbin(Dustbin dustbin) {
		this.dustbin = dustbin;
	}


	public String getReturndata() {
		return returndata;
	}


	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}
	
	
	
	@Action(value="deleteArticle",results= {@Result(name="success",type="json",
			params= {"root","returndata"})})
	public String delete() {
		System.out.println("刚进来的dustbin:"+dustbin);
		//文章内容
		article = articleService.findById(dustbin.getArtId()) ;
		System.out.println("查完之后的article："+article);
		//在article表中删除文章
		articleService.deleteArticle(article);
		dustbin.setArtContent(article.getArtContent());
		System.out.println(dustbin);
		//把要删除的文章保存进dustbin表
		articleService.saveDustbin(dustbin) ;
		
		
		return SUCCESS;
	}
	
	/**
	 * 首页菜单跳转回收站列表
	 */
	@Action(value="dustbinList",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/dustbinList.jsp")})
	public String toDustbinList() {
		
		currentPage = 1 ;
		
		//分页查找所有文章
		
		dustbins = articleService.findAllDustbin(currentPage, MAXRESULTS) ;
		
		//所有草稿
		Long totalItems = articleService.findAllDraft() ;
		Long totalPages ;
		//总页数
		if(0 == totalItems%10) {
			totalPages = totalItems/10 ;
		}else {
			totalPages = (totalItems/10) + 1  ;
		}
		//放进session
		session.setAttribute("totalPages", totalPages);
		
		
		
		
		return SUCCESS ;
	}
	
	
	

}
