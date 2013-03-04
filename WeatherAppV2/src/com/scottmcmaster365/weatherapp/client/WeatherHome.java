package com.scottmcmaster365.weatherapp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

public class WeatherHome extends Composite implements WeatherView {

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
	@UiField Hidden ready;
	
	private WeatherViewEventHandler handler;
	
	private static WeatherHomeUiBinder uiBinder = GWT
			.create(WeatherHomeUiBinder.class);

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

	@Override
	public void setEventHandler(WeatherViewEventHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public String getCountry() {
		return country.getText();
	}
	
	@Override
	public String getCity() {
		if (city.getSelectedIndex() < 0) {
			return null;
		}
		return city.getItemText(city.getSelectedIndex());
	}
	
	@Override
	public String getUserName() {
		return userName.getText();
	}
	
	@UiHandler("loadWeather")
	void onLoadWeather(ClickEvent e) {
		handler.onLoadWeather();
	}

	@UiHandler("loadCities")
	void onLoadCities(ClickEvent e) {
		handler.onLoadCities();
	}
	
	@UiHandler("login")
	void onLogin(ClickEvent e) {
		handler.onLogin();
	}

	@Override
	public void setWeatherCity(String city) {
		cityName.setText(city);
	}
	
	@Override
	public void setWeatherTemperature(String temp) {
		temperature.setText(temp);
	}
	
	@Override
	public void setWeatherHumidity(String humid) {
		humidity.setText(humid);
	}
	
	@Override
	public void setWeatherWind(String windValue) {
		wind.setText(windValue);
	}
	
	@Override
	public void setWeatherPressure(String press) {
		pressure.setText(press);
	}
	
	@Override
	public void setWeatherVisibility(String vis) {
		visibility.setText(vis);
	}
	
	@Override
	public void setWeatherVisible(boolean visible) {
		UIObject.setVisible(weatherTable, visible);
	}
	
	@Override
	public void setUnknownUserVisible(boolean visible) {
		unknownUser.setVisible(visible);
	}
	
	@Override
	public void setCityRowVisible(boolean visible) {
		UIObject.setVisible(cityRow, visible);
	}
	
	@Override
	public void setNoCitiesVisible(boolean visible) {
		noCities.setVisible(visible);
	}
	
	@Override
	public void addCity(String cityNameValue) {
		city.addItem(cityNameValue);
	}
	
	@Override
	public void clearCityList() {
		city.clear();
	}
	
	@Override
	public void errorAlert(String msg) {
		Window.alert(msg);
	}

	@Override
	public boolean getReady() {
		return "ready".equals(ready.getValue());
	}

	@Override
	public void setReady(boolean isReady) {
		if (isReady) {
			ready.setValue("ready");
		} else {
			ready.setValue("busy");
		}
	}
}
