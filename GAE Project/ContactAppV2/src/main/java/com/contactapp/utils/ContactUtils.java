package com.contactapp.utils;

import com.contactapp.bean.ContactEntity;
import com.contactapp.constants.ContactAppApiConstant;
import com.contactapp.constants.ContactAppApiConstant.Basic;
import com.contactapp.controller.ContactAppUtil;
import com.contactapp.service.ObjectifyWebListener;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.contactapp.constants.ContactAppApiConstant.ERROR;
import static com.contactapp.constants.ContactAppApiConstant.SUCCESS;
import static com.contactapp.constants.ContactAppApiConstant.ApiError.error;
import static com.contactapp.constants.ContactAppApiConstant.ApiError.success;
import static com.contactapp.constants.ContactAppApiConstant.ApiStatusCode.FAILED;
import static com.contactapp.constants.ContactAppApiConstant.ApiStatusCode.OK;
import static com.contactapp.constants.ContactAppApiConstant.Basic.*;
import static com.contactapp.controller.ContactAppUtil.apiResponseWriter;

public class ContactUtils {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public ContactUtils(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static ContactUtils getInstance(HttpServletRequest request, HttpServletResponse response) {
        return new ContactUtils(request, response);
    }

    boolean postValidate(ContactEntity entity) {
        return !entity.getName().isEmpty() && !entity.getEmailId().isEmpty() && !entity.getEmpId().toString().isEmpty() && !entity.getPhoneNo().isEmpty();
    }

    public void processPOST() {
        ContactEntity entity = new Gson().fromJson(ContactAppUtil.getRequestBody(request), ContactEntity.class);
        try {
            if (postValidate(entity)) {
                ObjectifyWebListener.ofy().save().entities(entity).now();
                apiResponseWriter(response, SUCCESS, successfully_load_data, success, entity.getEmpId(), OK);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        apiResponseWriter(response, ERROR, failed_load_data, error, null, FAILED);
    }

    public void processPUT() {

        ContactEntity entity = new Gson().fromJson(ContactAppUtil.getRequestBody(request), ContactEntity.class);
        try {
            if (postValidate(entity)) {
                ObjectifyWebListener.ofy().save().entities(entity).now();
                apiResponseWriter(response, SUCCESS, successfully_data_update, success, entity.getEmpId(), OK);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        apiResponseWriter(response, ERROR, failed_data_update, error, null, FAILED);
    }

    public void processDELETE() {

        String empId = request.getParameter(ContactAppApiConstant.Basic.empId);
        try {
            ObjectifyWebListener.ofy().delete().type(ContactEntity.class).id(Long.parseLong(empId)).now();

            apiResponseWriter(response, SUCCESS, successfully_data_delete, success, Long.parseLong(empId), OK);
            return;

        } catch (Exception e) {
            e.printStackTrace();
        }
        apiResponseWriter(response, ERROR, failed_data_delete, error, null, FAILED);

    }
}
