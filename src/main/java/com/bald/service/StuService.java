package com.bald.service;

import java.util.List;
import java.util.Scanner;

import com.bald.bean.Student;
import com.bald.dao.StuDao;

public class StuService extends StuDao {
	StuDao dao = new StuDao();
	/*
	 * 返回学生人数
	 */
	public int stuNum(int grade) {
		Object count=null;
		if(grade==0){
			count = dao.scalar("select count(*) from student");
		}
		else count = dao.scalar("select sum from grade where grade=?", grade);
		return Integer.parseInt(count+"");
	}
	/*
	 * 返回学生信息
	 */
	public List<Student> stu(int grade){
		List<Student> list = dao.queryNulti("select * from student where grade=?", Student.class, grade);
		return list;
	}
	/*
	 * 通过学号查询学生
	 */
	public Student queryIdStu(int num) {
		Student stu = dao.querySingle("select * from student where num=?", Student.class, num);
		return stu;
	}
	/*
	 * 通过姓名查询学生
	 */
	public Student queryNameStu(String name) {
		Student stu = dao.querySingle("select * from student where stuName=?", Student.class, name);
		return stu;
	}
	/*
	 * 新增学生信息：学号、姓名、年级、出生日期
	 */
	public void insert() {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入新增学生的信息");
		System.out.print("学号=");
		int num = s.nextInt();
		System.out.print("姓名=");
		String name = s.next();
		System.out.print("年级（1-4）=");
		int g = s.nextInt();
		System.out.print("出生日期（%Y-%M-%D）=");
		String date = s.next();
		int p = dao.update("INSERT INTO student VALUES(?,?,?,?)",num,name,g,date );
		if(p==1)System.out.println("添加成功");
		else System.out.println("添加失败");
		return;
	}
	/*
	 * 删除指定学生
	 */
	public void delete() {
		Scanner s = new Scanner(System.in);
		System.out.print("请输入需要删除学生的学号:");
		int num = s.nextInt();
		int p = dao.update("delete from student where num=?",num );
		if(p==1)System.out.println("删除成功");
		else System.out.println("删除失败");
		return;
	}
	/*
	 * 一次性增加一万名学生进入一年级，进行批处理
	 */
	public void entrance() {
		Object count = dao.scalar("select max(num) from student");
		String sql="insert into student values(?,?,?,?)";
		Object[][] params= new Object[1000][];//1000条信息处理一次
		int n = Integer.parseInt(count+"");//找出当前最后的学号
		for(int i=n+1,j=0;i<1001+n;i++,j++) {
				params[j]= new Object[4];
				params[j][0] = i;
				params[j][1] = "stu"+i;
				params[j][2] = 1;
				params[j][3] = "2000-1-1";	
			}
			dao.batchUpdate(sql, params);
		System.out.println("新生录入成功");
		return;
	}
	/*
	 * 修改指定学号学生信息
	 */
	public void alter() {
		Scanner s = new Scanner(System.in);
		System.out.print("请输入需要修改信息学生的学号:");
		int num = s.nextInt();
		Student stu = dao.querySingle("select * from student where num=?", Student.class, num);
		if(stu==null) {
			System.out.println("查无此人！");
			return;
		}
		dao.update("delete from student where num=?",num );
		System.out.println("请输入需要修改的信息，不需要修改的则输入-1。");
		System.out.print("姓名=");
		String name = s.next();
		if(name.equals("-1"))name=stu.getStuName();
		System.out.print("年级（1-4）=");
		int g = s.nextInt();
		if(g==-1)g=stu.getGrade();
		System.out.print("出生日期（%Y-%M-%D）=");
		String date = s.next();
		if(date.equals("-1")) date=stu.getBirth();
		int p = dao.update("INSERT INTO student VALUES(?,?,?,?)",num,name,g,date );
		if(p==1)System.out.println("修改成功");
		else System.out.println("修改失败");
		return;
	}
	
}
