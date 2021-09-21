package com.contactapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.contactapp.controller.ContactAppUtil;
import com.contactapp.servlet.bean.ApiResponce;
import com.google.gson.Gson;

@WebServlet(name = "SignOutClass", urlPatterns = "/signout")
public class SignOutClass extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContactAppUtil.signOut(request,response);
    }
}
