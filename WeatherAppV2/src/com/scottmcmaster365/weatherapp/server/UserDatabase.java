package com.scottmcmaster365.weatherapp.server;

import com.scottmcmaster365.weatherapp.shared.City;

public interface UserDatabase {
	City loadCityForUser(String userName);
}