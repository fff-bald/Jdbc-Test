package com.bald.service;

import java.util.List;
import java.util.Scanner;

import com.bald.bean.Student;
import com.bald.dao.StuDao;

public class StuService extends StuDao {
	StuDao dao = new StuDao();
	/*
	 * ����ѧ������
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
	 * ����ѧ����Ϣ
	 */
	public List<Student> stu(int grade){
		List<Student> list = dao.queryNulti("select * from student where grade=?", Student.class, grade);
		return list;
	}
	/*
	 * ͨ��ѧ�Ų�ѯѧ��
	 */
	public Student queryIdStu(int num) {
		Student stu = dao.querySingle("select * from student where num=?", Student.class, num);
		return stu;
	}
	/*
	 * ͨ��������ѯѧ��
	 */
	public Student queryNameStu(String name) {
		Student stu = dao.querySingle("select * from student where stuName=?", Student.class, name);
		return stu;
	}
	/*
	 * ����ѧ����Ϣ��ѧ�š��������꼶����������
	 */
	public void insert() {
		Scanner s = new Scanner(System.in);
		System.out.println("����������ѧ������Ϣ");
		System.out.print("ѧ��=");
		int num = s.nextInt();
		System.out.print("����=");
		String name = s.next();
		System.out.print("�꼶��1-4��=");
		int g = s.nextInt();
		System.out.print("�������ڣ�%Y-%M-%D��=");
		String date = s.next();
		int p = dao.update("INSERT INTO student VALUES(?,?,?,?)",num,name,g,date );
		if(p==1)System.out.println("��ӳɹ�");
		else System.out.println("���ʧ��");
		return;
	}
	/*
	 * ɾ��ָ��ѧ��
	 */
	public void delete() {
		Scanner s = new Scanner(System.in);
		System.out.print("��������Ҫɾ��ѧ����ѧ��:");
		int num = s.nextInt();
		int p = dao.update("delete from student where num=?",num );
		if(p==1)System.out.println("ɾ���ɹ�");
		else System.out.println("ɾ��ʧ��");
		return;
	}
	/*
	 * һ��������һ����ѧ������һ�꼶������������
	 */
	public void entrance() {
		Object count = dao.scalar("select max(num) from student");
		String sql="insert into student values(?,?,?,?)";
		Object[][] params= new Object[1000][];//1000����Ϣ����һ��
		int n = Integer.parseInt(count+"");//�ҳ���ǰ����ѧ��
		for(int i=n+1,j=0;i<1001+n;i++,j++) {
				params[j]= new Object[4];
				params[j][0] = i;
				params[j][1] = "stu"+i;
				params[j][2] = 1;
				params[j][3] = "2000-1-1";	
			}
			dao.batchUpdate(sql, params);
		System.out.println("����¼��ɹ�");
		return;
	}
	/*
	 * �޸�ָ��ѧ��ѧ����Ϣ
	 */
	public void alter() {
		Scanner s = new Scanner(System.in);
		System.out.print("��������Ҫ�޸���Ϣѧ����ѧ��:");
		int num = s.nextInt();
		Student stu = dao.querySingle("select * from student where num=?", Student.class, num);
		if(stu==null) {
			System.out.println("���޴��ˣ�");
			return;
		}
		dao.update("delete from student where num=?",num );
		System.out.println("��������Ҫ�޸ĵ���Ϣ������Ҫ�޸ĵ�������-1��");
		System.out.print("����=");
		String name = s.next();
		if(name.equals("-1"))name=stu.getStuName();
		System.out.print("�꼶��1-4��=");
		int g = s.nextInt();
		if(g==-1)g=stu.getGrade();
		System.out.print("�������ڣ�%Y-%M-%D��=");
		String date = s.next();
		if(date.equals("-1")) date=stu.getBirth();
		int p = dao.update("INSERT INTO student VALUES(?,?,?,?)",num,name,g,date );
		if(p==1)System.out.println("�޸ĳɹ�");
		else System.out.println("�޸�ʧ��");
		return;
	}
	
}
