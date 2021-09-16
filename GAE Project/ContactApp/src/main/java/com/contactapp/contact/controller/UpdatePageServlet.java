package com.contactapp.contact.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.SwitchPoint;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cantactapp.service.ContactEntity;
import com.cantactapp.service.OfyService;

@WebServlet(name = "UpdatePageServlet", urlPatterns = { "/updatepage" })
public class UpdatePageServlet extends HttpServlet {
	String message, foredit, value, contactid;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		message = (String) request.getParameter("message");

		HttpSession session = request.getSession(true);

		request.getRequestDispatcher("contactpage").include(request, response);
		if (message.equals("edit")) {
			
			request.getRequestDispatcher("editcontact.html").include(request, response);
			
		} else {
			value = (String) request.getParameter("value");
			foredit = (String) request.getParameter("foredit");
			contactid = (String) request.getParameter("contactid");

			ContactEntity entity = OfyService.ofy().load().type(ContactEntity.class).id(Long.parseLong(contactid))
					.now();

			switch(foredit) {
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
			default:
				break;
				
			}

//			OfyService.ofy().load().type(SignupEntity.class).id(Long.parseLong(empId)).now();

			OfyService.ofy().save().entities(entity).now();
			request.getRequestDispatcher("displaypage").forward(request, response);
		}
	}
}
