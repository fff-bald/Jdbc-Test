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
 * ͨ��druid���ݿ����ӳػ��connection
 */
public class JDBCUtilsByDruid {
	static DataSource ds;
	static {
		try {
			Properties pro = new Properties();
			pro.load(new FileInputStream("src\\main\\resources\\jdbc_druid.properties"));
			//����һ��ָ���˲��������ݿ����ӳ�
			ds = DruidDataSourceFactory.createDataSource(pro);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		
		//�����ӳ��л�ÿ��õ����Ӷ���
		return ds.getConnection();
	}
	
	/*
	 * �ͷ���Դ
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
