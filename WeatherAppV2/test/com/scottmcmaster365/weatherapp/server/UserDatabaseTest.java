package com.scottmcmaster365.weatherapp.server;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.scottmcmaster365.weatherapp.shared.City;

public class UserDatabaseTest {

	private UserDatabase database;
	
	@Before
	public void setUp() throws SQLException, Exception {
		database = new UserDatabase(
				new InMemoryConnectionManager().getConnection());
	}
	
	@Test
	public void testLoadCityForUser_notFound() {
		assertNull(database.loadCityForUser("not a username"));
	}
	
	@Test
	public void testLoadCityForUser_found() {
		City city = database.loadCityForUser("scott");
		assertEquals("china", city.getCountryName());
		assertEquals("beijing", city.getCityName());
	}
}
