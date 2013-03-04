package com.scottmcmaster365.weatherapp.server;

import java.util.ArrayList;
import java.util.List;

import com.scottmcmaster365.weatherapp.client.WeatherService;
import com.scottmcmaster365.weatherapp.shared.Weather;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Singleton
public class WeatherServiceImpl extends RemoteServiceServlet implements
		WeatherService {

	private final GlobalWeatherService service;
	
	@Inject
	public WeatherServiceImpl(GlobalWeatherService service) {
		this.service = service;
	}
	
	public List<String> getCitiesForCountry(String countryName) {
		try {
			if (countryName == null || countryName.isEmpty()) {
				return new ArrayList<String>();
			}
			return service.getCitiesForCountry(countryName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Weather getWeather(String countryName, String cityName) {
		try {
			return service.getWeatherForCity(countryName, cityName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Weather getWeatherForUser(String userName) {
		// TODO: Replace with a real user database.
		if ("scott".equals(userName.toLowerCase())) {
			return getWeather("china", "beijing");
		} else if ("marissa".equals(userName.toLowerCase())) {
			return getWeather("united states", "seattle");
		}
		return null;
	}
}
