package com.scottmcmaster365.weatherapp.server;

import java.util.List;

import com.scottmcmaster365.weatherapp.shared.Weather;

public interface GlobalWeatherService {

	List<String> getCitiesForCountry(String countryName) throws Exception;

	Weather getWeatherForCity(String countryName, String cityName)
			throws Exception;

}