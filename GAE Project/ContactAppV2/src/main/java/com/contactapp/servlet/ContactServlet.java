package com.contactapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.contactapp.service.ObjectifyWebListener;
import com.contactapp.servlet.bean.ApiResponce;
import com.contactapp.servlet.bean.ContactEntity;
import com.google.gson.Gson;


@WebServlet (name = "ContactServlet", urlPatterns = "/contact")
public class ContactServlet extends HttpServlet{

	private String empId, name, phoneNo,emailId;
   
    
  

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  ContactEntity contactEntity=new Gson().fromJson(jb.toString(), ContactEntity.class);
		
		emailId =contactEntity.getEmailId();
		name = contactEntity.getName();
		empId = contactEntity.getEmpId().toString();
		phoneNo = contactEntity.getPhoneNo();
		
		
		

		try {
			if (postValidate()) {
				
				
				ContactEntity entity = new ContactEntity(empId, emailId, name, phoneNo);
				
				ObjectifyWebListener.ofy().save().entities(entity).now();
				
				writeResponce(response, new ApiResponce("SUCESS","sucessfuly login","sucess",Collections.singletonMap("id", empId)),200);
				return;
			}
		} catch (Exception e) {

		} 
		writeResponce(response, new ApiResponce("ERROR","failed login","error",Collections.singletonMap("id", empId)),400);
		
	}
	
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  ContactEntity contactEntity=new Gson().fromJson(jb.toString(), ContactEntity.class);
		
		emailId =contactEntity.getEmailId();
		name = contactEntity.getName();
		empId = contactEntity.getEmpId().toString();
		phoneNo = contactEntity.getPhoneNo();
		
		
		

		try {
			if (postValidate()) {
				
				
				ContactEntity entity = new ContactEntity(empId, emailId, name, phoneNo);
				
				ObjectifyWebListener.ofy().save().entities(entity).now();
				
				writeResponce(response, new ApiResponce("SUCESS","sucessfuly login","sucess",Collections.singletonMap("id", empId)),200);
				return;
			}
		} catch (Exception e) {

		} 
		writeResponce(response, new ApiResponce("ERROR","failed login","error",Collections.singletonMap("id", empId)),400);
		
	}
	
	
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
		empId = request.getParameter("empId");
		
		
		try {
			
				
				
				ObjectifyWebListener.ofy().delete().type(ContactEntity.class).id(Long.parseLong(empId)).now();

				
				writeResponce(response, new ApiResponce("SUCESS","sucessfuly login","sucess",Collections.singletonMap("id", empId)),200);
				return;
			
		} catch (Exception e) {

		} 
		writeResponce(response, new ApiResponce("ERROR","failed login","error",Collections.singletonMap("id", empId)),400);
		
	}
	
	void writeResponce(HttpServletResponse response,Object resObject,int statusCode){
		PrintWriter out;
		try {
			out = response.getWriter();
			response.setStatus(statusCode);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(new Gson().toJson(resObject));
			out.flush(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	boolean postValidate() {

		if (!name.isEmpty() && !emailId.isEmpty() && !empId.isEmpty() && !phoneNo.isEmpty()
				) {
			return true;
		} else {
			return false;
		}

	}
	

	
	
	
	
}
