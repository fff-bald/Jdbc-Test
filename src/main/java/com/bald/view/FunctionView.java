package com.bald.view;

public class FunctionView {
	public static void functionView() {
		System.out.println("----------------------------------------");
		System.out.println("1:查看年级人数");
		System.out.println("2:查看年级所有学生");
		System.out.println("3:按照学号查询学生");
		System.out.println("4:按照姓名查询学生");
		System.out.println("5:按照学号删除学生信息");
		System.out.println("6:按照学号修改学生信息");
		System.out.println("7:新增学生信息");
		System.out.println("8:新生入学");//批处理增加1000名学生
		System.out.println("9:按照学生学号让学生升学");//年级+1，用事务处理保证sql语句同时执行
		System.out.println("10:退出系统");
		System.out.print("请输入对应功能代表的数字（1-10）:");
	}
}
