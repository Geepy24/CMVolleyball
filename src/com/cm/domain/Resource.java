package com.cm.domain;

import java.io.Serializable;

public class Resource implements Serializable {
	
	private Integer resId ;
	private String resTag ;
	private String resCom ;
	private String pubTime ;
	private Integer adsId ;
//	private Integer userId ;
	//外键字段
	private Picture picture ;
	private Movie movie ;
	//多对一，resId作为外键
	private User user ;
	
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
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
	public String getPubTime() {
		return pubTime;
	}
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	
	
//	public Integer getUserId() {
//		return userId;
//	}
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
	public Picture getPicture() {
		return picture;
	}
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getAdsId() {
		return adsId;
	}
	public void setAdsId(Integer adsId) {
		this.adsId = adsId;
	}
	@Override
	public String toString() {
		return "Resource [resId=" + resId + ", resTag=" + resTag + ", resCom=" + resCom + ", pubTime=" + pubTime
				+  ", adsId=" + adsId + ",  picture=" + picture +
				", movie=" + movie + "]";
	}
	

	
	
	
}
