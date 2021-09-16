package com.contactapp.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.contactapp.home.controller.SignupEntity;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@WebListener
public class OfyService implements ServletContextListener {
	

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ObjectifyService.init();
		ObjectifyService.register(SignupEntity.class);
		ObjectifyService.register(ContactEntity.class);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}
}
