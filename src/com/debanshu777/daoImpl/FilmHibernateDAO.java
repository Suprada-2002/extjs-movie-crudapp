package com.debanshu777.daoImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.debanshu777.dao.FilmHibernateDAOInterface;
import com.debanshu777.model.FilmPOJO;
import com.google.gson.Gson;


public class FilmHibernateDAO implements FilmHibernateDAOInterface{

	SessionFactory sessionFactory = FilmHibernateSingleton.getSessionfactory();
	
	@Override
	public String fetch(String dataQuerry,int page,int total,String countQuerry) {
		Session session =sessionFactory.openSession();
		Transaction tx =null;
		ArrayList<FilmPOJO> films = new ArrayList<>();
		try {
			tx= session.beginTransaction();
			int j=count(countQuerry);
			System.out.println(j);
			String sql=dataQuerry+page+","+total+";";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	         List data = query.list();

	         for(Object object : data) {
	            Map row = (Map)object;
	            FilmPOJO film=new FilmPOJO();
				film.setFilm_id((short)row.get("film_id"));
				film.setDescription((String)row.get("description"));
				film.setDirector_name((String)row.get("director"));
				film.setRelease_year((((Date) row.get("release_year")).toString().substring(0,4)));
				film.setRating((String)row.get("rating"));
				film.setSecial_features((String)row.get("special_features"));
				film.setTitle((String)row.get("title"));
				film.setLanguage(Utility.getlanguage((byte)(row.get("language_id"))));
				films.add(film);
	         }
	         tx.commit();
	         String json = new Gson().toJson(films);
			 String a="\"films\" :"+json;
			 String b="{\"total\":"+j+","+a+"}";
			 return b;
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		return null;
	}
	
	@Override
	public void insert(FilmPOJO insertfilm) {
		Session session = sessionFactory.openSession();
		Transaction tx =null;
		try {
			tx= session.beginTransaction();
			session.save(insertfilm);
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	@Override
	public void update(FilmPOJO insertfilm) {
		Session session = sessionFactory.openSession();
		Transaction tx =null;
		try {
			tx= session.beginTransaction();
			session.update(insertfilm);
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	@Override
	public void delete(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx =null;
		try {
			tx= session.beginTransaction();
			String sql = "UPDATE film set is_disabled = 0 WHERE film_id = "+id+";";
	        session.createSQLQuery(sql).executeUpdate();
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	}
	
	public int count(String SQL) {
		Session session = sessionFactory.openSession();
		BigInteger i = null;
		Transaction tx =null;
		try {
			tx= session.beginTransaction();
	        i=(BigInteger) (session.createSQLQuery(SQL).uniqueResult());
			tx.commit();
			System.out.println(i);
			return i.intValue();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		return  i.intValue();
	}
	

}
