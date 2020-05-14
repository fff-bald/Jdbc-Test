package com.bald.bean;
/*
 * 年级类
 */
public class Grade {
	private int grade;//所在年级
	private int sum;//年纪总人数
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public Grade(int grade, int sum) {
		super();
		this.grade = grade;
		this.sum = sum;
	}
	
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Grade [grade=" + grade + ", sum=" + sum + "]";
	}
	
}
