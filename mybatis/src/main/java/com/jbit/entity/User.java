package com.jbit.entity;

public class User {
	int userId;
	String userName;
	String passWord;
	public User() {
		super();
	}
	
	public User(int userId, String userName, String passWord) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
	}

	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
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
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + "]";
	}
	
	
}
