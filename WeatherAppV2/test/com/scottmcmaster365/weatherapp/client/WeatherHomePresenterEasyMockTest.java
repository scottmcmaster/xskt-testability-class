package com.scottmcmaster365.weatherapp.client;

import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scottmcmaster365.weatherapp.client.WeatherView.WeatherViewEventHandler;
import com.scottmcmaster365.weatherapp.shared.Weather;

public class WeatherHomePresenterEasyMockTest {

	private WeatherServiceAsync weatherService;
	private WeatherView weatherView;
	
	@Before
	public void setUp() {
		weatherService = EasyMock.createMock(WeatherServiceAsync.class);
		weatherView = EasyMock.createMock(WeatherView.class);
	}
	
	@Test
	public void testOnLogin_unknownUser() {
		Capture<AsyncCallback<Weather>> loginCallback = new Capture<AsyncCallback<Weather>>();
		weatherService.getWeatherForUser(
				EasyMock.eq("unknown"),
				EasyMock.capture(loginCallback));
		EasyMock.expectLastCall();
		
		EasyMock.expect(weatherView.getUserName()).andReturn("unknown");
		weatherView.setUnknownUserVisible(true);
		EasyMock.expectLastCall();
		weatherView.setEventHandler(EasyMock.anyObject(WeatherViewEventHandler.class));
		EasyMock.expectLastCall();
		weatherView.setReady(false);
		EasyMock.expectLastCall();
		weatherView.setReady(true);
		EasyMock.expectLastCall();
		
		EasyMock.replay(weatherView, weatherService);
		
		WeatherHomePresenter presenter = new WeatherHomePresenter(weatherService, weatherView);
		presenter.onLogin();
		loginCallback.getValue().onSuccess(null);
		
		EasyMock.verify(weatherView, weatherService);
	}
}
