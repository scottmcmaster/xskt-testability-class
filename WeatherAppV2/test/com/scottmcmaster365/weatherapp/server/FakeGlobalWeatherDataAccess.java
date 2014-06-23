package com.scottmcmaster365.weatherapp.server;

public class FakeGlobalWeatherDataAccess implements GlobalWeatherDataAccess {

	// Try http://www.htmlescape.net/javaescape_tool.html to generate these.
	private static final String CHINA_CITIES = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<string xmlns=\"http://www.webserviceX.NET\"><NewDataSet>\n  <Table>\n    <Country>China</Country>\n    <City>Beijing</City>\n  </Table>\n  <Table>\n    <Country>China</Country>\n    <City>Shanghai</City>\n  </Table>\n</NewDataSet></string>";
	private static final String BEIJING_WEATHER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<string xmlns=\"http://www.webserviceX.NET\">&lt;?xml version=\"1.0\" encoding=\"utf-16\"?&gt;\n&lt;CurrentWeather&gt;\n  &lt;Location&gt;Beijing, China (ZBAA) 39-56N 116-17E 55M&lt;/Location&gt;\n  &lt;Time&gt;Oct 27, 2012 - 04:00 PM EDT / 2012.10.27 2000 UTC&lt;/Time&gt;\n  &lt;Wind&gt; from the N (010 degrees) at 9 MPH (8 KT):0&lt;/Wind&gt;\n  &lt;Visibility&gt; greater than 7 mile(s):0&lt;/Visibility&gt;\n  &lt;Temperature&gt; 39 F (4 C)&lt;/Temperature&gt;\n  &lt;DewPoint&gt; 28 F (-2 C)&lt;/DewPoint&gt;\n  &lt;RelativeHumidity&gt; 64%&lt;/RelativeHumidity&gt;\n  &lt;Pressure&gt; 30.30 in. Hg (1026 hPa)&lt;/Pressure&gt;\n  &lt;Status&gt;Success&lt;/Status&gt;\n&lt;/CurrentWeather&gt;</string>";
	private static final String NO_CITIES = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<string xmlns=\"http://www.webserviceX.NET\"><NewDataSet /></string>";
	
	@Override
	public String getCitiesForCountryXml(String countryName) throws Exception {
		//Thread.sleep(5000);
		if ("china".equals(countryName.toLowerCase())) {
			return CHINA_CITIES;
		}
		return NO_CITIES;
	}

	@Override
	public String getWeatherForCityXml(String countryName, String cityName)
			throws Exception {
		return BEIJING_WEATHER;
	}
}
