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
	private String artUri ;
	private int userId ;
	private String userName ;
	private String adsName ;
	private String pubTime ;
	private String lastMod ;
	
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
	public String getArtUri() {
		return artUri;
	}
	public void setArtUri(String artUri) {
		this.artUri = artUri;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
		return "Article [artId=" + artId + ", artTitle=" + artTitle + ", artUri=" + artUri + ", userId=" + userId
				+ ", userName=" + userName + ", adsName=" + adsName + ", pubTime=" + pubTime + ", lastMod=" + lastMod
				+ "]";
	}
	
	
	
	
}
