package com.contactapp.controller;

import com.contactapp.bean.ApiResponce;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.contactapp.constants.ContactAppApiConstant.SUCCESS;
import static com.contactapp.constants.ContactAppApiConstant.ApiError.success;
import static com.contactapp.constants.ContactAppApiConstant.ApiStatusCode.OK;
import static com.contactapp.constants.ContactAppApiConstant.Basic.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class ContactAppUtil {

    public static void apiResponseWriter(HttpServletResponse response, String success, String successfully_delete, String success2, Long empId, int ok) {
        writeResponse(response, new ApiResponce(success, successfully_delete, success2, Collections.singletonMap(id, empId)), ok);
    }

    public static void writeResponse(HttpServletResponse response, Object resObject, int statusCode){

        try {
            PrintWriter out = response.getWriter();
            response.setStatus(statusCode);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(new Gson().toJson(resObject));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getRequestBody(HttpServletRequest request) {
        StringBuilder jb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception ignored) {

        }
        return jb.toString();
    }

    public static void signOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String empId = (String) session.getAttribute("empId");
            session.invalidate();
            apiResponseWriter(response,SUCCESS, successfully_sign_out, success, Long.parseLong(empId), OK);
        }
    }
}
