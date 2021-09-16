package com.contactapp.contact.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.objectify.impl.Session;



@WebServlet(name = "SearchPageServlet", urlPatterns = { "/searchpage" })
public class SearchPageServlet extends HttpServlet{
	String message,searchlist,value;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		message = (String) request.getParameter("message");
		
		
//		System.out.println(message);
		HttpSession session = request.getSession(true);
		

		request.getRequestDispatcher("contactpage").include(request, response);
		if(message.equals("search")) {
			request.getRequestDispatcher("search.html").include(request, response);
		}else {
			value = (String) request.getParameter("value");
			searchlist =(String) request.getParameter("searchlist");
		
			session.setAttribute("message", message);
			session.setAttribute("searchlist", searchlist);
			session.setAttribute("value", value);
			
			System.out.println(searchlist);
			request.getRequestDispatcher("displaypage").forward(request, response);
		}
		
	}
	
	
}
