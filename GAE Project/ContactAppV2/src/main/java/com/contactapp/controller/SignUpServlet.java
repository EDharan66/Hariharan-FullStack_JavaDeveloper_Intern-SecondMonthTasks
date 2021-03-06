package com.contactapp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.contactapp.utils.UserUtil;

import java.io.IOException;


@WebServlet (name = "SignUpServlet", urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet{

	/**
	 *
	 * @param request
	 * @param response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		UserUtil.getInstance(request, response).processSignUp();
	}
	
}
