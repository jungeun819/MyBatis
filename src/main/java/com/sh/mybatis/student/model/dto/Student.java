package com.sh.mybatis.student.model.dto;

import java.time.LocalDateTime;

public class Student {
	// field
	private int no;
	private String name;
	private String tel;
	private LocalDateTime createAt;
	
	// constructor
	public Student() {
		super();
	}
	public Student(int no, String name, String tel, LocalDateTime createAt) {
		super();
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.createAt = createAt;
	}
	
	// getter setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	@Override
	public String toString() {
		return "Student [no=" + no + ", name=" + name + ", tel=" + tel + ", createAt=" + createAt + "]";
	}
}
