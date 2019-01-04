package com.cm.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;

import net.sf.json.JSONObject;

/**
 * 处理JSON数据
 * @author mac
 *
 */
public class jsonUtils{
	/**
	 * 将articles变为JSON字符串返回
	 * @param list
	 * @return
	 */
	public static String artListToJsonString(List<Article> list) {
		
		Iterator<Article> iterator = list.iterator() ;
		Map<String ,String> map = new HashMap<String, String>() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Article article = iterator.next() ;
			String artTitle = article.getArtTitle() ;
			String pubTime = article.getPubTime() ;
			String lastMod = article.getLastMod() ;
			String artId = String.valueOf(article.getArtId()) ;
		//	System.out.println(i+"-"+artTitle);
			//合并在一个String中，通过###分割
			String content = artTitle+"###"+pubTime+"###"+lastMod+"###"+artId ;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map) ;
		return jsonObject.toString() ;
	}
	
	public static String draListToJsonString(List<Draft> list) {
			
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<Draft> iterator = list.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Draft draft = iterator.next() ;
			String artTitle = draft.getArtTitle() ;
			String lastMod = draft.getLastMod() ;
			String draId = String.valueOf(draft.getDraId()) ;
			System.out.println(i+"-"+artTitle);
			//合并在一个String中，通过###分割
			String content = artTitle+"###"+lastMod+"###"+ draId;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
			JSONObject jsonObject = JSONObject.fromObject(map) ;
			return jsonObject.toString() ;
		}
		
	public static String dsutListToJsonString(List<Dustbin> list) {
		
		Map<String ,String> map = new HashMap<String, String>() ;
		Iterator<Dustbin> iterator = list.iterator() ;
		int i = 1 ;
		while (iterator.hasNext()) {
			Dustbin dustbin = iterator.next() ;
			String artTitle = dustbin.getArtTitle() ;
			String delTime = dustbin.getDelTime() ;
			String dustId = String.valueOf(dustbin.getDustId()) ;
			System.out.println(i+"-"+artTitle);
			//合并在一个String中，通过###分割
			String content = artTitle+"###"+delTime+"###"+ dustId;
			map.put(String.valueOf(i), content) ;
			
			i++ ;
			
		}
			JSONObject jsonObject = JSONObject.fromObject(map) ;
			return jsonObject.toString() ;
		}
		
	
	
	
	
}
