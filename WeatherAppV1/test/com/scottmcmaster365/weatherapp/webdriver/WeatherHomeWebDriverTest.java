package com.scottmcmaster365.weatherapp.webdriver;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.scottmcmaster365.weatherapp.client.DebugIds;
import com.scottmcmaster365.weatherapp.webdriver.WeatherHomePageObject.WeatherHomeLocators;


@RunWith(JUnit4.class)
public class WeatherHomeWebDriverTest extends AbstractWebDriverTest {

	private static final String HOME_URL = "http://localhost:8888/WeatherAppV1.html";
		
	private WeatherHomePageObject pageObject;
	
	@Before
	public void setUp() throws IOException {
		super.setUp();
		driver.get(HOME_URL);
		pageObject = new WeatherHomePageObject(this);
	}
	
	@Test
	public void testUnknownCountryName() {
		pageObject.loadCitiesForCountry("Does not exist");
		
		WebDriverWait wait = createDefaultWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(WeatherHomeLocators.noCities));
		
		pageObject.assertCitiesNotLoadedDisplay();
	}
	
	@Test
	public void testKnownCountryName() {
		WebElement countryElement = driver.findElement(WeatherHomeLocators.countryName);
		countryElement.sendKeys("China");
		WebElement loadCitiesButton = driver.findElement(WeatherHomeLocators.loadCities);
		loadCitiesButton.click();
		
		waitForCities();
		
		WebElement noCitiesElement = driver.findElement(WeatherHomeLocators.noCities);
		assertFalse(noCitiesElement.isDisplayed());
		
		WebElement cityElement = driver.findElement(WeatherHomeLocators.city);
		Select citySelect = new Select(cityElement);
		boolean foundBeijing = false;
		boolean foundWuhan = false;
		List<WebElement> cities = citySelect.getOptions();
		for (WebElement city : cities) {
			if (city.getText().equals("Beijing")) {
				foundBeijing = true;
			} else if (city.getText().equals("Wuhan")) {
				foundWuhan = true;
			}
		}
		assertTrue(foundBeijing);
		assertTrue(foundWuhan);
	}

	@Test
	public void testLoadWeather() {
		WebElement countryElement = driver.findElement(WeatherHomeLocators.countryName);
		countryElement.sendKeys("China");
		WebElement loadCitiesButton = driver.findElement(WeatherHomeLocators.loadCities);
		loadCitiesButton.click();
		
		waitForCities();
		
		WebElement noCitiesElement = driver.findElement(WeatherHomeLocators.noCities);
		assertFalse(noCitiesElement.isDisplayed());
		
		WebElement cityElement = driver.findElement(WeatherHomeLocators.city);
		Select citySelect = new Select(cityElement);
		citySelect.selectByVisibleText("Beijing");

		WebElement loadWeatherButton = driver.findElement(WeatherHomeLocators.loadWeather);
		loadWeatherButton.click();
		
		waitForWeather();
		
		WebElement weatherCityElement = driver.findElement(WeatherHomeLocators.weatherCity);
		assertTrue(weatherCityElement.getText().contains("Beijing"));
		
		WebElement weatherTempElement = driver.findElement(WeatherHomeLocators.weatherTemp);
		assertTrue(weatherTempElement.getText().contains("C)"));
	}
	
	@Test
	public void testLoginLoadWeatherForKnownUser() {
		WebElement userNameElement = driver.findElement(WeatherHomeLocators.userName);
		userNameElement.sendKeys("scott");
		WebElement loginButton = driver.findElement(WeatherHomeLocators.login);
		loginButton.click();

		waitForWeather();

		WebElement weatherCityElement = driver.findElement(WeatherHomeLocators.weatherCity);
		assertTrue(weatherCityElement.getText().contains("Beijing"));
	}

	@Test
	public void testLoginLoadWeatherForUnkownUser() {
		WebElement userNameElement = driver.findElement(WeatherHomeLocators.userName);
		userNameElement.sendKeys("unknown");
		WebElement loginButton = driver.findElement(WeatherHomeLocators.login);
		loginButton.click();

		WebDriverWait wait = createDefaultWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(WeatherHomeLocators.unknownUser));
	}

	private WebDriverWait createDefaultWait() {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		return wait;
	}

	private void waitForCities() {
		WebDriverWait wait = createDefaultWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(WeatherHomeLocators.cityRow));
	}
	
	private void waitForWeather() {
		WebDriverWait wait = createDefaultWait();
		wait.until(ExpectedConditions.visibilityOfElementLocated(WeatherHomeLocators.weather));
	}	
}
