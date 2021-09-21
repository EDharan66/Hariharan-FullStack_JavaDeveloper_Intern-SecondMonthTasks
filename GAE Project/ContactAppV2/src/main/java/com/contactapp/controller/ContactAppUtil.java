package com.contactapp.controller;

import com.contactapp.servlet.bean.ApiResponce;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import static com.contactapp.service.ContactAppApiConstant.ApiError.success;
import static com.contactapp.service.ContactAppApiConstant.ApiStatusCode.OK;
import static com.contactapp.service.ContactAppApiConstant.Basic.*;
import static com.contactapp.service.ContactAppApiConstant.SUCCESS;

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
