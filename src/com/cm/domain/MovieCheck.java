package com.cm.domain;

import java.io.Serializable;

public class MovieCheck implements Serializable {

	private Integer movId ;
	private String movUri ;
	private String movName ;
	private Integer checkTag ;
	private String checkCom ;
	private Integer userId ; 
	private String resCom ;
	
	
	private MediaPreview mediaPreview ;
	public Integer getMovId() {
		return movId;
	}

	public void setMovId(Integer movId) {
		this.movId = movId;
	}

	public String getMovUri() {
		return movUri;
	}

	public void setMovUri(String movUri) {
		this.movUri = movUri;
	}

	public String getMovName() {
		return movName;
	}

	public void setMovName(String movName) {
		this.movName = movName;
	}

	public MediaPreview getMediaPreview() {
		return mediaPreview;
	}

	public void setMediaPreview(MediaPreview mediaPreview) {
		this.mediaPreview = mediaPreview;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getResCom() {
		return resCom;
	}

	public void setResCom(String resCom) {
		this.resCom = resCom;
	}

	@Override
	public String toString() {
		return "MovieCheck [movId=" + movId + ", movUri=" + movUri + ", movName=" + movName + ", checkTag=" + checkTag
				+ ", checkCom=" + checkCom + "resTag="+resCom +", userId=" + userId + "]";
	}

	
	
}
