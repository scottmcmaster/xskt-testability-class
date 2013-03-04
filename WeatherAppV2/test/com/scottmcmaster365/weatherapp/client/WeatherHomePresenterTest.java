package com.scottmcmaster365.weatherapp.client;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.scottmcmaster365.weatherapp.client.WeatherView.WeatherViewEventHandler;

public class WeatherHomePresenterTest {

	private MockWeatherServiceAsync weatherService;
	private WeatherView weatherView;
	
	@Before
	public void setUp() {
		weatherService = new MockWeatherServiceAsync();
		weatherView = EasyMock.createMock(WeatherView.class);
	}
	
	@Test
	public void testOnLogin_unknownUser() {
		weatherService.expectGetWeatherForUser("unknown");
		EasyMock.expect(weatherView.getUserName()).andReturn("unknown");
		weatherView.setUnknownUserVisible(true);
		EasyMock.expectLastCall();
		weatherView.setEventHandler(EasyMock.anyObject(WeatherViewEventHandler.class));
		EasyMock.expectLastCall();
		weatherView.setReady(false);
		EasyMock.expectLastCall();
		weatherView.setReady(true);
		EasyMock.expectLastCall();
		
		EasyMock.replay(weatherView);
		
		WeatherHomePresenter presenter = new WeatherHomePresenter(weatherService, weatherView);
		presenter.onLogin();
		
		EasyMock.verify(weatherView);
		weatherService.verify();
	}
	
	@Test
	public void testOnLogin_knownUser() {
		weatherService.expectGetWeatherForUser("scott");
		EasyMock.expect(weatherView.getUserName()).andReturn("scott");
		weatherView.setUnknownUserVisible(false);
		EasyMock.expectLastCall();
		weatherView.setEventHandler(EasyMock.anyObject(WeatherViewEventHandler.class));
		EasyMock.expectLastCall();
		
		weatherView.setWeatherCity(EasyMock.anyObject(String.class));
		EasyMock.expectLastCall();
		weatherView.setWeatherTemperature(EasyMock.anyObject(String.class));
		EasyMock.expectLastCall();
		weatherView.setWeatherHumidity(EasyMock.anyObject(String.class));
		EasyMock.expectLastCall();
		weatherView.setWeatherWind(EasyMock.anyObject(String.class));
		EasyMock.expectLastCall();
		weatherView.setWeatherVisibility(EasyMock.anyObject(String.class));
		EasyMock.expectLastCall();
		weatherView.setWeatherPressure(EasyMock.anyObject(String.class));
		EasyMock.expectLastCall();
		weatherView.setWeatherVisible(true);
		EasyMock.expectLastCall();
		weatherView.setReady(false);
		EasyMock.expectLastCall();
		weatherView.setReady(true);
		EasyMock.expectLastCall();

		EasyMock.replay(weatherView);
		
		WeatherHomePresenter presenter = new WeatherHomePresenter(weatherService, weatherView);
		presenter.onLogin();
		
		EasyMock.verify(weatherView);
		weatherService.verify();
	}
}
