package com.cm.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;

public class WebUtils {
	 
	
	/**
	 * 	得到web应用的根目录
	 * @return
	 */
	public static String getAppPath() {
		ActionContext actionContext = ActionContext.getContext();
		 ServletContext servletContext = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
		return servletContext.getRealPath("/");
	}

	/**
	 * 	根据时间生成随机数
	 */
	
	public static String random() {
		String f ;
		long l =System.currentTimeMillis()%1000 ;
		f = String.valueOf(l) ;
		return f ;
	}
	@Test
	public void test() {
		System.out.println(random());
	}

	/**
	 * 	该动作方法用于前端文本编辑器传入的文本处理成新的html
	 * 	包括读取模板html，替换模板html中相应内容，生成新的html文件存入磁盘
	 * 	返回值：新生成的html的uri地址
	 * 	参数：文章标题，作者，内容，发布时间
	 */
	public static String createArticle(String title,String author,String content,String pubTime) {
		//文章模板html的路径
				String articleTemplatePath = WebUtils.getAppPath() +"articleTemplate.html" ;
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
				//存放的uri是在与该web项目同级的CMVolleyball-article文件夹中
				//如果存放在项目内，重新部署服务器会清空
			//	pathwebapp = pathwebapp.substring(0,pathwebapp.lastIndexOf("\\"));
				String webPath = WebUtils.getAppPath() ;
				
				String newArticlePath =  webPath.substring(0,webPath.lastIndexOf("\\"))
						+ "-article\\" 
						+ title +"-"+WebUtils.random()+".html" ;
				System.out.println("新文章的uri地址："+newArticlePath);
				try {
					
					//用提交来的文章的内容替换模板中相应的内容
					//因为文章中可能会有特殊字符使得replaceAll出现错误，所以要先处理特殊字符
					title = java.util.regex.Matcher.quoteReplacement(title) ;
					author = java.util.regex.Matcher.quoteReplacement(author) ;
					content = java.util.regex.Matcher.quoteReplacement(content) ;
					
					str = str.replaceAll("###title###", title) ;
					str = str.replaceAll("###author###",author) ;
					str = str.replaceAll("###content###", content) ;
					
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
		
			return newArticlePath ;
				
				
				
				
				
				
	}
	/**
	 * 得到 yyyy-mm-dd的当前时间
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(currentTime); 
	    return dateString ;
	}
	
	
	
}
