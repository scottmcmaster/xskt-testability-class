package com.scottmcmaster365.weatherapp.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.scottmcmaster365.weatherapp.shared.Weather;

public class WeatherHome extends Composite {

	@UiField Button login;
	@UiField TextBox userName;
	@UiField TextBox country;
	@UiField ListBox city;
	@UiField Button loadWeather;
	@UiField TableRowElement cityRow;
	@UiField Button loadCities;
	@UiField TableElement weatherTable;
	@UiField Label cityName;
	@UiField Label temperature;
	@UiField Label wind;
	@UiField Label pressure;
	@UiField Label visibility;
	@UiField Label humidity;
	@UiField Label noCities;
	@UiField Label unknownUser;
	
	private static WeatherHomeUiBinder uiBinder = GWT
			.create(WeatherHomeUiBinder.class);

	private final WeatherServiceAsync weatherService = GWT
			.create(WeatherService.class);

	interface WeatherHomeUiBinder extends UiBinder<Widget, WeatherHome> {
	}

	public WeatherHome() {
		initWidget(uiBinder.createAndBindUi(this));
		
		city.ensureDebugId(DebugIds.CITY_NAME_ID);
		country.ensureDebugId(DebugIds.COUNTRY_NAME_ID);
		noCities.ensureDebugId(DebugIds.NO_CITIES_ID);
		UIObject.ensureDebugId(cityRow, DebugIds.CITY_ROW_ID);
		loadCities.ensureDebugId(DebugIds.LOAD_CITIES_ID);
		city.ensureDebugId(DebugIds.CITY_ID);
		loadWeather.ensureDebugId(DebugIds.LOAD_WEATHER_ID);
		UIObject.ensureDebugId(weatherTable, DebugIds.WEATHER);
		cityName.ensureDebugId(DebugIds.WEATHER_CITY);
		temperature.ensureDebugId(DebugIds.WEATHER_TEMP);
		login.ensureDebugId(DebugIds.LOGIN);
		userName.ensureDebugId(DebugIds.USER_NAME);
		unknownUser.ensureDebugId(DebugIds.UNKNOWN_USER);
		
		UIObject.setVisible(cityRow, false);
		UIObject.setVisible(weatherTable, false);
	}

	@UiHandler("loadWeather")
	void onLoadWeather(ClickEvent e) {
		weatherService.getWeather(country.getText(), city.getItemText(city.getSelectedIndex()),
				new AsyncCallback<Weather>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("oops");						
					}

					@Override
					public void onSuccess(Weather weather) {
						fillWeather(weather);
					}
				});
	}

	private void fillWeather(Weather weather) {
		cityName.setText(weather.getCityName());
		temperature.setText(weather.getTemperature());
		humidity.setText(weather.getHumidity());
		wind.setText(weather.getWind());
		visibility.setText(weather.getVisibility());
		pressure.setText(weather.getPressure());
		UIObject.setVisible(weatherTable, true);
	}

	@UiHandler("loadCities")
	void onLoadCities(ClickEvent e) {
		UIObject.setVisible(weatherTable, false);
		weatherService.getCitiesForCountry(country.getText(),
				new AsyncCallback<List<String>>() {
					
					@Override
					public void onSuccess(List<String> cityNames) {
						city.clear();
						if (cityNames.isEmpty()) {
							noCities.setVisible(true);
							UIObject.setVisible(cityRow, false);
						} else {
							for (String cityName : cityNames) {
								noCities.setVisible(false);
								city.addItem(cityName);
							}
							UIObject.setVisible(cityRow, true);
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("oops");
					}
				});
	}
	
	@UiHandler("login")
	void onLogin(ClickEvent e) {
		weatherService.getWeatherForUser(userName.getText(),
				new AsyncCallback<Weather>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("oops");						
					}

					@Override
					public void onSuccess(Weather weather) {
						if (weather != null) {
							fillWeather(weather);
							unknownUser.setVisible(false);
						} else {
							unknownUser.setVisible(true);
						}
					}
				});		
	}
}
