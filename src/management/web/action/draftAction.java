package management.web.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.Draft;
import com.cm.domain.User;
import com.cm.service.IArticleService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
@Namespace("/Article")
public class draftAction extends ActionSupport implements ModelDriven<Draft> {
	
	@Autowired
	private IArticleService articleService ;
	private Draft draft = new Draft() ;
	private String returndata ;
	private User user;
	private List<Draft> drafts ;
	private static int MAXRESULTS = 10 ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	@Override
	public Draft getModel() {
		return draft;
	}


	//-----------getter/setter------------------
	

	public IArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}	
	
	
	
	
	
	
	public String getReturndata() {
		return returndata;
	}


	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}


	//-----------action--------------------
	/**
	 * 	保存草稿跳到草稿箱的action
	 * 	将草稿的id返回
	 */
	@Action(value="saveTemp",results= {@Result(name="success",type="json",params= {"root","returndata"})})
	//location="/WEB-INF/jsp/management/article/tempList.jsp"
	public String tempArticle() {
		user = (User) session.getAttribute("loginInfo") ;
		System.out.println("草稿箱");
		System.out.println(draft);
		System.out.println(user);
		
		draft.setAuthorId(user.getUserId());
		
		
		articleService.saveDraft(draft);
		
		System.out.println(draft.getDraId());
		returndata = "{\"draId\" :  \""+draft.getDraId()+"\",\"authorId\" : \""+draft.getAuthorId()+"\"}" ;
		
		
		
		
		return SUCCESS ;
	}

/**
 * 	草稿箱列表，可以从菜单进来（带入数据为authorId）
 * 			也可以从保存文章进来（带入数据为authorID和draId）
 * 	需要改进dao层的分页方法
 * @return
 */
	@Action(value="toDraftList",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/tempList.jsp")})
	public String toList() {
		
		int currentPage = 1 ;
		//Draft draftTemp = null ;
		System.out.println(draft.getDraId());
		//用这种办法让值栈顶端的对象有值，不改变实体类的地址
		//draftTemp = articleService.findDraftById(draft.getDraId());
		System.out.println(draft.getAuthorId());
		//draft.setArtTitle(draftTemp.getArtTitle());
		//draft.setArtContent(draftTemp.getArtContent());
		//draft.setLastMod(draftTemp.getLastMod());
		//dao层有错，要用query
		//drafts = articleService.findAllDraft(draft.getAuthorId(), currentPage, MAXRESULTS) ;
		
		System.out.println(drafts);
		return SUCCESS;
	}
	
}
