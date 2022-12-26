package com.debanshu777.daoImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextSingleton {

	private static ApplicationContext context;
	private ApplicationContextSingleton() {
		
	}
	public static synchronized ApplicationContext getApplicationContext() {
		if(context==null) {
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		return context;
	}
}
