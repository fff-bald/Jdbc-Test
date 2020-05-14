package com.bald.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bald.utils.JDBCUtilsByDruid;
/*
 * ��װDBUtils����
 */
public class BasicDao<T> {
	
	/*
	 *  ͨ�õ���ɾ�ķ�����������κα�
	 */
	public int update(String sql,Object...param) {
		Connection connection = null;
		try {
		connection = JDBCUtilsByDruid.getConnection();
		
		QueryRunner qr = new QueryRunner();
		return qr.update(connection, sql, param);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			 JDBCUtilsByDruid.close(null,null,connection);
		}
	}
	/*
	 * ���ص�����������κα�
	 */
	public  T querySingle(String sql,Class<T> clazz,Object...params) {
		Connection connection = null;
		try {
		connection = JDBCUtilsByDruid.getConnection();
		
		QueryRunner qr = new QueryRunner();
		return qr.query(connection, sql,new BeanHandler<T>(clazz), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtilsByDruid.close(null,null,connection);
		}
	}
	/*
	 * ���ض����������κα�
	 */
	public List<T> queryNulti(String sql,Class<T> clazz,Object...params) {
		Connection connection = null;
		try {
		connection = JDBCUtilsByDruid.getConnection();
		
		QueryRunner qr = new QueryRunner();
		return qr.query(connection, sql,new BeanListHandler<T>(clazz), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtilsByDruid.close(null,null,connection);
		}
	}
	/*
	 * ���ص���ֵ
	 */
	public Object scalar(String sql,Object...params) {
		Connection connection = null;
		try {
		connection = JDBCUtilsByDruid.getConnection();
		
		QueryRunner qr = new QueryRunner();
		return qr.query(connection, sql,new ScalarHandler(), params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JDBCUtilsByDruid.close(null,null,connection);
		}
	}
	/*
	 * ������
	 */
	public void batchUpdate(String sql, Object[][] params) {
        Connection conn = null;

        try {
            conn = JDBCUtilsByDruid.getConnection();
            QueryRunner qr = new QueryRunner();
            qr.batch(conn,sql, params);
        } catch (Exception e) {
        	throw new RuntimeException(e);
        } finally {
        	JDBCUtilsByDruid.close(null,null,conn);
        }
        return;
    }
	public void tP(String sql1, Object[]params1,String sql2, Object[]params2) throws SQLException {
		 Connection conn = null;
		 try{
			 conn = JDBCUtilsByDruid.getConnection();
			 //��������
			 conn.setAutoCommit(false);
			 /**
			  * �ڴ���QueryRunner����ʱ������������Դ��������Ϊ�˱�֤������SQL��ͬһ�������н��У�
			  * �����ֶ���ȡ���ݿ����ӣ�Ȼ����������SQLʹ��ͬһ�����ݿ�����ִ��
			  */
			 QueryRunner runner = new QueryRunner();
			 runner.update(conn,sql1,params1);
			 //ģ���������쳣������ع�
			 runner.update(conn,sql2,params2);
			 //sql����ִ��֮����ύ����
			 conn.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
			//�����쳣֮��ͻع�����
			 if(conn!=null) {
				 conn.rollback();
				 System.out.println("���ݲ���ʧ�ܣ��ع��ɹ���");
			 }
			
		 } finally {
			 //�ر����ݿ�����
			 JDBCUtilsByDruid.close(null,null,conn);
		 }
		 return;
	}
	
}
