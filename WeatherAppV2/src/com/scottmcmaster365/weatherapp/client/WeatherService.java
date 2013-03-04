package com.scottmcmaster365.weatherapp.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.scottmcmaster365.weatherapp.shared.Weather;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("weather")
public interface WeatherService extends RemoteService {
	List<String> getCitiesForCountry(String countryName);
	Weather getWeather(String countryName, String cityName);
	Weather getWeatherForUser(String userName);
}
