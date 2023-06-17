package com.suprada.dao;

import com.suprada.model.FilmPOJO;

public interface FilmHibernateDAOInterface {

	public String fetch(String data_querry,int page,int total,String count_querry);
	public void insert(FilmPOJO insertfilm);
	public void update(FilmPOJO insertfilm);
	public void delete(int id);

}
