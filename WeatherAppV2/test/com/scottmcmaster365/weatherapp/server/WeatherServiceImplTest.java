package com.scottmcmaster365.weatherapp.server;

import static org.junit.Assert.*;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class WeatherServiceImplTest {

	private GlobalWeatherService globalWeatherService;
	private WeatherServiceImpl weatherService;
	
	@Before
	public void setUp() {
		globalWeatherService = EasyMock.createMock(GlobalWeatherService.class);
		weatherService = new WeatherServiceImpl(globalWeatherService);
	}
	
	@Test
	public void testGetCitiesForCountry_nonEmpty() throws Exception {
		EasyMock.expect(globalWeatherService.getCitiesForCountry("china"))
			.andReturn(ImmutableList.of("beijing", "shanghai"));
		EasyMock.replay(globalWeatherService);
		List<String> cities = weatherService.getCitiesForCountry("china");
		assertEquals(2, cities.size());
		assertTrue(cities.contains("beijing"));
		assertTrue(cities.contains("shanghai"));
		EasyMock.verify(globalWeatherService);
	}

	@Test
	public void testGetCitiesForCountry_null() {
		EasyMock.replay(globalWeatherService);
		assertTrue(weatherService.getCitiesForCountry(null).isEmpty());
		EasyMock.verify(globalWeatherService);
	}
	
	@Test
	public void testGetCitiesForCountry_empty() {
		EasyMock.replay(globalWeatherService);
		assertTrue(weatherService.getCitiesForCountry("").isEmpty());
		EasyMock.verify(globalWeatherService);
	}
}
