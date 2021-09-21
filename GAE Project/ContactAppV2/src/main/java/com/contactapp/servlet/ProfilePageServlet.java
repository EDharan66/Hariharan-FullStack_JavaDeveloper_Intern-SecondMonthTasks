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


import com.contactapp.controller.ContactAppUtil;
import com.contactapp.model.ProfileUtils;
import com.contactapp.service.LoginEntity;
import com.contactapp.service.ObjectifyWebListener;
import com.contactapp.servlet.bean.ApiResponce;
import com.contactapp.servlet.bean.ContactEntity;
import com.google.gson.Gson;

@WebServlet(name = "ProfilePageServlet", urlPatterns = { "/profile" })
public class ProfilePageServlet extends HttpServlet{
	private String empId, name, phoneNo,emailId,password;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session!=null) {
			empId = (String) session.getAttribute("empId");
		}
		
		
		
		try {
			LoginEntity entity = (LoginEntity) ObjectifyWebListener.ofy().load().type(LoginEntity.class).id(Long.parseLong(empId))
					.now();
				writeResponce(response, entity ,200);
				return;
			
		} catch (Exception e) {

		} 
		writeResponce(response, new ApiResponce("ERROR","failed login","error",Collections.singletonMap("id", empId)),400);
		
		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ProfileUtils.getInstance(request,response).processPOST();
		


	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
			empId = (String) session.getAttribute("empId");
		}
		
		
		try {
			ObjectifyWebListener.ofy().delete().type(LoginEntity.class).id(Long.parseLong(empId)).now();
			writeResponce(response, new ApiResponce("SUCESS","sucessfuly login","sucess",Collections.singletonMap("id", empId)),200);
			session.invalidate();
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
