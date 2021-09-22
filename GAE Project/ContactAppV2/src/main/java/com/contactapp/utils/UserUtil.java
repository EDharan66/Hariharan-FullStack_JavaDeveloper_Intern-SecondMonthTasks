package com.contactapp.utils;

import com.contactapp.bean.LoginEntity;
import com.contactapp.controller.ContactAppUtil;
import com.contactapp.service.ObjectifyWebListener;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.contactapp.constants.ContactAppApiConstant.ERROR;
import static com.contactapp.constants.ContactAppApiConstant.SUCCESS;
import static com.contactapp.constants.ContactAppApiConstant.ApiError.error;
import static com.contactapp.constants.ContactAppApiConstant.ApiError.success;
import static com.contactapp.constants.ContactAppApiConstant.ApiStatusCode.FAILED;
import static com.contactapp.constants.ContactAppApiConstant.ApiStatusCode.OK;
import static com.contactapp.constants.ContactAppApiConstant.Basic.*;
import static com.contactapp.controller.ContactAppUtil.apiResponseWriter;

public class UserUtil {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public UserUtil(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static UserUtil getInstance(HttpServletRequest request, HttpServletResponse response) {
        return new UserUtil(request, response);
    }

    private boolean getValidate(LoginEntity entity) {
        return Objects.nonNull(entity.getEmpId()) && Objects.nonNull(entity.getPassword()) && !entity.getEmpId().toString().isEmpty() && !entity.getPassword().isEmpty();
    }

    private boolean postValidate(LoginEntity entity) {
        return !entity.getName().isEmpty() && !entity.getEmailId().isEmpty() && !entity.getEmpId().toString().isEmpty() && !entity.getPhoneNo().isEmpty() && !entity.getPassword().isEmpty();
    }

    private void loadSession(String value, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("empId", value);
    }

    public void processLogin() {
        LoginEntity entity = new Gson().fromJson(ContactAppUtil.getRequestBody(request), LoginEntity.class);
        if (getValidate(entity)) {
            loadSession(entity.getEmpId().toString(), request);
            String password = entity.getPassword();
            try {
                entity = ObjectifyWebListener.ofy().load().type(LoginEntity.class).id(entity.getEmpId()).now();
                if (Objects.nonNull(entity) && password.equals(entity.getPassword())) {
                    apiResponseWriter(response, SUCCESS, successfully_login, success, entity.getEmpId(), OK);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        apiResponseWriter(response, ERROR, failed_login, error, null, FAILED);
    }

    public void processSignUp() {
        LoginEntity entity = new Gson().fromJson(ContactAppUtil.getRequestBody(request), LoginEntity.class);
        
        try {
            if (postValidate(entity)) {
                loadSession(entity.getEmpId().toString(), request);
                
                ObjectifyWebListener.ofy().save().entities(entity).now();
                apiResponseWriter(response, SUCCESS, successfully_sign_up, success, entity.getEmpId(), OK);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        apiResponseWriter(response, ERROR, failed_sign_up, error, null, FAILED);
    }
}
