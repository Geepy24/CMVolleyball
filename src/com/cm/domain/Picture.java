package com.cm.domain;

import java.io.Serializable;

public class Picture implements Serializable {

	private Integer picId ;
	private String picUri ;
	private String picName ;
	
	
	
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
	
	
	@Override
	public String toString() {
		return "Picture [picId=" + picId + ", picUri=" + picUri + ", picName=" + picName + "]";
	}
	
	
	
	
	
}
