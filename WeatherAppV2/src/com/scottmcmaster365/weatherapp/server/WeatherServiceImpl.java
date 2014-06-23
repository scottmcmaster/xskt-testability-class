package com.scottmcmaster365.weatherapp.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.scottmcmaster365.weatherapp.client.WeatherService;
import com.scottmcmaster365.weatherapp.shared.City;
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
	private final UserDatabase userDatabase;
	
	@Inject
	public WeatherServiceImpl(GlobalWeatherService service,
			UserDatabase userDatabase) {
		this.service = service;
		this.userDatabase = userDatabase;
	}
	
	public List<String> getCitiesForCountry(String countryName) {
		try {
			if (countryName == null || countryName.isEmpty()) {
				return new ArrayList<String>();
			}
			return service.getCitiesForCountry(countryName);
		} catch (Exception e) {
			throw new WeatherServiceException(e);
		}
	}

	@Override
	public Weather getWeather(String countryName, String cityName) {
		try {
			Weather weather = service.getWeatherForCity(countryName, cityName);
			return weather;
		} catch (Exception e) {
			throw new WeatherServiceException(e);
		}
	}

	@Override
	public Weather getWeatherForUser(String userName) {
		City city = userDatabase.loadCityForUser(userName);
		if (city != null) {
			String lastCity = city.getCountryName() + "," + city.getCityName();
			getThreadLocalResponse().addCookie(new Cookie("last", lastCity));
			return getWeather(city.getCountryName(), city.getCityName());
		}
		return null;
	}
}
