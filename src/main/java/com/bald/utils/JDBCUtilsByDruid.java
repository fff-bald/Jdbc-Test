package com.bald.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/*
 * 通过druid数据库连接池获得connection
 */
public class JDBCUtilsByDruid {
	static DataSource ds;
	static {
		try {
			Properties pro = new Properties();
			pro.load(new FileInputStream("src\\main\\resources\\jdbc_druid.properties"));
			//创建一个指定了参数的数据库连接池
			ds = DruidDataSourceFactory.createDataSource(pro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		
		//从连接池中获得可用的连接对象
		return ds.getConnection();
	}
	
	/*
	 * 释放资源
	 */
	public static void close(ResultSet set, Statement statement, Connection connection) {
		try {
			if(set!=null) {
				set.close();
			}
			if(statement!=null) {
				statement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
