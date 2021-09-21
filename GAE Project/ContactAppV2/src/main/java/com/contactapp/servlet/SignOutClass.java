package com.contactapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.contactapp.servlet.bean.ApiResponce;
import com.google.gson.Gson;

@WebServlet (name = "SignOutClass", urlPatterns = "/signout")
public class SignOutClass extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
		String empId = (String) session.getAttribute("empId");
		session.invalidate();
		writeResponce(response, new ApiResponce("SUCESS","sucessfuly login","sucess",Collections.singletonMap("id", empId)),200);
		return;
		}
		
		
		
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
}
