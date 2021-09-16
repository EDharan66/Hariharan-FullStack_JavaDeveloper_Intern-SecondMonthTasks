package com.contactapp.home.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;

import com.cantactapp.service.OfyService;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet{
	
	String empId;
	String password,entityPassword;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + "<html><body>");
		
		empId = request.getParameter("userid");
		password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.setAttribute("empId", empId);
		
		
		SignupEntity entity = (SignupEntity) OfyService.ofy().load().type(SignupEntity.class).id(Long.parseLong(empId)).now();
//		System.out.println(entity.name+", "+entity.password+", "+password);
		if(!entity.equals(null)) {
			entityPassword = entity.password;
		}else {
			response.sendRedirect("index");
		}
		
		
		
		try {
			if (!validate()) {
				response.sendRedirect("index");
			} else if (validate() && entityPassword.equals(password)) {
				
				 request.getRequestDispatcher("homepage").forward(request, response);
			} else {
				out.println(
						"<p align=\"center\">your password/name is incorrect, Please enter correctly or signup new!!<p>");
				response.sendRedirect("index");

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			out.println("</body></html>");
			out.close();
		}
	}
	
	boolean validate() {

		if (!empId.isEmpty() && !password.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
}
