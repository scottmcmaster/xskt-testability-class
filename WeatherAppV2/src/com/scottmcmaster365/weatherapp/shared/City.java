package com.scottmcmaster365.weatherapp.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class City implements Serializable, IsSerializable {

	private String cityName;
	private String countryName;
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public City(String countryName, String cityName) {
		super();
		this.cityName = cityName;
		this.countryName = countryName;
	}
}
