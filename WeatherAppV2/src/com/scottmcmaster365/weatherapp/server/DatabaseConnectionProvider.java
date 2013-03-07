package com.scottmcmaster365.weatherapp.server;

import java.sql.Connection;
import java.sql.SQLException;

import com.google.inject.Provider;

public class DatabaseConnectionProvider implements Provider<Connection>{

	@Override
	public Connection get() {
		DatabaseConnectionManager manager = new DatabaseConnectionManager();
		try {
			return manager.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}
}
