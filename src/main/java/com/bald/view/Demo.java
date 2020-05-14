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
		System.out.print("���������Ա������");
		String adminName=sc.next();
		System.out.print("���������Ա���룺");
		String pw=sc.next();
		if(as.login(adminName, pw)) {
			System.out.println("����Ա"+adminName+"��ã���ӭ����ѧ������ϵͳ��");
			while(true) {
				gs.stuUpdate();//���¸��꼶ѧ������
				FunctionView.functionView();
				int n = sc.nextInt();	
				switch(n) {
					case 1:
						System.out.print("�������ѯ�꼶��0-4��,0����ȫУ:");
						n=sc.nextInt();
						if(n!=1&&n!=2&&n!=3&&n!=4&&n!=0) System.out.println("�����ڴ��꼶��");
						else {
							int result = ss.stuNum(n);
							if(n==0) System.out.println("ȫУ����"+result+"��ѧ��");
							else System.out.println("�꼶"+n+"����"+result+"��ѧ��");
						}
						break;
					case 2:
						System.out.print("�������ѯ�꼶��1-4��:");
						n=sc.nextInt();
						if(n!=1&&n!=2&&n!=3&&n!=4) System.out.println("�����ڴ��꼶��");
						else {
							List <Student> list = ss.stu(n);
							for(Student s : list) {
								System.out.println(s);
							}
						}
						break;
					case 3:
						System.out.print("�������ѯѧ��ѧ��:");
						n=sc.nextInt();
						stu = ss.queryIdStu(n);
						if(stu==null) System.out.println("�����ڴ�ѧ��");
						else 	System.out.println(stu);
						break;
					case 4:
						System.out.print("�������ѯѧ������:");
						String s=sc.next();
						stu = ss.queryNameStu(s);
						if(stu==null)System.out.println("�����ڴ�ѧ��");
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
						System.out.print("�������ѧ��ѧ��:");
						int num = sc.nextInt();
						gs.higher(num);
						break;
					case 10:
						System.out.println("ϵͳ�˳��ɹ�");
						return;
					default:
						System.out.println("��������");
						
				}		
			}
		} else {
			System.out.println("����Ա�û�������������");
		}
	}
}
