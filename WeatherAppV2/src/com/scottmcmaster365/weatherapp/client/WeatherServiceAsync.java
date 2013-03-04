package com.scottmcmaster365.weatherapp.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scottmcmaster365.weatherapp.shared.Weather;

/**
 * The async counterpart of <code>WeatherService</code>.
 */
public interface WeatherServiceAsync {
	void getCitiesForCountry(String input, AsyncCallback<List<String>> callback);
	void getWeather(String countryName, String cityName, AsyncCallback<Weather> callback);
	void getWeatherForUser(String userName, AsyncCallback<Weather> callback);
}
