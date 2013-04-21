package com.scottmcmaster365.weatherapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.scottmcmaster365.weatherapp.shared.City;

public class UserDatabase {

	public static City loadCityForUser(String userName) {
      try {
    	  Class.forName("com.mysql.jdbc.Driver");
    	  Connection connect = DriverManager
				    .getConnection("jdbc:mysql://localhost/weatherusers?"
				        + "user=weatherapp&password=weatherapppwd");
				PreparedStatement preparedStatement = connect
	          .prepareStatement("select city, country from Users where name = ?");
	      preparedStatement.setString(1, userName);
	      ResultSet resultSet = preparedStatement.executeQuery();
	      if (!resultSet.next()) {
	      	return null;
	      }
	      String city = resultSet.getString("city");
	      String country = resultSet.getString("country");
	      return new City(country, city);
      } catch (SQLException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
	}
}
