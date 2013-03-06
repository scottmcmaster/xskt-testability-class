package com.scottmcmaster365.weatherapp.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.inject.Singleton;
import com.scottmcmaster365.weatherapp.shared.City;

@Singleton
public class UserDatabaseImpl implements UserDatabase {

	private Properties prop;

	public UserDatabaseImpl() throws Exception {
		this.prop = new Properties();
		InputStream in = getClass().getResourceAsStream("userdatabase.properties");
		try {
			prop.load(in);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
	}
	
	@Override
	public City loadCityForUser(String userName) {		
		String result = prop.getProperty(userName);
		if (result == null) {
			return null;
		}
		String[] pair = result.split(",");
		return new City(pair[0], pair[1]);
	}

}
