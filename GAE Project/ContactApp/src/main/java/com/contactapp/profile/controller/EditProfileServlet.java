package com.contactapp.profile.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.contactapp.home.controller.SignupEntity;
import com.contactapp.service.OfyService;

@WebServlet(name = "EditProfileServlet", urlPatterns = { "/editprofile" })
public class EditProfileServlet extends HttpServlet{
	String empId;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
		 empId = (String) session.getAttribute("empId");
			
		}
		
		String message = request.getParameter("message");
		if(message.equals("updated")) {
			String forUpdate = request.getParameter("forUpdate");
			String value = request.getParameter("value");
			
			SignupEntity entity = OfyService.ofy().load().type(SignupEntity.class).id(Long.parseLong(empId))
					.now();

			switch(forUpdate) {
			case "name":
				entity.setName(value);
				break;
			case "empId":
				entity.setEmpId(Long.parseLong(value));
				break;
			case "phoneNo":
				entity.setPhoneNo(value);
				break;
			case "emailId":
				entity.setEmailId(value);
				break;
			case "password":
				entity.setPassword(value);
				break;
			default:
				break;
				
			}
			
			OfyService.ofy().save().entities(entity).now();
			request.getRequestDispatcher("profilepage").forward(request, response);
		}
	}
}
