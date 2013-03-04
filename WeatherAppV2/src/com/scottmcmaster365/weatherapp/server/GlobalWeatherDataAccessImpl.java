package com.scottmcmaster365.weatherapp.server;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;

public class GlobalWeatherDataAccessImpl implements GlobalWeatherDataAccess {
	
	private static final String BASE_URL = "http://www.webservicex.net/globalweather.asmx/";

	@Override
	public String getCitiesForCountryXml(String countryName) throws Exception {
		String url = BASE_URL + "GetCitiesByCountry?CountryName=" + URLEncoder.encode(countryName, "utf-8");		
		String result = null;
		InputStream in = new URL(url).openStream();
		try {
			result = IOUtils.toString(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
		return StringEscapeUtils.unescapeXml(result);
	}
	

	@Override
	public String getWeatherForCityXml(String countryName, String cityName) throws Exception {		
		String url = BASE_URL + "GetWeather?CityName=" +
			URLEncoder.encode(cityName, "utf-8") + "&CountryName=" + URLEncoder.encode(countryName, "utf-8");		
		String result = null;
		InputStream in = new URL(url).openStream();
		try {
			result = IOUtils.toString(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
		return result;
	}	
}
