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
 * 文章的动作类
 * @author Huangjiping
 *
 */
@ParentPackage("json-default")
@Namespace("/Article")
public class articleAction extends ActionSupport implements ModelDriven<Article> {
	//模型驱动
	private Article article = new Article() ;
	//属性驱动
	private String articleTitle;
	private String publishTime;
	private String articleMain ;
	private JSONObject returndata ;
	//其他
	private User user ;
	HttpSession session = ServletActionContext.getRequest().getSession() ;
	//文章模板html的路径
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

//-------------------动作方法-------------------------------



	

	@Action(value="articleHndler",results= {@Result(name="handle",type="json")},
				params= {"root","returndata"})
	public String articleHandler() {
		//该动作类处理管理员的文章提交
		//当前用户，即管理员
		user = (User) session.getAttribute("loginInfo") ;
		System.out.println("当前页面用户"+user);
		System.out.println("文章标题"+articleTitle);
		System.out.println("文章内容"+articleMain);
		System.out.println("提交时间"+publishTime);
		
		article.setArtTitle(articleTitle);
		article.setAdsName(user.getUserName());
		article.setPubTime(publishTime);
		article.setUserId(user.getUserId());
		article.setUserName(user.getUserName());
		//uri在后面设置
		
		
		
		//文章模板html的路径
		articleTemplatePath = WebUtils.getAppPath() +"articleTemplate.html" ;
		//System.out.println(articleTemplatePath);
		
		//读取模板，将模板中的相应内容替换成文章的相应内容，再将文件写出
		//使用bufferedReader
		String str = "" ;
		try {
			String tempStr = "" ;
			FileInputStream is = new FileInputStream(articleTemplatePath) ;
			BufferedReader br = new BufferedReader(new InputStreamReader(is)) ;
			while ((tempStr = br.readLine()) != null) {
				str = str + tempStr ;
			}
			System.out.println("读取的模板"+str);
			is.close(); 
			
		} catch (IOException e) {
			e.printStackTrace(); 
			
		}
		//生成的新的html的地址，名称为标题+三位随机数
		String newArticlePath = WebUtils.getAppPath() + "WEB-INF\\article\\" 
				+ articleTitle +"-"+WebUtils.random()+".html" ;
		System.out.println("新文章的uri地址："+newArticlePath);
		try {
			
			//用提交来的文章的内容替换模板中相应的内容
			//因为文章中可能会有特殊字符使得replaceAll出现错误，所以要先处理特殊字符
			articleTitle = java.util.regex.Matcher.quoteReplacement(articleTitle) ;
			String author = java.util.regex.Matcher.quoteReplacement(article.getUserName()) ;
			articleMain = java.util.regex.Matcher.quoteReplacement(articleMain) ;
			
			str = str.replaceAll("###title###", articleTitle) ;
			str = str.replaceAll("###author###",author) ;
			str = str.replaceAll("###content###", articleMain) ;
			
			System.out.println("替换后的内容"+str);
			//写出到新的html中
			
			File newArticle = new File(newArticlePath) ;
			//outputStreamWriter可以指定写出的编码
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
