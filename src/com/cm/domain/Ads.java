package com.cm.domain;

import java.io.Serializable;
/**
 * 	管理员表
 * @author Huangjiping
 *
 */
public class Ads implements Serializable {

	private int adsId ;
	private String adsName ;
	private int userId ;  //用于存放是管理员的用户的ID
	
	

	public int getAdsId() {
		return adsId;
	}
	public void setAdsId(int adsId) {
		this.adsId = adsId;
	}
	public String getAdsName() {
		return adsName;
	}
	public void setAdsName(String adsName) {
		this.adsName = adsName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Ads [adsId=" + adsId + ", adsName=" + adsName + ", userId=" + userId + "]";
	}

	
	
	
	
}
