package com.scottmcmaster365.weatherapp.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {
    	@Override
    	protected void configureServlets() {
    		serve("/weatherappv2/weather").with(WeatherServiceImpl.class);
    	}
    }, new WeatherAppModule());
  }
}