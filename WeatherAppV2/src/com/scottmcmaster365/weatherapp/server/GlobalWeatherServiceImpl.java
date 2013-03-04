package com.scottmcmaster365.weatherapp.server;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.tools.ant.filters.StringInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.inject.Inject;
import com.scottmcmaster365.weatherapp.shared.Weather;

public class GlobalWeatherServiceImpl implements GlobalWeatherService {
	
	private final GlobalWeatherDataAccess dataAccess;
	
	@Inject
	public GlobalWeatherServiceImpl(GlobalWeatherDataAccess dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	@Override
	public List<String> getCitiesForCountry(String countryName) throws Exception {
		List<String> cities = new ArrayList<String>();
		String result = dataAccess.getCitiesForCountryXml(countryName);
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(false);
    DocumentBuilder builder = domFactory.newDocumentBuilder();
    Document dDoc = builder.parse(new StringInputStream(result));

    XPath xPath = XPathFactory.newInstance().newXPath();
    NodeList cityNodes = (NodeList) xPath.evaluate("//City", dDoc, XPathConstants.NODESET);

		for (int i = 0; i < cityNodes.getLength(); i++) {
			Node cityNode = cityNodes.item(i);
			cities.add(cityNode.getTextContent());
		}
		return cities;
	}
	

	@Override
	public Weather getWeatherForCity(String countryName, String cityName) throws Exception {
		Weather weather = new Weather();
		String result = dataAccess.getWeatherForCityXml(countryName, cityName);
		
		// This response is weird -- it has encoded XML embedded inside an XML wrapper.
		
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(false);
    DocumentBuilder builder = domFactory.newDocumentBuilder();
    Document dDoc = builder.parse(new StringInputStream(result));
		String realResponse = dDoc.getFirstChild().getTextContent();
		
    builder = domFactory.newDocumentBuilder();
    dDoc = builder.parse(new InputSource(new StringReader(realResponse)));

    XPath xPath = XPathFactory.newInstance().newXPath();
    NodeList locationNodes = (NodeList) xPath.evaluate("//Location", dDoc, XPathConstants.NODESET);
    weather.setCityName(locationNodes.item(0).getTextContent());
    
    NodeList tempNodes = (NodeList) xPath.evaluate("//Temperature", dDoc, XPathConstants.NODESET);
    weather.setTemperature(tempNodes.item(0).getTextContent());
    
    NodeList visNodes = (NodeList) xPath.evaluate("//Visibility", dDoc, XPathConstants.NODESET);
    weather.setVisibility(visNodes.item(0).getTextContent());
    
    NodeList windNodes = (NodeList) xPath.evaluate("//Wind", dDoc, XPathConstants.NODESET);
    weather.setWind(windNodes.item(0).getTextContent());
    
    NodeList pressureNodes = (NodeList) xPath.evaluate("//Pressure", dDoc, XPathConstants.NODESET);
    weather.setPressure(pressureNodes.item(0).getTextContent());
    
    NodeList humidNodes = (NodeList) xPath.evaluate("//RelativeHumidity", dDoc, XPathConstants.NODESET);
    weather.setHumidity(humidNodes.item(0).getTextContent());
    
    return weather;
	}
}
