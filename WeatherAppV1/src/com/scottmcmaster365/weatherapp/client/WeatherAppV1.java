package com.scottmcmaster365.weatherapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WeatherAppV1 implements EntryPoint {
	public void onModuleLoad() {
		RootPanel.get().add(new WeatherHome());
	}
}
