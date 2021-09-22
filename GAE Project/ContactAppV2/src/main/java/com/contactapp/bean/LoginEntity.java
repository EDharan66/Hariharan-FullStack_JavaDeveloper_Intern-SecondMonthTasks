package com.contactapp.bean;

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

	public LoginEntity setEmpId(Long empId) {
		this.empId = empId;
		return this;
	}

	public String getEmailId() {
		return emailId;
	}

	public LoginEntity setEmailId(String emailId) {
		this.emailId = emailId;
		return this;
	}

	public String getName() {
		return name;
	}

	public LoginEntity setName(String name) {
		this.name = name;
		return this;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public LoginEntity setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public LoginEntity setPassword(String password) {
		this.password = password;
		return this;
	}
}
