package com.bald.bean;
/*
 * Ñ§ÉúÀà
 */
public class Student {
	private int num;
	private String stuName;
	private int grade;
	private String birth;
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public Student(int num, String stuName, int grade, String birth) {
		super();
		this.num = num;
		this.stuName = stuName;
		this.grade = grade;
		this.birth = birth;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", stuName=" + stuName + ", grade=" + grade + ", birth=" + birth + "]";
	}
	
	
}
