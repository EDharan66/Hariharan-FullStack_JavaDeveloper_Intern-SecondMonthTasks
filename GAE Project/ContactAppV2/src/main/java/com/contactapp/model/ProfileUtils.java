package com.contactapp.model;

import com.contactapp.controller.ContactAppUtil;
import com.contactapp.service.LoginEntity;
import com.contactapp.service.ObjectifyWebListener;
import com.contactapp.servlet.bean.ApiResponce;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Objects;

import static com.contactapp.controller.ContactAppUtil.writeResponce;

public class ProfileUtils {

    final private HttpServletRequest request;
    final private HttpServletResponse response;


    public ProfileUtils(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;


    }

    public static ProfileUtils getInstance(HttpServletRequest request, HttpServletResponse response) {
        return new ProfileUtils(request, response);
    }

    public void processPOST() {
        LoginEntity loginEntity = new Gson().fromJson(ContactAppUtil.getRequestBody(request), LoginEntity.class);


        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session)) {
            loginEntity.setEmpId(Long.parseLong((String) session.getAttribute("empId")));
        }

        try {
            if (postValidate(loginEntity)) {
                ObjectifyWebListener.ofy().save().entities(loginEntity).now();
                writeResponce(response, new ApiResponce("SUCESS", "sucessfuly login", "sucess", Collections.singletonMap("id", loginEntity.getEmpId())), 200);
                return;
            }
        } catch (Exception ignored) {
        }
        writeResponce(response, new ApiResponce("ERROR", "failed login", "error", Collections.singletonMap("id", loginEntity.getEmpId())), 400);

    }

    private boolean postValidate(LoginEntity loginEntity) {
        return !loginEntity.getName().isEmpty() && !loginEntity.getEmailId().isEmpty() && !loginEntity.getEmpId().toString().isEmpty() && !loginEntity.getPhoneNo().isEmpty();
    }
}
