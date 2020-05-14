package com.bald.bean;
/*
 * 管理员类
 */
public class Administrator {
	private int id;
	private String userName;
	private int pw;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}
	public Administrator(int id, String userName, int pw) {
		super();
		this.id = id;
		this.userName = userName;
		this.pw = pw;
	}
	
	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", userName=" + userName + ", pw=" + pw + "]";
	}
	
}
