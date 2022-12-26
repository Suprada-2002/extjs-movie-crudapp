package com.debanshu777.actions;


import com.debanshu777.daoImpl.ApplicationContextSingleton;
import com.debanshu777.daoImpl.Constants;
import com.debanshu777.daoImpl.Utility;
import com.debanshu777.manager.FilmManagerInterface;
import com.debanshu777.managerImpl.FilmManager;
import com.debanshu777.model.FilmPOJO;


public class Add {
	private static final long serialVersionUID = 1L;
	private FilmPOJO tmp;
	private FilmManagerInterface manager;
	private String title;
	private String description;
	private String release_year;
	private String director_name;
	private String rating;
	private String language;
	private String secial_features;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRelease_year() {
		return release_year;
	}
	public void setRelease_year(String release_year) {
		this.release_year = release_year;
	}
	public String getDirector_name() {
		return director_name;
	}
	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSecial_features() {
		return secial_features;
	}
	public void setSecial_features(String secial_features) {
		this.secial_features = secial_features;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String execute() {
		
		tmp=(FilmPOJO)ApplicationContextSingleton.getApplicationContext().getBean("filmPOJO");
		manager=(FilmManager)ApplicationContextSingleton.getApplicationContext().getBean("filmManger");
		tmp.setTitle(getTitle());
		tmp.setDescription(getDescription());
		tmp.setRelease_year(getRelease_year());
		tmp.setDirector_name(getDirector_name());
		tmp.setRating(getRating());
		tmp.setLanguage_id(Utility.languageId(getLanguage()));
		System.out.println(getSecial_features().replace(", ",","));
		tmp.setSecial_features(getSecial_features().replace(", ",","));
		tmp.setIs_disabled(Constants.getIsDisabled());
		manager.insert(tmp);
		
		return null;
	}
	
}
