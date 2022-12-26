package com.debanshu777.manager;

import com.debanshu777.daoImpl.FilmHibernateDAO;
import com.debanshu777.model.FilmPOJO;

public interface FilmManagerInterface {
	
	public FilmHibernateDAO getInstance();
	public void insert(FilmPOJO insertfilm);
	public void update(FilmPOJO updatefilm);
	public void delete(int id);
	public String fetch(String data_querry,int page, int total,String count_querry) ;
	public String getData(String data_querry,int page, int total,String count_querry);
}
