package com.scottmcmaster365.weatherapp.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GlobalWeatherServiceImplTest {

	private GlobalWeatherServiceImpl globalWeatherService;
	private FakeGlobalWeatherDataAccess dataAccess;
	
	@Before
	public void setUp() {
		dataAccess = new FakeGlobalWeatherDataAccess();
		globalWeatherService = new GlobalWeatherServiceImpl(dataAccess);
	}
	
	@Test
	public void testGetCitiesForCountry_nonEmpty() throws Exception {
		List<String> cities = globalWeatherService.getCitiesForCountry("china");
		assertEquals(2, cities.size());
		assertTrue(cities.contains("Beijing"));
		assertTrue(cities.contains("Shanghai"));
	}
	
	@Test
	public void testGetCitiesForCountry_empty() throws Exception {
		List<String> cities = globalWeatherService.getCitiesForCountry("nowhere");
		assertTrue(cities.isEmpty());
	}
}
