package com.scottmcmaster365.weatherapp.client;

import java.util.List;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scottmcmaster365.weatherapp.client.WeatherView.WeatherViewEventHandler;
import com.scottmcmaster365.weatherapp.shared.Weather;

/**
 * This class currently assumes one outstanding RPC at a time,
 * noting the use of view.setReady().
 */
public class WeatherHomePresenter implements WeatherViewEventHandler{

	private final WeatherServiceAsync weatherService;
	private final WeatherView view;
	
	public WeatherHomePresenter(WeatherServiceAsync weatherService, WeatherView view) {
		this.weatherService = weatherService;
		this.view = view;
		view.setEventHandler(this);
		if (Cookies.getCookie("user") != null) {
			this.view.setKnownUserVisible(true);
			String userName = Cookies.getCookie("user");
			this.view.setKnownUser(userName);
			doUserWeather(userName);
		}
	}

	@Override
	public void onLoadWeather() {
		view.setReady(false);
		weatherService.getWeather(view.getCountry(), view.getCity(),
				new AsyncCallback<Weather>() {

					@Override
					public void onFailure(Throwable caught) {
						view.errorAlert("oops");
						view.setReady(true);
					}

					@Override
					public void onSuccess(Weather weather) {
						fillWeather(weather);
						view.setReady(true);
					}
				});
	}

	private void fillWeather(Weather weather) {
		view.setWeatherCity(weather.getCityName());
		view.setWeatherTemperature(weather.getTemperature());
		view.setWeatherHumidity(weather.getHumidity());
		view.setWeatherWind(weather.getWind());
		view.setWeatherVisibility(weather.getVisibility());
		view.setWeatherPressure(weather.getPressure());
		view.setWeatherVisible(true);
	}

	@Override
	public void onLoadCities() {
		view.setReady(false);
		view.setWeatherVisible(false);
		weatherService.getCitiesForCountry(view.getCountry(),
				new AsyncCallback<List<String>>() {
					
					@Override
					public void onSuccess(List<String> cityNames) {
						view.clearCityList();
						if (cityNames.isEmpty()) {
							view.setNoCitiesVisible(true);
							view.setCityRowVisible(false);
						} else {
							view.setNoCitiesVisible(false);
							for (String cityName : cityNames) {
								view.addCity(cityName);
							}
							view.setCityRowVisible(true);
						}
						view.setReady(true);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						view.errorAlert("oops");
						view.setReady(true);
					}
				});
	}
	
	@Override
	public void onLogin() {
		view.setReady(false);
		doUserWeather(view.getUserName());		
	}

	@Override
	public void onLogout() {
		Cookies.removeCookie("user");
		view.setKnownUserVisible(false);
	}

	private void doUserWeather(final String userName) {
		weatherService.getWeatherForUser(userName,
				new AsyncCallback<Weather>() {

					@Override
					public void onFailure(Throwable caught) {
						view.errorAlert("oops");
						view.setReady(true);
					}

					@Override
					public void onSuccess(Weather weather) {
						if (weather != null) {
							fillWeather(weather);
							view.setUnknownUserVisible(false);
							view.setKnownUserVisible(true);
							Cookies.setCookie("user", userName);
						} else {
							view.setUnknownUserVisible(true);
							view.setKnownUserVisible(false);
						}
						view.setReady(true);
					}
				});
	}
}
