package com.contactapp.servlet;

import com.contactapp.controller.ContactAppUtil;
import com.contactapp.service.ObjectifyWebListener;
import com.contactapp.service.ContactEntity;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.contactapp.service.ContactAppApiConstant.ApiStatusCode.OK;


@WebServlet(name = "ContactSearchServlet", urlPatterns = { "/search" })
public class ContactSearchServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) {
			ContactAppUtil.writeResponse(response,ObjectifyWebListener.ofy().load().type(ContactEntity.class).list(), OK);
	}
}
