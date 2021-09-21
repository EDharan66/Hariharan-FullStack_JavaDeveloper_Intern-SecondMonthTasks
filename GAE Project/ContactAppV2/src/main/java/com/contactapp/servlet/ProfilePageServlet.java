package com.contactapp.servlet;

import com.contactapp.model.ProfileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfilePageServlet", urlPatterns = { "/profile" })
public class ProfilePageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileUtils.getInstance(request,response).processGET();
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileUtils.getInstance(request,response).processPOST();
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProfileUtils.getInstance(request, response).processDelete();
	}
}
