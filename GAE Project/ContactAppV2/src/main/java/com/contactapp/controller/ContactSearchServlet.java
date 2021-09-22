package com.contactapp.controller;

import com.contactapp.bean.ContactEntity;
import com.contactapp.service.ObjectifyWebListener;

import static com.contactapp.constants.ContactAppApiConstant.ApiStatusCode.OK;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ContactSearchServlet", urlPatterns = { "/search" })
public class ContactSearchServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) {
			ContactAppUtil.writeResponse(response,ObjectifyWebListener.ofy().load().type(ContactEntity.class).list(), OK);
	}
}
