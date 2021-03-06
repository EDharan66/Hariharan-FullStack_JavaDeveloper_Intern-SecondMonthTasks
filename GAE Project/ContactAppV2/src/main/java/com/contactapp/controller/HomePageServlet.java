package com.contactapp.controller;

import static com.contactapp.constants.ContactAppApiConstant.HOME_PAGE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomePageServlet", urlPatterns = { "/home" })
public class HomePageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(HOME_PAGE).include(request, response);
	}
}
