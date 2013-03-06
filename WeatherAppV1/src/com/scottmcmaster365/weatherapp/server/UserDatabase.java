package com.scottmcmaster365.weatherapp.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.scottmcmaster365.weatherapp.shared.City;

public class UserDatabase {

	public static City loadCityForUser(String userName) {
		Properties prop = new Properties();
		InputStream in = UserDatabase.class.getResourceAsStream("userdatabase.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			return null;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		
		String result = prop.getProperty(userName);
		if (result == null) {
			return null;
		}
		String[] pair = result.split(",");
		return new City(pair[0], pair[1]);
	}

}
