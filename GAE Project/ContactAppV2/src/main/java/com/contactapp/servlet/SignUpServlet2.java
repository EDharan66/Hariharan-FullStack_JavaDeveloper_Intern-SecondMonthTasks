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


import com.contactapp.service.LoginEntity;
import com.contactapp.service.ObjectifyWebListener;
import com.contactapp.servlet.bean.ApiResponce;
import com.google.gson.Gson;


@WebServlet (name = "SignUpServlet2", urlPatterns = "/signup")
public class SignUpServlet2 extends HttpServlet{

	private String empId, name, phoneNo,emailId;
    private	String password,conformPassword;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.sendRedirect("homepage.html");
        }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		StringBuffer jb = new StringBuffer();
		String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  LoginEntity loginEnty=new Gson().fromJson(jb.toString(), LoginEntity.class);
		
		emailId =loginEnty.getEmailId();
		name = loginEnty.getName();
		empId = loginEnty.getEmpId().toString();
		phoneNo = loginEnty.getPhoneNo();
		password = loginEnty.getPassword();
		
		

		try {
			if (postValidate()) {
				
				loadSession("empId",empId,request);
				LoginEntity entity = new LoginEntity(empId, emailId, name, phoneNo, password);
				
				ObjectifyWebListener.ofy().save().entities(entity).now();
				
				writeResponce(response, new ApiResponce("SUCESS","sucessfuly login","sucess",Collections.singletonMap("id", empId)),200);
				return;
			}else {
				response.sendRedirect("/index.html");
			}
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
	
	boolean getValidate() {

		if (!empId.isEmpty() && !password.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
	
	boolean postValidate() {

		if (!name.isEmpty() && !emailId.isEmpty() && !empId.isEmpty() && !phoneNo.isEmpty() && !password.isEmpty()
				) {
			return true;
		} else {
			return false;
		}

	}
	

	
	private void loadSession(String key, String value, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
		
		
	}
	
	
}
