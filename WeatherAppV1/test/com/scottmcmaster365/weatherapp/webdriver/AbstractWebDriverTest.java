package com.scottmcmaster365.weatherapp.webdriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class AbstractWebDriverTest implements WebDriverWrapper {
	
	protected WebDriver driver;
	
	@Before
	public void setUp() throws IOException {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(firefoxProfile);
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
	
	@Override
	public WebDriver getDriver() {
		return driver;
	}
	
	@Override
	public WebElement findElementOrFail(By by) {
		WebElement element = driver.findElement(by);
		if (element == null) {
			org.junit.Assert.fail("Element " + by + " not found");
		}
		return element;
	}
	
	@Override
	public void click(By by) {
		WebElement element = findElementOrFail(by);
		element.click();
	}
	
	@Override
	public void setText(By by, String text) {
		WebElement element = findElementOrFail(by);
		element.clear();
		element.sendKeys(text);
	}
	
	@Override
	public void assertIsDisplayed(By by) {
		WebElement element = findElementOrFail(by);
		assertTrue(element.isDisplayed());
	}
	
	@Override
	public void assertIsNotDisplayed(By by) {
		WebElement element = findElementOrFail(by);
		assertFalse(element.isDisplayed());
	}
}
