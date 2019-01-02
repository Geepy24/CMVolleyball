package com.cm.domain;

import java.io.Serializable;
/**
 * 文章的实体类
 * @author Huangjiping
 *
 */
public class Article implements Serializable {
	
	private int artId ;
	private String artTitle ;
	private String artContent ;
//	private int authorId ;
//	private String authorName ;
	private String adsName ;
	private String pubTime ;
	private String lastMod ;
	
	//多对一，artId作为外键
	private User user ;
	
	
	public int getArtId() {
		return artId;
	}
	public void setArtId(int artId) {
		this.artId = artId;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getArtContent() {
		return artContent;
	}
	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}
	
//	public int getAuthorId() {
//		return authorId;
//	}
//	public void setAuthorId(int authorId) {
//		this.authorId = authorId;
//	}
//	public String getAuthorName() {
//		return authorName;
//	}
//	public void setAuthorName(String authorName) {
//		this.authorName = authorName;
//	}
	public String getAdsName() {
		return adsName;
	}
	public void setAdsName(String adsName) {
		this.adsName = adsName;
	}
	public String getPubTime() {
		return pubTime;
	}
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	public String getLastMod() {
		return lastMod;
	}
	public void setLastMod(String lastMod) {
		this.lastMod = lastMod;
	}
	@Override
	public String toString() {
		return "Article [artId=" + artId + ", artTitle=" + artTitle + ", artContent=" + artContent + ", authorId="
				+ ", adsName=" + adsName + ", pubTime=" + pubTime
				+ ", lastMod=" + lastMod + "]";
	}


	
	
	
	
}
