package management.web.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
/**
 * 操作回收站的controller
 * @author Huangjiping
 *
 */
@Namespace("/Article")
@ParentPackage("json-default")
@Result(name="fail",location="/fail.jsp")
public class dustbinAction extends ActionSupport implements ModelDriven<Dustbin> {
	private Dustbin dustbin = new Dustbin() ;
	//模型驱动的是dustbin
	private Article article ;
	private String returndata ;
	private Integer currentPage ;
	private List<Dustbin> dustbins ;
	private static Integer MAXRESULTS = 10 ;
	private Integer toPage ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	HttpServletRequest request = ServletActionContext.getRequest() ;
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


	public Integer getToPage() {
		return toPage;
	}


	public void setToPage(Integer toPage) {
		this.toPage = toPage;
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
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 删除,在articleAction中已给出
	 * @return
	 
	@Action(value="deleteArticle",results= {@Result(name="success",type="json",
			params= {"root","returndata"})})
	public String delete() {
		System.out.println("刚进来的dustbin:"+dustbin);
		//文章内容
		article = articleService.findById(dustbin.getUser().getUserId()) ;
		System.out.println("查完之后的article："+article);
		//在article表中删除文章
		articleService.deleteArticle(article);
		dustbin.setArtContent(article.getArtContent());
		System.out.println(dustbin);
		//把要删除的文章保存进dustbin表
		articleService.saveDustbin(dustbin) ;
		
		
		return SUCCESS;
	}
	*/
	/**
	 * 首页菜单跳转回收站列表
	 */
	@Action(value="dustbinList",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/dustbinList.jsp")})
	public String toDustbinList() {
		
		currentPage = 1 ;
		
		//分页查找所有文章
		
		dustbins = articleService.findAllDustbin(currentPage, MAXRESULTS) ;
		
		//System.out.println(dustbins.get(1));
		//所有草稿
		Long totalItems = articleService.AllDustbinNumber() ;
		System.out.println(totalItems);
		Long totalDustbins ;
		//总页数
		if(0 == totalItems) {
			totalDustbins = new Long(1);
		}else if(0 == totalItems%10) {
			totalDustbins = totalItems/10 ;
		}else {
			totalDustbins = (totalItems/10) + 1  ;
		}
		//放进session
		session.setAttribute("totalDustbins", totalDustbins);
		
		
		
		
		return SUCCESS ;
	}
	
	
	/**
	 * 	彻底删除,要先解除关联关系，再删除,在dao中
	 */
	@Action(value="compDelete",results= {@Result(name="success",type="chain",location="dustbinList")})
	public String cDelete() {
		
		articleService.deleteDustbin(dustbin.getDustId()) ;
		
		
		return SUCCESS ;
	}
	
	/**
	 * 	删除一页
	 */
	@Action(value="delePage",results={
			@Result(name="success",type="chain",location="dustbinList"),
			@Result(name="error",location="fail.jsp")
	})
	public String delePage() {
		
		System.out.println(currentPage);
		
		dustbins = articleService.findAllDustbin(currentPage, MAXRESULTS) ;
		try {
			Iterator<Dustbin> iterator = dustbins.iterator() ;
			while(iterator.hasNext()) {
				articleService.deleteDustbin(iterator.next().getDustId());
			}
		}catch(Exception e){
			e.printStackTrace();
			return ERROR ;
		}
		
		return SUCCESS ;
	}
	//下一页文章列表
	@Action(value="nextDustbin",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/dustbinList.jsp")})
	public String nPage() {
		
		//不能改变currentPage的地址，不然属性驱动被放进值栈的永远是最开始的那个地址
		int temp = currentPage ;
		temp = temp + 1 ;
		currentPage = temp ;
		
			dustbins = articleService.findAllDustbin(currentPage, MAXRESULTS) ;
			if(dustbins.size() == 0) {
				return "fail" ;
			}
			return SUCCESS ;
		
	}
	//上一页文章列表
	@Action(value="preDustbin",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/dustbinList.jsp")})
	public String pPage() {
		
		int temp = currentPage ;
		temp = temp - 1 ;
		currentPage = temp ;
		if(currentPage <= 0) {
			return "fail" ;
		}
		
		
		dustbins = articleService.findAllDustbin(currentPage, MAXRESULTS) ;
		
	
		
		return SUCCESS ;
	}
	//文章列表的页码选择，前端不同页显示返回的不同页的数据
	@Action(value="selectDustbinPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/dustbinList.jsp")})
	public String selectPage() {
		
		System.out.println(toPage);
		currentPage = toPage ;
		try {
			dustbins = articleService.findAllDustbin(currentPage, MAXRESULTS) ;
			return SUCCESS ;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail" ;
		}
	
	}
	/**
	 * 跳转到编辑页面
	 */
	@Action(value="toDustEdit",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/editArticle.jsp")})
	public String toDustbinEdit() {
		
		
		System.out.println(dustbin.getDustId());
		session.setAttribute("editDustId", dustbin.getDustId());
		
		
		return SUCCESS ;
	}
	@Action(value="dustEdit",results= {@Result(name="success",type="json",
			params= {"root","returndata"})})
	public String dustEdit() {
		Dustbin dustbinTemp = new Dustbin() ;
		
		System.out.println(session.getAttribute("editDustId"));

		dustbinTemp = articleService.findDustbinById((Integer)session.getAttribute("editDustId")) ;
		
		Map<String, String> map = new HashMap<>() ;
		map.put("dustId", String.valueOf(dustbinTemp.getDustId())) ;
		map.put("artId", String.valueOf(dustbinTemp.getArtId())) ;
		map.put("artContent", dustbinTemp.getArtContent()) ;
		map.put("artTitle",	dustbinTemp.getArtTitle()) ;
		map.put("authorName", dustbinTemp.getUser().getUserName()) ;
		map.put("delTime,", dustbinTemp.getDelTime()) ;
		
		JSONObject json = JSONObject.fromObject(map) ;
		returndata = json.toString() ;
		
		System.out.println(returndata);
		return SUCCESS ;
	}


	
	
}
