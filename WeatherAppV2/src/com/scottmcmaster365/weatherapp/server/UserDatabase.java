package com.scottmcmaster365.weatherapp.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.scottmcmaster365.weatherapp.shared.City;

@Singleton
public class UserDatabase {

	private Connection conn;

	@Inject
	public UserDatabase(Connection conn) throws Exception {
		this.conn = conn;
	}
	
	public City loadCityForUser(String userName) {
		try {
			PreparedStatement select = conn.prepareStatement("SELECT * from Users WHERE name = ?");
			select.setString(1, userName);
			ResultSet result = select.executeQuery();
			City city = null;
			while (result.next()) {
				city = new City(result.getString("country"), result.getString("city"));
				break;
			}
			return city;
		} catch (SQLException e) {
			return null;
		}
	}

}
