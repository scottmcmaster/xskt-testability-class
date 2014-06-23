package com.scottmcmaster365.weatherapp.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Weather implements Serializable, IsSerializable {

	private String cityName;
	private String temperature;
	private String wind;
	private String visibility;
	private String pressure;
	private String humidity;
	private String lastCity;
	
	public String getLastCity() {
		return lastCity;
	}
	public void setLastCity(String lastCity) {
		this.lastCity = lastCity;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
}
