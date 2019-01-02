package com.cm.domain;

import java.io.Serializable;
/**
 * 	文章回收站的实体类
 * @author Huangjiping
 *
 */
public class Dustbin implements Serializable {

	private Integer dustId ;
	private Integer artId ;
//	private String authorName ;
	private String artTitle ;
	private String artContent ;
	private String delTime ;
	
	//一对多
	private User user ;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getDustId() {
		return dustId;
	}
	public void setDustId(Integer dustId) {
		this.dustId = dustId;
	}
	public Integer getArtId() {
		return artId;
	}
	public void setArtId(Integer artId) {
		this.artId = artId;
	}
//	public String getAuthorName() {
//		return authorName;
//	}
//	public void setAuthorName(String authorName) {
//		this.authorName = authorName;
//	}
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
	public String getDelTime() {
		return delTime;
	}
	public void setDelTime(String delTime) {
		this.delTime = delTime;
	}
	@Override
	public String toString() {
		return "Dustbin [dustId=" + dustId + ", artId=" +", artTitle="
				+ artTitle + ", artContent=" + artContent + ", delTime=" + delTime + "]";
	}
	
	
	
}
