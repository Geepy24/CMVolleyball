package management.web.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.cm.domain.Article;
import com.cm.domain.User;
import com.cm.utils.WebUtils;
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
	//ģ������
	private Article article = new Article() ;
	//��������
	private String articleTitle;
	private String publishTime;
	private String articleMain ;
	private JSONObject returndata ;
	//����
	private User user ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	//����ģ��html��·��
	private String articleTemplatePath ;
	@Override
	public Article getModel() {

		return article;
	}


	public String getArticleTitle() {
		return articleTitle;
	}


	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}


	public String getPublishTime() {
		return publishTime;
	}


	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}


	public String getArticleMain() {
		return articleMain;
	}


	public void setArticleMain(String articleMain) {
		this.articleMain = articleMain;
	}
	
	public JSONObject getReturndata() {
		return returndata;
	}


	public void setReturndata(JSONObject returndata) {
		this.returndata = returndata;
	}

//-------------------��������-------------------------------



	

	@Action(value="articleHndler",results= {@Result(name="handle",type="json")},
				params= {"root","returndata"})
	public String articleHandler() {
		//�ö����ദ�����Ա�������ύ
		//��ǰ�û���������Ա
		user = (User) session.getAttribute("loginInfo") ;
		System.out.println("��ǰҳ���û�"+user);
		System.out.println("���±���"+articleTitle);
		System.out.println("��������"+articleMain);
		System.out.println("�ύʱ��"+publishTime);
		
		article.setArtTitle(articleTitle);
		article.setAdsName(user.getUserName());
		article.setPubTime(publishTime);
		article.setUserId(user.getUserId());
		article.setUserName(user.getUserName());
		//uri�ں�������
		
		
		
		//����ģ��html��·��
		articleTemplatePath = WebUtils.getAppPath() +"articleTemplate.html" ;
		//System.out.println(articleTemplatePath);
		
		//��ȡģ�壬��ģ���е���Ӧ�����滻�����µ���Ӧ���ݣ��ٽ��ļ�д��
		//ʹ��bufferedReader
		String str = "" ;
		try {
			String tempStr = "" ;
			FileInputStream is = new FileInputStream(articleTemplatePath) ;
			BufferedReader br = new BufferedReader(new InputStreamReader(is)) ;
			while ((tempStr = br.readLine()) != null) {
				str = str + tempStr ;
			}
			System.out.println("��ȡ��ģ��"+str);
			is.close(); 
			
		} catch (IOException e) {
			e.printStackTrace(); 
			
		}
		//���ɵ��µ�html�ĵ�ַ������Ϊ����+��λ�����
		String newArticlePath = WebUtils.getAppPath() + "WEB-INF\\article\\" 
				+ articleTitle +"-"+WebUtils.random()+".html" ;
		System.out.println("�����µ�uri��ַ��"+newArticlePath);
		try {
			
			//���ύ�������µ������滻ģ������Ӧ������
			//��Ϊ�����п��ܻ��������ַ�ʹ��replaceAll���ִ�������Ҫ�ȴ��������ַ�
			articleTitle = java.util.regex.Matcher.quoteReplacement(articleTitle) ;
			String author = java.util.regex.Matcher.quoteReplacement(article.getUserName()) ;
			articleMain = java.util.regex.Matcher.quoteReplacement(articleMain) ;
			
			str = str.replaceAll("###title###", articleTitle) ;
			str = str.replaceAll("###author###",author) ;
			str = str.replaceAll("###content###", articleMain) ;
			
			System.out.println("�滻�������"+str);
			//д�����µ�html��
			
			File newArticle = new File(newArticlePath) ;
			//outputStreamWriter����ָ��д���ı���
			OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(newArticle),"UTF-8") ;
			
			BufferedWriter bw = new BufferedWriter(ow) ;
			bw.write(str);
			bw.close();
			str = "" ;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		article.setArtUri(newArticlePath);
		
		return "handle" ;
	}

	

}
