package com.scottmcmaster365.weatherapp.server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.scottmcmaster365.weatherapp.shared.Weather;

public class WeatherServiceImplTest {

	private GlobalWeatherService globalWeatherService;
	private WeatherServiceImpl weatherService;
	
	@Before
	public void setUp() throws SQLException, Exception {
		globalWeatherService = EasyMock.createMock(GlobalWeatherService.class);
		weatherService = new WeatherServiceImpl(globalWeatherService,
				new UserDatabase(new InMemoryConnectionManager().getConnection()));
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
	public void testGetCitiesForCountry_exception() throws Exception {
		EasyMock.expect(globalWeatherService.getCitiesForCountry("china"))
			.andThrow(new IOException());
		EasyMock.replay(globalWeatherService);
		try {
			weatherService.getCitiesForCountry("china");
		} catch (WeatherServiceException e) {
			assertTrue(e.getCause() instanceof IOException);
			return;
		}
		fail("Didn't find expected exception");
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
	
	@Test
	public void testGetWeatherForUser_found() throws Exception {
		Weather weather = new Weather();
		weather.setCityName("beijing");
		EasyMock.expect(globalWeatherService.getWeatherForCity("china", "beijing"))
			.andReturn(weather);
		EasyMock.replay(globalWeatherService);
		Weather foundWeather = weatherService.getWeatherForUser("scott");
		assertEquals("beijing", foundWeather.getCityName());
		EasyMock.verify(globalWeatherService);
	}
	
	@Test
	public void testGetWeatherForUser_exception() throws Exception {
		EasyMock.expect(globalWeatherService.getWeatherForCity("china", "beijing"))
			.andThrow(new IOException());
		EasyMock.replay(globalWeatherService);
		try {
			weatherService.getWeatherForUser("scott");
		} catch (WeatherServiceException e) {
			assertTrue(e.getCause() instanceof IOException);
			EasyMock.verify(globalWeatherService);
			return;
		}
		fail("Didn't find expected exception");
	}
	
	@Test
	public void testGetWeatherForUser_notFound() throws Exception {
		EasyMock.replay(globalWeatherService);
		Weather foundWeather = weatherService.getWeatherForUser("nobody");
		assertNull(foundWeather);
		EasyMock.verify(globalWeatherService);
	}
}
