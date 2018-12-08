package com.cm.domain;

import java.io.Serializable;

public class User implements Serializable{

	private int userId ;
	private String userName ;
	private String realName ;
	private String password ;
	private String gender ;
	private String tel ;
	
	
	
	
	public User() {}
	public User(String userName, String realName, String password,String gender,String tel) {
		super();
		this.userName = userName;
		this.realName = realName;
		this.password = password;
		this.gender = gender;
		this.tel = tel ;
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
