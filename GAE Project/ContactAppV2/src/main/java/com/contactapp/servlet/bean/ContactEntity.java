package com.contactapp.servlet.bean;

import com.google.gson.Gson;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class ContactEntity {
	
	@Id Long empId;
	
	@Index String name;
	@Index String emailId;
	@Index String phoneNo;
	
	
	
	public ContactEntity() {
		
	}


	public ContactEntity(String empId, String emailId, String name, String phoneNo) {
		this.empId = Long.parseLong(empId);
		this.emailId = emailId;
		this.name = name;
		this.phoneNo = phoneNo;
		
		
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


	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	
	
	
}
