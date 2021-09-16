package com.contactapp.home.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cantactapp.service.OfyService;

@WebServlet(name = "SingupServlet", urlPatterns = { "/signup" })
public class SignupServlet extends HttpServlet {
	
	String emailId, name, empId, phoneNo, password, conformPassword;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + "<html><body>");

		emailId = request.getParameter("Email");
		name = request.getParameter("Name");
		empId = request.getParameter("EmpId");
		phoneNo = request.getParameter("PhoneNo");
		password = request.getParameter("password");
		conformPassword = request.getParameter("conformPassword");
		
		HttpSession session = request.getSession();
		session.setAttribute("empId", empId);

		try {
			if (validate() && password.equals(conformPassword)) {
				SignupEntity entity = new SignupEntity(empId, emailId, name, phoneNo, password);
				
//				OfyService.ofy().load().type(SignupEntity.class).id(Long.parseLong(empId)).now();
				OfyService.ofy().save().entities(entity).now();

				request.getRequestDispatcher("homepage").forward(request, response);
				

			} else {
				response.sendRedirect("index");
			}
		} catch (Exception e) {

		} finally {
			out.println("</body></html>");
			out.close();
			
		}
	}

	boolean validate() {

		if (!name.isEmpty() && !emailId.isEmpty() && !empId.isEmpty() && !phoneNo.isEmpty() && !password.isEmpty()
				&& !conformPassword.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

}
