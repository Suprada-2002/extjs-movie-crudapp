package com.suprada.actions;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.suprada.daoImpl.ApplicationContextSingleton;
import com.suprada.daoImpl.FilmDAO;


public class Fetch{
	private FilmDAO filmDAO;
	private String start;
	private String limit;
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	
	public String execute() {

		filmDAO=(FilmDAO)ApplicationContextSingleton.getApplicationContext().getBean("filmDAO");
		
		String filmJson=filmDAO.selectAllList2(Integer.parseInt(getStart()),Integer.parseInt(getLimit()));
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    try {
			response.getWriter().print(filmJson);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return null;
	}
}
