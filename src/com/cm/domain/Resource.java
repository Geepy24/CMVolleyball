package com.cm.domain;

import java.io.Serializable;

public class Resource implements Serializable {
	
	private int resId ;
	private String resTag ;
	private String resCom ;
	private String resUri ;
	private int userId ;
	private String userName ;
	private String adsName ;
	private String pubTime ;
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public String getResTag() {
		return resTag;
	}
	public void setResTag(String resTag) {
		this.resTag = resTag;
	}
	public String getResCom() {
		return resCom;
	}
	public void setResCom(String resCom) {
		this.resCom = resCom;
	}
	public String getResUri() {
		return resUri;
	}
	public void setResUri(String resUri) {
		this.resUri = resUri;
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
	@Override
	public String toString() {
		return "Resource [resId=" + resId + ", resTag=" + resTag + ", resCom=" + resCom + ", resUri=" + resUri
				+ ", userId=" + userId + ", userName=" + userName + ", adsName=" + adsName + ", pubTime=" + pubTime
				+ "]";
	}
	
	
	
}
