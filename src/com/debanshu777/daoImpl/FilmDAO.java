package com.debanshu777.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.debanshu777.model.FilmPOJO;
import com.google.gson.Gson;


public class FilmDAO {
	
	  
	
		protected Connection getConnection() {
			Connection connection = null;
			try {
				//Register JDBC driver
				Class.forName(Constants.getJdbcDriver());
				// Open a connection
				connection = DriverManager.getConnection(Constants.getDbUrl(), Constants.getUser(), Constants.getPass());
			} catch (SQLException e) {
				printSQLException(e);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return connection;
		}
		
		public int film_idGenerator() {
			int result = 0;
			Connection connection = getConnection();
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(Constants.getMaxDoc());
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					result =Integer.parseInt(rs.getString(1));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return result;
		}
		

		public void delete(int id) throws SQLException{
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(Constants.getSoftDeleteBill())) {
				preparedStatement.setInt(1,id);
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			}catch (SQLException e) {
				printSQLException(e);
			}
		}
		
		public void insert(FilmPOJO insertfilm) {

			// using try-with-resources to avoid closing resources (boiler plate code)
			
			
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement statement = connection.prepareStatement(Constants.getInsertFilm())) {
					statement.setInt(1,film_idGenerator()+1);
					statement.setString(2, insertfilm.getTitle());
					statement.setString(3, insertfilm.getDescription());
					statement.setInt(4,Integer.parseInt(insertfilm.getRelease_year()));               
					statement.setString(5, insertfilm.getRating());
					statement.setInt(6, insertfilm.getLanguage_id());
					statement.setString(7,insertfilm.getSecial_features());
					statement.setString(8,insertfilm.getDirector_name());
					statement.setInt(9,Constants.getIsDisabled());// 1-> to make the disabled visible
					System.out.println(statement);
					statement.executeUpdate();
					} catch (SQLException e) {
						printSQLException(e);
					}
			}
	
		public void update(FilmPOJO updatefilm) {

			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement statement = connection.prepareStatement(Constants.getUpdateFilm())) {
				statement.setString(1, updatefilm.getTitle());
				statement.setString(2, updatefilm.getDescription());
				statement.setString(3,updatefilm.getRelease_year());   
				statement.setInt(4,updatefilm.getLanguage_id());
				statement.setString(5, updatefilm.getRating());
				statement.setString(6,updatefilm.getSecial_features());
				statement.setString(7,updatefilm.getDirector_name());
				statement.setInt(8, updatefilm.getFilm_id());
				System.out.println(statement);
				statement.executeUpdate();
					statement.executeUpdate();
					} catch (SQLException e) {
						printSQLException(e);
					}
			
		}
		public int count(String x) {
			int result = 0;
			Connection connection = getConnection();
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(x);
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
					result =Integer.parseInt(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		public String selectAllList2(int page,int total) {

			// using try-with-resources to avoid closing resources (boiler plate code)
			int j=0;
			ArrayList<FilmPOJO> films = new ArrayList<FilmPOJO>();
			
			j=count(Constants.getCountDoc());
			
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(Constants.getSelectAllFilms());) {
				preparedStatement.setInt(1, page);
				preparedStatement.setInt(2, total);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				
				// Step 4: Process the ResultSet object.
				
				while (rs.next()) {
					FilmPOJO film=new FilmPOJO();
					film.setFilm_id(rs.getShort("film_id"));
					film.setDescription(rs.getString("description"));
					film.setDirector_name(rs.getString("director"));
					film.setRelease_year(rs.getString("release_year"));
					film.setRating(rs.getString("rating"));
					film.setLanguage(rs.getString("language"));
					film.setSecial_features(rs.getString("special_features"));
					film.setTitle(rs.getString("title"));
					film.setIs_disabled(rs.getInt("is_disabled"));
				
					films.add(film);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			
			String json = new Gson().toJson(films);
			String a="\"films\" :"+json;
			String b="{\"total\":"+j+","+a+"}";
			return b;
		}
		
		public String fecthAndSearch(String search_SQL,int page,int total,String Querry) {
			 
			// using try-with-resources to avoid closing resources (boiler plate code)
			ArrayList<FilmPOJO> films = new ArrayList<>();
			// Step 1: Establishing a Connection
			int j=count(Querry);
			try (Connection connection = getConnection();
		 
					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(search_SQL)) {
				preparedStatement.setInt(1, page);
				preparedStatement.setInt(2, total);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
		 
				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					FilmPOJO film=new FilmPOJO();
					film.setFilm_id(rs.getShort("film_id"));
					film.setDescription(rs.getString("description"));
					film.setDirector_name(rs.getString("director"));
					film.setRelease_year(rs.getString("release_year").substring(0,4));
					film.setRating(rs.getString("rating"));
					film.setSecial_features(rs.getString("special_features"));
					film.setTitle(rs.getString("title"));
					film.setLanguage( rs.getString("language"));
					films.add(film);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			String json = new Gson().toJson(films);
			String a="\"films\" :"+json;
			String b="{\"total\":"+j+","+a+"}";
			return b;
		}
		
		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}


}
