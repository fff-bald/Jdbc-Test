package com.bald.service;

import java.sql.SQLException;

import com.bald.bean.Student;
import com.bald.dao.GradeDao;

public class GradeService extends GradeDao {
	 GradeDao dao = new GradeDao();
	/*
	 * 事务处理，修改student表中学生相对应的年级，grade表相对应的年级分别加一减一
	 */
	public void higher(int num) {
		StuService stuService = new StuService();
		Student stu = stuService.queryIdStu(num);
		String sql1="update grade set sum=sum-1 where grade=?";
		String sql2="update grade set sum=sum+1 where grade=?";
		dao.update("update student set grade=? where num=?",stu.getGrade()+1,num);
		if(stu.getGrade()==4) {
			System.out.println("学生"+stu.getStuName()+"毕业了");
			return;
		}
		Object params1[] = new Object[1];
		Object params2[] = new Object[1];
		params2[0]=stu.getGrade();
		params2[0]=stu.getGrade()+1;
		try {
			dao.tP(sql1, params1, sql2, params2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("升学成功！");
		return;
	}
	/*
	 * 更新年级学生人数
	 */
	public void stuUpdate() {
		Object count=null;
		Object[][] params= new Object[4][];
		for(int i=0;i<4;i++) {
			params[i]=new Object[2];
			params[i][0]=dao.scalar("select count(*) from student where grade=?", i);
			params[i][1]=i;
		}
		dao.batchUpdate("update grade set sum=? where grade=?",params);//更新年级学生人数
		dao.update("delete from student where grade>?", 4);//四年级后毕业
		return;
	}
	
}
