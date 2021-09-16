package com.contactapp.contact.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.contactapp.home.controller.SignupEntity;
import com.contactapp.service.ContactEntity;
import com.contactapp.service.OfyService;

@WebServlet(name = "CreatePageServlet", urlPatterns = { "/createpage" })
public class CreatePageServlet extends HttpServlet{

	String message;
	String emailId, name, empId, phoneNo;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		message = request.getParameter("message");
		request.getRequestDispatcher("contactpage").include(request, response);
//		out.println("this is create page");
		HttpSession session = request.getSession(true);
		
		if(message.equals("create")) {
			request.getRequestDispatcher("createcontact.html").include(request, response);
		}else {
			emailId = request.getParameter("Email");
			name = request.getParameter("Name");
			empId = request.getParameter("EmpId");
			phoneNo = request.getParameter("PhoneNo");
			
			ContactEntity entity = new ContactEntity(empId, emailId, name, phoneNo);
			
//			OfyService.ofy().load().type(SignupEntity.class).id(Long.parseLong(empId)).now();
			
			OfyService.ofy().save().entities(entity).now();
			
			session.setAttribute("message", message);
			session.setAttribute("searchlist", "all");
			request.getRequestDispatcher("displaypage").forward(request, response);
		}
		
		
	}
	
}
