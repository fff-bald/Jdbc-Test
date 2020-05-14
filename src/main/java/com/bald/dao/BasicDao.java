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
 * 封装DBUtils方法
 */
public class BasicDao<T> {
	
	/*
	 *  通用的增删改方法，针对于任何表
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
	 * 返回单个对象，针对任何表
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
	 * 返回多个对象，针对任何表
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
	 * 返回单个值
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
	 * 批处理
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
			 //开启事务
			 conn.setAutoCommit(false);
			 /**
			  * 在创建QueryRunner对象时，不传递数据源给它，是为了保证这两条SQL在同一个事务中进行，
			  * 我们手动获取数据库连接，然后让这两条SQL使用同一个数据库连接执行
			  */
			 QueryRunner runner = new QueryRunner();
			 runner.update(conn,sql1,params1);
			 //模拟程序出现异常让事务回滚
			 runner.update(conn,sql2,params2);
			 //sql正常执行之后就提交事务
			 conn.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
			//出现异常之后就回滚事务
			 if(conn!=null) {
				 conn.rollback();
				 System.out.println("数据操作失败，回滚成功。");
			 }
			
		 } finally {
			 //关闭数据库连接
			 JDBCUtilsByDruid.close(null,null,conn);
		 }
		 return;
	}
	
}
