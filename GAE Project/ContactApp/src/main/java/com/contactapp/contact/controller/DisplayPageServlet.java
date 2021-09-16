package com.contactapp.contact.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.contactapp.service.ContactEntity;
import com.contactapp.service.OfyService;
import com.google.appengine.api.search.query.QueryParser.value_return;


@WebServlet(name = "DisplayPageServlet", urlPatterns = { "/displaypage" })
public class DisplayPageServlet extends HttpServlet {
	String message, searchlist=null,value;
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		message = (String)session.getAttribute("message");
		searchlist = (String)session.getAttribute("searchlist");

	
		
		out.println("<style>\r\n"
				+ "table.display {\r\n"
				+ "	border: 2px solid blueviolet;\r\n"
				+ "	padding: 10px;"
				+ "margin: auto;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "td, th {\r\n"
				+ "	padding-bottom: 10px;\r\n"
				+ "}\r\n"
				+ "</style>");
		
		out.println("<body>\r\n"
				+ "<h4 align=\"center\">display contact</h4>"
				+ "<table class=\"display\">"+"<tr>" + "<th>EmpId</th>" + "<th>Name</th>" + "<th>Email Id</th>" + "<th>Phone No</th>"
				+ "</tr>");
		if (searchlist.equals("all")) {

			List<ContactEntity> entity = OfyService.ofy().load().type(ContactEntity.class).list();
			for (int i = 0; i < entity.size(); i++) {
				out.println("<tr>" + "<td>" + entity.get(i).getEmpId() + "</td>" + "<td>" + entity.get(i).getName()
						+ "</td>" + "<td>" + entity.get(i).getEmailId() + "</td>" + "<td>" + entity.get(i).getPhoneNo()
						+ "</td>"
						+ "</tr>");
			}
			
		} else if(searchlist.equals("empId")) {
			
				String empId = (String) session.getAttribute("value");
				ContactEntity entity = OfyService.ofy().load().type(ContactEntity.class).id(Long.parseLong(empId)).now();
				
				out.println("<tr>" + "<td>" + entity.getEmpId() + "</td>" + "<td>" + entity.getName()
						+ "</td>" + "<td>" + entity.getEmailId() + "</td>" + "<td>" + entity.getPhoneNo()
						+ "</td>"
						+ "</tr>");
			
		} else{
			value = (String) session.getAttribute("value");
			
			List<ContactEntity> entity =  OfyService.ofy().load().type(ContactEntity.class).filter(searchlist,value).list();
//			System.out.println(value+", "+entity);
			
			for (int i = 0; i < entity.size(); i++) {
				out.println("<tr>" + "<td>" + entity.get(i).getEmpId() + "</td>" + "<td>" + entity.get(i).getName()
						+ "</td>" + "<td>" + entity.get(i).getEmailId() + "</td>" + "<td>" + entity.get(i).getPhoneNo()
						+ "</td>"
						+ "</tr>");
			}
		
		}
		out.println("<table></body>");

	}
}
