package com.contactapp.utils;

import com.contactapp.bean.LoginEntity;
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
import static com.contactapp.utils.ContactAppUtil.apiResponseWriter;
import static com.contactapp.utils.ContactAppUtil.writeResponse;

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

    private HttpSession getSession() {
        return request.getSession(false);
    }

    private boolean postValidate(LoginEntity loginEntity) {
        return !loginEntity.getName().isEmpty() && !loginEntity.getEmailId().isEmpty() && !loginEntity.getEmpId().toString().isEmpty() && !loginEntity.getPhoneNo().isEmpty();
    }

    private String getEmpId() {
        return (String) getSession().getAttribute(empId);
    }

    public void processPOST() {
        LoginEntity loginEntity = new Gson().fromJson(ContactAppUtil.getRequestBody(request), LoginEntity.class);

        HttpSession session = getSession();
        if (Objects.nonNull(session)) {
            loginEntity.setEmpId(Long.parseLong((String) session.getAttribute("empId")));
        }
        try {
            if (postValidate(loginEntity)) {
                ObjectifyWebListener.ofy().save().entities(loginEntity).now();
                apiResponseWriter(response,SUCCESS, successfully_login, success, loginEntity.getEmpId(), OK);
                return;
            }
        } catch (Exception ignored) {
        }
        apiResponseWriter(response,ERROR, failed_login, error, loginEntity.getEmpId(), FAILED);

    }

    public void processDelete() {
        try {
            LoginEntity entity = new LoginEntity().setEmpId(Long.parseLong(Objects.requireNonNull(getEmpId())));
            ObjectifyWebListener.ofy().delete().type(LoginEntity.class).id(entity.getEmpId()).now();
            apiResponseWriter(response,SUCCESS, successfully_delete, success, entity.getEmpId(), OK);
            getSession().invalidate();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        apiResponseWriter(response,ERROR, failed_delete, error, null, FAILED);

    }

    public void processGET() {
        try {
            LoginEntity entity = new LoginEntity().setEmpId(Long.parseLong(Objects.requireNonNull(getEmpId())));
            entity = ObjectifyWebListener.ofy().load().type(LoginEntity.class).id(entity.getEmpId()).now();
            writeResponse(response, entity, OK);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        apiResponseWriter(response,ERROR, failed_get, error, null, FAILED);
    }



}
