package com.scottmcmaster365.weatherapp.client;

public interface WeatherView {

	public interface WeatherViewEventHandler {
		void onLoadWeather();
		void onLoadCities();
		void onLogin();
	}
	
	void setEventHandler(WeatherViewEventHandler handler);

	String getCountry();

	String getCity();

	String getUserName();

	void setWeatherCity(String city);

	void setWeatherTemperature(String temp);

	void setWeatherHumidity(String humid);

	void setWeatherWind(String windValue);

	void setWeatherPressure(String press);

	void setWeatherVisibility(String vis);

	void setWeatherVisible(boolean visible);

	void setUnknownUserVisible(boolean visible);

	void setCityRowVisible(boolean visible);

	void setNoCitiesVisible(boolean visible);

	void addCity(String cityNameValue);

	void clearCityList();

	void errorAlert(String msg);
	
	boolean getReady();
	
	void setReady(boolean ready);
}