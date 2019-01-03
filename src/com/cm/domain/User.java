package com.cm.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable{

	private int userId ;
	private String userName ;
	private String realName ;
	private String password ;
	private String gender ;
	private String tel ;
	
	
	//一对多关系映射
	//文章，图片，视频
	//使用set集合方便，参数0表示只分配内存地址，不开辟内存
	private Set<Resource> resources = new LinkedHashSet<>(0) ;
	private Set<Article>articles =new LinkedHashSet<>(0) ;
	private Set<Dustbin> dustbins = new LinkedHashSet<>(0) ;
	private Set<Draft> drafts =new LinkedHashSet<>(0) ;
	
	public User() {}
	public User(String userName, String realName, String password,String gender,String tel) {
		super();
		this.userName = userName;
		this.realName = realName;
		this.password = password;
		this.gender = gender;
		this.tel = tel ;
	}
	
	
	public Set<Dustbin> getDustbins() {
		return dustbins;
	}
	public void setDustbins(Set<Dustbin> dustbins) {
		this.dustbins = dustbins;
	}
	public Set<Draft> getDrafts() {
		return drafts;
	}
	public void setDrafts(Set<Draft> drafts) {
		this.drafts = drafts;
	}
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public User(String userName, String realName, String password, String gender) {
		super();
		this.userName = userName;
		this.realName = realName;
		this.password = password;
		this.gender = gender;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	

	
	
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", realName=" + realName + ", password="
				+ password + ", gender=" + gender + ", tel=" + tel + "]";
	}
	
	
	
}
