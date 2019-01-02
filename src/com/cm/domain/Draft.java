package com.cm.domain;

import java.io.Serializable;

public class Draft implements Serializable {
	
	private int draId ;
	private String artTitle ;
	private String artContent ;
//	private int authorId ;
	private String lastMod ;
	
	//Ò»¶Ô¶à
	private User user ;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getDraId() {
		return draId;
	}
	public void setDraId(int draId) {
		this.draId = draId;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtContent() {
		return artContent;
	}
	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}
	
	public String getLastMod() {
		return lastMod;
	}
	public void setLastMod(String lastMod) {
		this.lastMod = lastMod;
	}
//	public int getAuthorId() {
//		return authorId;
//	}
//	public void setAuthorId(int authorId) {
//		this.authorId = authorId;
//	}
	@Override
	public String toString() {
		return "Draft [draId=" + draId + ", artTitle=" + artTitle + ", artContent=" + artContent + ", authorId="
				 + ", lastMod=" + lastMod + "]";
	}
	

	
	
	
	
}
