package com.suprada.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.suprada.daoImpl.ApplicationContextSingleton;
import com.suprada.daoImpl.Constants;
import com.suprada.daoImpl.Utility;
import com.suprada.manager.FilmManagerInterface;
import com.suprada.managerImpl.FilmManager;
import com.opensymphony.xwork2.ActionSupport;
 
public class Getdata extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private FilmManagerInterface manager;
	private String start;
	private String limit;
	private String title;
	private String language;
	private String release_year;
	private String director;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getRelease_year() {
		return release_year;
	}
	public void setRelease_year(String release_year) {
		this.release_year = release_year;
	}
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
 
 
	public String execute() throws IOException {
		

		manager=(FilmManager)ApplicationContextSingleton.getApplicationContext().getBean("filmManger");
		HttpServletResponse response = ServletActionContext.getResponse();
		int start=Integer.parseInt(getStart());
		String title=getTitle();
		String directorName=getDirector();
		String language=getLanguage();
		String releaseYear=getRelease_year();
		String searchSQL= Constants.getModifiedSelectAllFlims();
		String countSQL=Constants.getCountDoc();
		if(title!=null && title.length()>2)
		{
			String s=" and title="+title;
			searchSQL=searchSQL+s;
			countSQL=countSQL+s;
		}
		if(directorName!=null && directorName.length()>2)
		{
			String s=" and director="+directorName;
			searchSQL=searchSQL+s;
			countSQL=countSQL+s;
		}
		if(language!=null && language.length()>2)
		{
			String d=language.substring(1,language.length()-1);
			int x=Utility.languageId(d);
			String s=" and language_id="+x;
			searchSQL=searchSQL+s;
			countSQL=countSQL+s;
		}
		if(releaseYear!=null && releaseYear.length()>2)
		{
			String s=" and release_year="+releaseYear;
			searchSQL=searchSQL+s;
			countSQL=countSQL+s;
		}
		searchSQL=searchSQL+" LIMIT ";
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {

			String listUser = manager.getData(searchSQL,start,Integer.parseInt(getLimit()),countSQL);
	        out.print(listUser);
	        response.setStatus(200);
 
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(400);
 
		} finally {
	        out.flush();
		}
	return null;
}
}