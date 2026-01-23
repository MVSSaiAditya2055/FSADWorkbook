package com.klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
	private int studentId;
	private String name;
	private String gender;
	@Autowired
	private Certification certificate;
	
	public Student() {
		this.studentId=2055;
		this.name="Aditya";
		this.gender="Male";
	}
	
	public void display() {
		System.out.println("following are the certification details:");
		System.out.println("StudentId:"+studentId);
		System.out.println("Name:"+name);
		System.out.println("Gender:"+gender);
		System.out.println("CertificateId:"+certificate.getId());
		System.out.println("CertificateName:"+certificate.getName());
		System.out.println("DateOfCompletion:"+certificate.getDate());
	}
}
