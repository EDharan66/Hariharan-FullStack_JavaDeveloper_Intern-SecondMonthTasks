package com.contactapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.contactapp.service.ObjectifyWebListener;
import com.contactapp.servlet.bean.ContactEntity;


@WebServlet(name = "ContactSearchServices", urlPatterns = { "/search" })
public class ContactSearchServices  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		List<ContactEntity> entity = ObjectifyWebListener.ofy().load().type(ContactEntity.class).list();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(entity);
		out.flush();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
