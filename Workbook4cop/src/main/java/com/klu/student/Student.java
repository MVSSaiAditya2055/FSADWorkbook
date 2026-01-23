package com.klu.student;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student {
	private int studentId;
	private String name;
	private String course;
	private int year;
	public Student(@Value("101")int studentId,@Value("Adithya")String name) {
		this.studentId=studentId;
		this.name=name;
	}
	@Value("DBMS")
	public void setCourse(String course) {
		this.course=course;
	}
	
	@Value("4")
	public void setYear(int year) {
		this.year=year;
	}
	public void display() {
		System.out.println("StudentID: "+studentId);
		System.out.println("Name: "+name);
		System.out.println("Course: "+course);
		System.out.println("Year: "+year);
	}
}
