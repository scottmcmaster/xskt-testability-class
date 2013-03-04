package com.scottmcmaster365.weatherapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WeatherAppV2 implements EntryPoint {
	public void onModuleLoad() {
		WeatherHome view = new WeatherHome();
		new WeatherHomePresenter((WeatherServiceAsync) GWT.create(WeatherService.class), view);
		RootPanel.get().add(view);
	}
}
