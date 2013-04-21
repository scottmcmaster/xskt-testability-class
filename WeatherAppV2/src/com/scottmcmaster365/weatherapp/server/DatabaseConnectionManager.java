package com.scottmcmaster365.weatherapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

	public Connection getConnection() throws SQLException {
	  try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	  Connection connect = DriverManager
		    .getConnection("jdbc:mysql://localhost/weatherusers?"
		        + "user=weatherapp&password=weatherapppwd");
		return connect;
	}
}
