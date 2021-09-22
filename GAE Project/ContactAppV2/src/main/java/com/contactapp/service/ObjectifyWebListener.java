package com.contactapp.service;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.contactapp.bean.ContactEntity;
import com.contactapp.bean.LoginEntity;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@WebListener
public class ObjectifyWebListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ObjectifyService.init();
    ObjectifyService.register(LoginEntity.class);
    ObjectifyService.register(ContactEntity.class);
   
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
  }
  
  public static Objectify ofy() {
		return ObjectifyService.ofy();
	}
}