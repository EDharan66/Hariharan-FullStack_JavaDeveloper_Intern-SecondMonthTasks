package com.contactapp.controller;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ContactAppUtil {

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

    public static void  writeResponce(HttpServletResponse response, Object resObject, int statusCode){
        PrintWriter out;
        try {
            out = response.getWriter();
            response.setStatus(statusCode);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(new Gson().toJson(resObject));
            out.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
