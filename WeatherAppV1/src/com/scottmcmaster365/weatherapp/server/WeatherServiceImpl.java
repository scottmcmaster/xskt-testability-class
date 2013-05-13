package com.scottmcmaster365.weatherapp.server;

import java.util.ArrayList;
import java.util.List;

import com.scottmcmaster365.weatherapp.client.WeatherService;
import com.scottmcmaster365.weatherapp.shared.City;
import com.scottmcmaster365.weatherapp.shared.Weather;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class WeatherServiceImpl extends RemoteServiceServlet implements
		WeatherService {
	
	private static final GlobalWeatherService service = new GlobalWeatherService();

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
			return service.getWeatherForCity(countryName, cityName);
		} catch (Exception e) {
			throw new WeatherServiceException(e);
		}
	}

	@Override
	public Weather getWeatherForUser(String userName) {
		City userCity = UserDatabase.loadCityForUser(userName);
		if (userCity != null) {
			return getWeather(userCity.getCountryName(), userCity.getCityName());
		}
		return null;
	}
}
