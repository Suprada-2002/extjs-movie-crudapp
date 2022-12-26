package com.debanshu777.actions;


import com.debanshu777.daoImpl.ApplicationContextSingleton;
import com.debanshu777.manager.FilmManagerInterface;
import com.debanshu777.managerImpl.FilmManager;
import com.opensymphony.xwork2.ActionSupport;

public class Delete extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String[] id;
	private FilmManagerInterface manager;
	
	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String execute() {
		

		manager=(FilmManager)ApplicationContextSingleton.getApplicationContext().getBean("filmManger");
		for(int i =0;i<getId().length;i++) {
			manager.delete(Integer.parseInt(getId()[i]));
		}
		return null;
	}

}
