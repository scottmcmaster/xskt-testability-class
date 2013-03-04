package com.scottmcmaster365.weatherapp.webdriver;

import org.openqa.selenium.By;

import com.scottmcmaster365.weatherapp.client.DebugIds;

public class WeatherHomePageObject {

	public static interface WeatherHomeLocators {
		
		public static final String GWT_DEBUG = "gwt-debug-";

		By countryName = By.id(GWT_DEBUG + DebugIds.COUNTRY_NAME_ID);
		By loadCities = By.id(GWT_DEBUG + DebugIds.LOAD_CITIES_ID);
		By noCities = By.id(GWT_DEBUG + DebugIds.NO_CITIES_ID);
		By cityRow = By.id(GWT_DEBUG + DebugIds.CITY_ROW_ID);
		By city = By.id(GWT_DEBUG + DebugIds.CITY_ID);
		By loadWeather = By.id(GWT_DEBUG + DebugIds.LOAD_WEATHER_ID);
		By weatherCity = By.id(GWT_DEBUG + DebugIds.WEATHER_CITY);
		By weatherTemp = By.id(GWT_DEBUG + DebugIds.WEATHER_TEMP);
		By userName = By.id(GWT_DEBUG + DebugIds.USER_NAME);
		By login = By.id(GWT_DEBUG + DebugIds.LOGIN);
		By weather = By.id(GWT_DEBUG + DebugIds.WEATHER);
		By unknownUser = By.id(GWT_DEBUG + DebugIds.UNKNOWN_USER);
	}

	private WebDriverWrapper driverWrapper;
	
	public WeatherHomePageObject(WebDriverWrapper driverWrapper) {
		this.driverWrapper = driverWrapper;
	}
	
	public void loadCitiesForCountry(String countryName) {
		driverWrapper.setText(WeatherHomeLocators.countryName, "Does not exist");
		driverWrapper.click(WeatherHomeLocators.loadCities);
	}
	
	public void assertCitiesNotLoadedDisplay() {
		driverWrapper.assertIsDisplayed(WeatherHomeLocators.noCities);
		driverWrapper.assertIsNotDisplayed(WeatherHomeLocators.cityRow);
	}
}
