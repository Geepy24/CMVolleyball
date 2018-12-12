package management.web.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.cm.domain.Article;
import com.cm.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 文章的动作类
 * @author Huangjiping
 *
 */
@ParentPackage("json-default")
@Namespace("/Article")
public class articleAction extends ActionSupport implements ModelDriven<Article> {
	//模型驱动
	private Article article = new Article() ;

	//其他
	private User user ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;

	@Override
	public Article getModel() {

		return article;
	}







//-------------------动作方法-------------------------------



	

	@Action(value="articleHndler",results= {@Result(name="handle",type="json")},
				params= {"root","returndata"})
	public String articleHandler() {
		//该动作类处理管理员的文章提交
		//当前用户，即管理员
		user = (User) session.getAttribute("loginInfo") ;
		System.out.println("当前页面用户"+user);
		System.out.println("模型驱动进来："+article);
		
		article.setAdsName(user.getUserName());
		article.setUserId(user.getUserId());
		article.setAdsName(user.getUserName());
		
		
		

		
	
		
		
		return "handle" ;
	}

	

}
