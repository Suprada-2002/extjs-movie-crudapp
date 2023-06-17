package com.suprada.manager;

import com.suprada.daoImpl.FilmHibernateDAO;
import com.suprada.model.FilmPOJO;

public interface FilmManagerInterface {
	
	public FilmHibernateDAO getInstance();
	public void insert(FilmPOJO insertfilm);
	public void update(FilmPOJO updatefilm);
	public void delete(int id);
	public String fetch(String data_querry,int page, int total,String count_querry) ;
	public String getData(String data_querry,int page, int total,String count_querry);
}
