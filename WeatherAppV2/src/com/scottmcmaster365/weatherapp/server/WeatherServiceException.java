package com.scottmcmaster365.weatherapp.server;

public class WeatherServiceException extends RuntimeException {

	private static final long serialVersionUID = -8516603341807856479L;

	public WeatherServiceException() {
		super();
	}

	public WeatherServiceException(String message) {
		super(message);
	}

	public WeatherServiceException(Throwable cause) {
		super(cause);
	}

	public WeatherServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
