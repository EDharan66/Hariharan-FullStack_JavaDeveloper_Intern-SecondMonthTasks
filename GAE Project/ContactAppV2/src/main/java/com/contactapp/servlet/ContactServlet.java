package com.contactapp.servlet;

import com.contactapp.model.ContactUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ContactServlet", urlPatterns = "/contact")
public class ContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        ContactUtils.getInstance(request, response).processPOST();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        ContactUtils.getInstance(request, response).processPUT();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        ContactUtils.getInstance(request, response).processDELETE();
    }
}
