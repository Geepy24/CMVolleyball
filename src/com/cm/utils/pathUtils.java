package com.cm.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 路径的工具类
 * @author mac
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:WebContent/WEB-INF/applicationContext.xml") 
public class pathUtils {
	
	/**
	 * 当前项目的绝对路径
	 */
	
	public static String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	
	
	/**
	 * 得到视频文件存储目录的绝对路径
	 */
	
	public static String moviePath() {
		HttpServletRequest request = ServletActionContext.getRequest() ;
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		String filePath = temp[0]+"mov" ;
		return filePath ;
	}
	/**
	 * 视频缩略图的文件夹位置
	 * @return
	 */
	public static String mpPath() {
		HttpServletRequest request = ServletActionContext.getRequest() ;
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		String filePath = temp[0]+"media_preview" ;
		return filePath ;
	}
	/**
	 * 得到图片文件夹的路径
	 * @return
	 */
	public static String picturePath() {
		HttpServletRequest request = ServletActionContext.getRequest() ;
		String realpath = request.getRealPath("/") ;
		String root = request.getContextPath() ;
		String [] temp = root.split("/") ;
		temp = realpath.split(temp[1]) ;
		String filePath = temp[0]+"pic" ;
		return filePath ;
	}
	
	
	
	
}
