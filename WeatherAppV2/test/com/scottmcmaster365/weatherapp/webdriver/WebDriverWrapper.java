package com.scottmcmaster365.weatherapp.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WebDriverWrapper {

	WebDriver getDriver();

	WebElement findElementOrFail(By by);

	void click(By by);

	void setText(By by, String text);

	void assertIsDisplayed(By by);

	void assertIsNotDisplayed(By by);

}