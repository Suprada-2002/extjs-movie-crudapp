package com.debanshu777.managerImpl;

import com.debanshu777.dao.FilmHibernateDAOInterface;
import com.debanshu777.daoImpl.ApplicationContextSingleton;
import com.debanshu777.daoImpl.FilmDAO;
import com.debanshu777.daoImpl.FilmHibernateDAO;
import com.debanshu777.manager.FilmManagerInterface;
import com.debanshu777.model.FilmPOJO;

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
