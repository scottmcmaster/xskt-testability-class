package com.scottmcmaster365.weatherapp.client;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.beust.jcommander.internal.Lists;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scottmcmaster365.weatherapp.shared.Weather;

public class MockWeatherServiceAsync implements WeatherServiceAsync {

	private List<String> expectGetWeatherForUserCalls = Lists.newArrayList();
	private List<String> observeGetWeatherForUserCalls = Lists.newArrayList();
	
	@Override
	public void getCitiesForCountry(String input,
			AsyncCallback<List<String>> callback) {
		// TODO not yet supported
	}

	@Override
	public void getWeather(String countryName, String cityName,
			AsyncCallback<Weather> callback) {
		// TODO not yet supported
	}

	@Override
	public void getWeatherForUser(String userName, AsyncCallback<Weather> callback) {
		observeGetWeatherForUserCalls.add(userName);
		if ("scott".equals(userName)) {
			callback.onSuccess(new Weather());
		} else {
			callback.onSuccess(null);
		}
	}
	
	public void expectGetWeatherForUser(String userName) {
		expectGetWeatherForUserCalls.add(userName);
	}
	
	public void verify() {
		assertEquals(expectGetWeatherForUserCalls, observeGetWeatherForUserCalls);
		expectGetWeatherForUserCalls.clear();
		observeGetWeatherForUserCalls.clear();
	}
}
