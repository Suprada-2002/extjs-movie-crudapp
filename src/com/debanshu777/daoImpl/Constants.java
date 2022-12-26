package com.debanshu777.daoImpl;

public class Constants {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/sakila";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	//Constant
	private static final int IS_DISABLED=1;
	//SQL Queries
	private static final String SELECT_ALL_FILMS="SELECT film_id,title,description,release_year,rating,special_features,director,is_disabled,(SELECT name FROM language WHERE language_id= film.language_id) as \"language\" FROM film WHERE is_disabled=1 limit ?,?";	
	private static final String MODIFIED_SELECT_ALL_FLIMS="SELECT film_id,title,description,release_year,rating,special_features,director,is_disabled,language_id FROM film where is_disabled=1";
	private static final String SELECT_COUNT="SELCET COUNT(title) from film";
	private static final String UPDATE_FILM= "update film set title = ?,description = ?,release_year = ?,language_id = ?,rating = ?,special_features = ?,director = ? where film_id = ?;";
	private static final String INSERT_FILM="INSERT INTO film(film_id,title,description,release_year,rating,language_id,special_features,director,is_disabled) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String MAX_DOC= "SELECT MAX(film_id) FROM film";
	private static final String SOFT_DELETE_BILL="UPDATE film set is_disabled = 0 WHERE film_id = ?;";
	private static final String Count_DOC="SELECT COUNT(*) from film where is_disabled=1";
	
	
	
	public static String getJdbcDriver() {
		return JDBC_DRIVER;
	}
	public static String getDbUrl() {
		return DB_URL;
	}
	public static String getUser() {
		return USER;
	}
	public static String getPass() {
		return PASS;
	}
	public static int getIsDisabled() {
		return IS_DISABLED;
	}
	public static String getSelectAllFilms() {
		return SELECT_ALL_FILMS;
	}
	public static String getSelectCount() {
		return SELECT_COUNT;
	}
	public static String getUpdateFilm() {
		return UPDATE_FILM;
	}
	public static String getInsertFilm() {
		return INSERT_FILM;
	}
	public static String getMaxDoc() {
		return MAX_DOC;
	}
	public static String getSoftDeleteBill() {
		return SOFT_DELETE_BILL;
	}
	public static String getCountDoc() {
		return Count_DOC;
	}
	public static String getModifiedSelectAllFlims() {
		return MODIFIED_SELECT_ALL_FLIMS;
	}
}
