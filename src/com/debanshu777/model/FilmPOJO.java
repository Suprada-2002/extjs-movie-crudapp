package com.debanshu777.model;

public class FilmPOJO {
	

	private Short film_id;

	private String title;
	

	private String description;
	

	private String release_year;
	

	private Integer language_id;
	

	private String rating;
	

	private String secial_features;
	

	private String director_name;
	

	private int is_disabled;
	
	private String language;
	
	public FilmPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIs_disabled() {
		return is_disabled;
	}
	public Integer getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(Integer language_id) {
		this.language_id = language_id;
	}
	public void setIs_disabled(int is_disabled) {
		this.is_disabled = is_disabled;
	}
	public FilmPOJO(Short film_id, String title, String description, String release_year, int language_id,
			String rating, String secial_features, String director_name, int is_disabled) {
		super();
		this.film_id = film_id;
		this.title = title;
		this.description = description;
		this.release_year = release_year;
		this.language_id = language_id;
		this.rating = rating;
		this.secial_features = secial_features;
		this.director_name = director_name;
		this.is_disabled = is_disabled;
	}
	public Short getFilm_id() {
		return film_id;
	}
	public void setFilm_id(Short film_id) {
		this.film_id = film_id;
	}
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
	
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getSecial_features() {
		return secial_features;
	}
	public void setSecial_features(String secial_features) {
		this.secial_features = secial_features;
	}
	public String getDirector_name() {
		return director_name;
	}
	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}
	@Override
	public String toString() {
		return "FilmPOJO [film_id=" + film_id + ", title=" + title + ", description=" + description + ", release_year="
				+ release_year + ", language_id=" + language_id + ", rating=" + rating + ", secial_features="
				+ secial_features + ", director_name=" + director_name + ", is_disabled=" + is_disabled + "]";
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
