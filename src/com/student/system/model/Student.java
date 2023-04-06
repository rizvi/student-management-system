package com.student.system.model;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

public class Student implements Serializable {
	@CsvBindByName(column = "sid")
	private Long sid;
	@CsvBindByName(column = "name")
	private String name;
	@CsvBindByName(column = "gender")
	private String gender;
	@CsvBindByName(column = "contactNo")
	private String contactNo;

	public Student() {
	}

	public Student(long sid, String name, String gender, String contactNo) {
		this.sid = sid;
		this.name = name;
		this.gender = gender;
		this.contactNo = contactNo;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", sid=" + sid +
				", gender='" + gender + '\'' +
				", contactNo='" + contactNo + '\'' +
				'}';
	}
}
