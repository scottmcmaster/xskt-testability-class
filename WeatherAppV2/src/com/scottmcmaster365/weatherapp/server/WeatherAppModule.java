package com.scottmcmaster365.weatherapp.server;

import java.sql.Connection;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class WeatherAppModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(WeatherServiceImpl.class);
		bind(GlobalWeatherService.class).to(GlobalWeatherServiceImpl.class);
		bind(GlobalWeatherDataAccess.class).to(GlobalWeatherDataAccessImpl.class);
		bind(Connection.class).toProvider(DatabaseConnectionProvider.class).in(Singleton.class);
	}
}
