package com.bald.view;

import java.util.List;
import java.util.Scanner;

import com.bald.bean.Student;
import com.bald.service.AdminService;
import com.bald.service.GradeService;
import com.bald.service.StuService;

public class Demo {

	public static void main(String[] args) {
		Student stu = new Student();
		StuService ss = new StuService();
		AdminService as = new AdminService();
		GradeService gs = new GradeService();
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入管理员姓名：");
		String adminName=sc.next();
		System.out.print("请输入管理员密码：");
		String pw=sc.next();
		if(as.login(adminName, pw)) {
			System.out.println("管理员"+adminName+"你好，欢迎进入学生管理系统。");
			while(true) {
				gs.stuUpdate();//更新各年级学生人数
				FunctionView.functionView();
				int n = sc.nextInt();	
				switch(n) {
					case 1:
						System.out.print("请输入查询年级（0-4）,0代表全校:");
						n=sc.nextInt();
						if(n!=1&&n!=2&&n!=3&&n!=4&&n!=0) System.out.println("不存在此年级！");
						else {
							int result = ss.stuNum(n);
							if(n==0) System.out.println("全校共有"+result+"名学生");
							else System.out.println("年级"+n+"共有"+result+"名学生");
						}
						break;
					case 2:
						System.out.print("请输入查询年级（1-4）:");
						n=sc.nextInt();
						if(n!=1&&n!=2&&n!=3&&n!=4) System.out.println("不存在此年级！");
						else {
							List <Student> list = ss.stu(n);
							for(Student s : list) {
								System.out.println(s);
							}
						}
						break;
					case 3:
						System.out.print("请输入查询学生学号:");
						n=sc.nextInt();
						stu = ss.queryIdStu(n);
						if(stu==null) System.out.println("不存在此学生");
						else 	System.out.println(stu);
						break;
					case 4:
						System.out.print("请输入查询学生姓名:");
						String s=sc.next();
						stu = ss.queryNameStu(s);
						if(stu==null)System.out.println("不存在此学生");
						else System.out.println(stu);
						break;
					case 5:
						ss.delete();
						break;
					case 6:
						ss.alter();
						break;
					case 7:
						ss.insert();
						break;
					case 8:
						ss.entrance();
						break;
					case 9:
						System.out.print("请输入该学生学号:");
						int num = sc.nextInt();
						gs.higher(num);
						break;
					case 10:
						System.out.println("系统退出成功");
						return;
					default:
						System.out.println("输入有误！");
						
				}		
			}
		} else {
			System.out.println("管理员用户名或密码有误。");
		}
	}
}
