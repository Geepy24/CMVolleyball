package com.cm.domain;

import java.io.Serializable;

public class PictureCheck implements Serializable {

	private Integer picId ;
	private String picUri ;
	private String picName ;
	private Integer checkTag ;
	private String checkCom ;
	private Integer userId ; 
	private String resCom ;
	
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public String getPicUri() {
		return picUri;
	}
	public void setPicUri(String picUri) {
		this.picUri = picUri;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public Integer getCheckTag() {
		return checkTag;
	}
	public void setCheckTag(Integer checkTag) {
		this.checkTag = checkTag;
	}
	public String getCheckCom() {
		return checkCom;
	}
	public void setCheckCom(String checkCom) {
		this.checkCom = checkCom;
	}
	
	
	public String getResCom() {
		return resCom;
	}
	public void setResCom(String resCom) {
		this.resCom = resCom;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "PictureCheck [picId=" + picId + ", picUri=" + picUri + ", picName=" + picName + ", checkTag=" + checkTag
				+ ", checkCom=" + checkCom +",resCom"+resCom+ ", userId=" + userId + "]";
	}
	
	
	
	
	
	
	
}
