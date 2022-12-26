package com.debanshu777.daoImpl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.debanshu777.model.FilmPOJO;

public class FilmHibernateSingleton {
	
	private static SessionFactory factory;
	private FilmHibernateSingleton(){
	}
	
	public static synchronized SessionFactory getSessionfactory() {
		if (factory == null) {
			Configuration configuration=new Configuration().configure().addAnnotatedClass(FilmPOJO.class); // configures settings from hibernate.cfg.xml
		    StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		    serviceRegistryBuilder.applySettings(configuration.getProperties());
		    ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		    
			factory = configuration.buildSessionFactory(serviceRegistry);
        }
        return factory;
	}

}
