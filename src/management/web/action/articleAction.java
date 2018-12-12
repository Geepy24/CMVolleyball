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
 * ���µĶ�����
 * @author Huangjiping
 *
 */
@ParentPackage("json-default")
@Namespace("/Article")
public class articleAction extends ActionSupport implements ModelDriven<Article> {
	//ģ������
	private Article article = new Article() ;

	//����
	private User user ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;

	@Override
	public Article getModel() {

		return article;
	}







//-------------------��������-------------------------------



	

	@Action(value="articleHndler",results= {@Result(name="handle",type="json")},
				params= {"root","returndata"})
	public String articleHandler() {
		//�ö����ദ�����Ա�������ύ
		//��ǰ�û���������Ա
		user = (User) session.getAttribute("loginInfo") ;
		System.out.println("��ǰҳ���û�"+user);
		System.out.println("ģ������������"+article);
		
		article.setAdsName(user.getUserName());
		article.setUserId(user.getUserId());
		article.setAdsName(user.getUserName());
		
		
		

		
	
		
		
		return "handle" ;
	}

	

}
