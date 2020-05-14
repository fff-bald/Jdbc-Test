package com.bald.service;

import com.bald.dao.AdminDao;

public class AdminService {
	AdminDao dao = new AdminDao();
	public boolean login(String userName, String password) {
		
		Object count = dao.scalar("select * from administrator where userName=? and pw=?", userName,password);
		return Integer.parseInt(count+"")>0;
	}
}
