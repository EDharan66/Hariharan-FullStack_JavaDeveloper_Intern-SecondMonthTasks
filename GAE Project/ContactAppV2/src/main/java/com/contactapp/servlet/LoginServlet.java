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

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	private String empId;
	private String password;

	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }

		  LoginEntity loginEnty=new Gson().fromJson(jb.toString(), LoginEntity.class);
		
		empId = loginEnty.getEmpId().toString();
		password = loginEnty.getPassword();

		if (getValidate()) {

			loadSession("empId", empId, request);

			try {
				LoginEntity entity = (LoginEntity) ObjectifyWebListener.ofy().load().type(LoginEntity.class)
						.id(Long.parseLong(empId)).now();

				if (java.util.Objects.nonNull(entity) && entity.getPassword().equals(password)) {					
					writeResponce(response, new ApiResponce("SUCESS","sucessfuly login","sucess",Collections.singletonMap("id", empId)),200);
					return;
				} 
			} catch (Exception e) {
			}
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
		return java.util.Objects.nonNull(empId) && java.util.Objects.nonNull(password) && !empId.isEmpty() && !password.isEmpty();
	}

	private void loadSession(String key, String value, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
	}

}
