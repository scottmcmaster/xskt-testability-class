package com.scottmcmaster365.weatherapp.server;

public interface GlobalWeatherDataAccess {

	String getCitiesForCountryXml(String countryName) throws Exception;

	String getWeatherForCityXml(String countryName, String cityName)
			throws Exception;

}