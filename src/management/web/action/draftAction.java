package management.web.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Session;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;

import com.cm.domain.Draft;
import com.cm.domain.User;
import com.cm.service.IArticleService;
import com.mysql.jdbc.log.Log;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
@Namespace("/Article")
public class draftAction extends ActionSupport implements ModelDriven<Draft> {
	
	@Autowired
	private IArticleService articleService ;
	private Draft draft = new Draft() ;
	private String returndata ;
	
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	//��ǰ�û�
	private User user =(User) session.getAttribute("loginInfo")  ;
	private List<Draft> drafts ;
	//��ҳ�����Ŀ��
	private static int MAXRESULTS = 10 ;
	//��ǰҳ��
	private int currentPage ;
	
	
	//�ݸ���ҳ����ת
	private int toPage ;
	
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
	
	



	public int getToPage() {
		return toPage;
	}


	public void setToPage(int toPage) {
		this.toPage = toPage;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}





	public List<Draft> getDrafts() {
		return drafts;
	}


	public void setDrafts(List<Draft> drafts) {
		this.drafts = drafts;
	}


	public String getReturndata() {
		return returndata;
	}


	public void setReturndata(String returndata) {
		this.returndata = returndata;
	}


	//-----------action--------------------
	/**
	 * 	����ݸ������ݸ����action
	 * 	���ݸ��id����
	 */
	@Action(value="saveTemp",results= {@Result(name="success",type="json",params= {"root","returndata"})})
	//location="/WEB-INF/jsp/management/article/tempList.jsp"
	public String tempArticle() {
		System.out.println("�ݸ���");
		System.out.println(draft);
		System.out.println(user);
		
		draft.setAuthorId(user.getUserId());
		
		
		articleService.saveDraft(draft);
		
		System.out.println(draft.getDraId());
		returndata = "{\"draId\" :  \""+draft.getDraId()+"\",\"authorId\" : \""+draft.getAuthorId()+"\"}" ;
		
		
		
		
		return SUCCESS ;
	}

/**
 * 	�ݸ����б����ԴӲ˵��������������ݣ�
 * 			Ҳ���Դӱ������½�������������ΪauthorID��draId��
 * 	��Ҫ�Ľ�dao��ķ�ҳ����
 * @return
 */
	@Action(value="toDraftList",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/tempList.jsp")})
	public String toList() {
		
		currentPage = 1 ;
		
 		//Draft draftTemp = null ;
		draft.setAuthorId(user.getUserId());
		System.out.println(draft.getDraId());
		//�����ְ취��ֵջ���˵Ķ�����ֵ�����ı�ʵ����ĵ�ַ
		//draftTemp = articleService.findDraftById(draft.getDraId());
		System.out.println(draft.getAuthorId());
		//draft.setArtTitle(draftTemp.getArtTitle());
		//draft.setArtContent(draftTemp.getArtContent());
		//draft.setLastMod(draftTemp.getLastMod());
		//ʹ��hibernateTemplate��findByExample����
		drafts = articleService.findAllDraft(draft.getAuthorId(), currentPage, MAXRESULTS) ;
		
		//���вݸ�
		Long totalItems = articleService.AllDraftNumber(draft.getAuthorId()) ;
		Long totalPages ;
		//��ҳ��
		if(0 == totalItems%10) {
			totalPages = totalItems/10 ;
		}else {
			totalPages = (totalItems/10) + 1  ;
		}
		//�Ž�session
		session.setAttribute("totalPages", totalPages);
		return SUCCESS;
	}
	
	//��һҳ�����б�
		@Action(value="nextDraft",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/tempList.jsp")})
		public String nPage() {
			
			//���ܸı�currentPage�ĵ�ַ����Ȼ�����������Ž�ֵջ����Զ���ʼ���Ǹ���ַ
			int temp = currentPage ;
			temp = temp + 1 ;
			currentPage = temp ;
			
			drafts = articleService.findAllDraft(user.getUserId(), currentPage, MAXRESULTS) ;
			
			
			
			return SUCCESS ;
		}
		//��һҳ�����б�
		@Action(value="preDraft",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/tempList.jsp")})
		public String pPage() {
			
			int temp = currentPage ;
			temp = temp - 1 ;
			currentPage = temp ;
			
			
			drafts = articleService.findAllDraft(user.getUserId(), currentPage, MAXRESULTS) ;
			
		
			
			return SUCCESS ;
		}
		//�����б��ҳ��ѡ��ǰ�˲�ͬҳ��ʾ���صĲ�ͬҳ������
		@Action(value="selectPage",results= {@Result(name="success",location="/WEB-INF/jsp/management/article/draftList.jsp")})
		public String selectPage() {
			
			System.out.println(toPage);
			currentPage = toPage ;
			drafts = articleService.findAllDraft(user.getUserId(),toPage, MAXRESULTS) ;
			
		
			
			return SUCCESS ;
		}
	
}
