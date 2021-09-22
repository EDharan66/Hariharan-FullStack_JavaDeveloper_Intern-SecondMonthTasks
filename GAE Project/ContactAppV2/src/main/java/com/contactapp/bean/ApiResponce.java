package com.contactapp.bean;

import java.util.HashMap;
import java.util.Map;



public class ApiResponce {
	private String status_code;
	private String message;
	private String status;
	private Map<String,Object> details = new HashMap();
	
	
	
	public ApiResponce(String status_code, String message, String status, Map<String, Object> details) {
		super();
		this.status_code = status_code;
		this.message = message;
		this.status = status;
		this.details = details;
	}
	public String getStatus_code() {
		return status_code;
	}
	public String getMessage() {
		return message;
	}
	public String getStatus() {
		return status;
	}
	public Map<String, Object> getDetails() {
		return details;
	}
	public ApiResponce setStatus_code(String status_code) {
		this.status_code = status_code;
		return this;
	}
	public ApiResponce setMessage(String message) {
		this.message = message;
		return this;
	}
	public ApiResponce setStatus(String status) {
		this.status = status;
		return this;
	}
	public ApiResponce setDetails(Map<String, Object> details) {
		this.details = details;
		return this;
	}
	
	
	
}
