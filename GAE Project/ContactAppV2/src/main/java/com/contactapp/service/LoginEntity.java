package com.contactapp.service;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class LoginEntity {

	@Id
	Long empId;
	String emailId;
	String name;
	String phoneNo;
	String password;
	
	
	
	public LoginEntity() {
		
	}


	public LoginEntity(String empId, String emailId, String name, String phoneNo, String password) {
		this.empId = Long.parseLong(empId);
		this.emailId = emailId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.password = password;
		
	}
	
	
	public Long getEmpId() {
		return empId;
	}
	public String getEmailId() {
		return emailId;
	}
	public String getName() {
		return name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String getPassword() {
		return password;
	}


	public void setEmpId(Long empId) {
		this.empId = empId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
