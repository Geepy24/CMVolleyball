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
 * ���µĶ�����
 * @author Huangjiping
 *
 */
@ParentPackage("json-default")
@Namespace("/Article")
public class articleAction extends ActionSupport implements ModelDriven<Article> {
	
	@Autowired
	IArticleService articleService ;
	
	//ģ������
	private Article article = new Article() ;
	
	

	//��������
	private List<Article> articles ;
	private int currentPage ;
	private int toPage ;
	private String returndata ;
	private JSONObject json ;
	//private int article_Id ;
	//����
	private User user ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	//��ҳ����Ŀ��
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

//-------------------��������-------------------------------





	//�ö����ദ�����Ա�������ύ
	@Action(value="articleHndler",results= {@Result(name="handle",type="json")},
				params= {"root","article"})
	public String articleHandler() {
		//��ǰ�û���������Ա
		user = (User) session.getAttribute("loginInfo") ;
		System.out.println("��ǰҳ���û�"+user);
		System.out.println("ģ������������"+article);
		
		article.setAuthorName(user.getUserName());
		article.setAuthorId(user.getUserId());
		article.setAdsName(user.getUserName());
		article.setLastMod(article.getPubTime());
		
		articleService.saveArticle(article);
		
		System.out.println(article);
		
		return "handle" ;
	}
	
	
	//������չʾģ������ʾ���£���Ҫ�Ĳ���Ϊ����ID
	@Action(value="showArticle",results={@Result(name="success",location="/WEB-INF/jsp/management/article/show.jsp")})
	public String showPage() {
		
		//ģ������������ֻ��artId������id����article
		//System.out.println(article);
		 
		article = articleService.findById(article.getArtId()) ;
		System.out.println("��ѯ�����"+article);
		//��ѯ�����ֵ������ֵջ�е�article������ûֵ
		
		return SUCCESS ;
	}

	//�����б���ҳ���Һ���ʾ����ʾ������ҳ
	@Action(value="articleList",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String showArticleList() {
		//��һҳ��ÿҳʮ��
		 
		 currentPage = 1 ;
		 articles = articleService.findAllArticle(currentPage,MAXRESULTS);
		 
		return SUCCESS;
		
	}
	//�����б��ҳ��ѡ��ǰ�˲�ͬҳ��ʾ���صĲ�ͬҳ������
	@Action(value="selectPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String selectPage() {
		
		System.out.println(toPage);
		currentPage = toPage ;
		articles = articleService.findAllArticle(toPage, MAXRESULTS) ;
		
		
		return SUCCESS ;
	}
	//��һҳ�����б�
	@Action(value="nextPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String nPage() {
		
		//���ܸı�currentPage�ĵ�ַ����Ȼ�����������Ž�ֵջ����Զ���ʼ���Ǹ���ַ
		int temp = currentPage ;
		temp = temp + 1 ;
		currentPage = temp ;
		
		articles = articleService.findAllArticle(currentPage,MAXRESULTS);
		
		return SUCCESS ;
	}
	//��һҳ�����б�
	@Action(value="prePage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/list.jsp")})
	public String pPage() {
		
		int temp = currentPage ;
		temp = temp - 1 ;
		currentPage = temp ;
		
		
		articles = articleService.findAllArticle(currentPage,MAXRESULTS);
		
		return SUCCESS ;
	}

	/**
	 * 	ҳ�����ʱ�����š���Դ��Ŀ���������
	 */
	@Action(value="indexArticle",results= {@Result(name="success",type="json",
					params= {"root","returndata"})})
	public String index() {
		System.out.println("��ҳ����");
		//���Ժ�����query.list()��ס��û�з�Ӧ
		//��������⣬û����ûֵ��������spring���񣬳������Զ��ع�
		//Ӧ�ò���������£����ܸյ���ҳ��û��hibernateû��session
		//������session������
		articles = articleService.findAllArticle(1, MAXRESULTS) ;
		Map<String , String> map = new LinkedHashMap<>() ;
		for(int i = 0 ; i<articles.size() ; i++) {
			map.put(articles.get(i).getArtId()+"", articles.get(i).getArtTitle()+"."+articles.get(i).getPubTime()) ;  
		}
		//�ټ�����������
		JSONObject json = JSONObject.fromObject(map) ;
		System.out.println(map);
		System.out.println(json);
		returndata = json.toString() ;
		System.out.println(returndata);
		
		return SUCCESS ;
	}
	
	
	
	
}	
