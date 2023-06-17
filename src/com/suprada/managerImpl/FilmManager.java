package com.suprada.managerImpl;

import com.suprada.dao.FilmHibernateDAOInterface;
import com.suprada.daoImpl.ApplicationContextSingleton;
import com.suprada.daoImpl.FilmDAO;
import com.suprada.daoImpl.FilmHibernateDAO;
import com.suprada.manager.FilmManagerInterface;
import com.suprada.model.FilmPOJO;

public class FilmManager implements FilmManagerInterface{
	private static FilmHibernateDAOInterface filmHibernateDAO;
	private FilmDAO filmDAO=new FilmDAO();
	
	private FilmManager(){}
	
	public FilmHibernateDAO getInstance()  {
		if(filmHibernateDAO==null) {
			filmHibernateDAO=(FilmHibernateDAO)ApplicationContextSingleton.getApplicationContext().getBean("filmHibernateDAO");
		}
		return (FilmHibernateDAO) filmHibernateDAO;
	}
	
	@Override
	public void insert(FilmPOJO insertfilm) {
		getInstance().insert(insertfilm);
	}
	
	@Override
	public void update(FilmPOJO updatefilm) {
		getInstance().update(updatefilm);
	}
	
	@Override
	public void delete(int id) {
		getInstance().delete(id);
	}
	
	@Override
	public String fetch(String dataQuerry,int page, int total,String countQuerry) {
		return filmDAO.selectAllList2(page, total);
	}
	
	@Override
	public String getData(String dataQuerry,int page, int total,String countQuerry) {
		return getInstance().fetch(dataQuerry, page, total, countQuerry);
	}
}
