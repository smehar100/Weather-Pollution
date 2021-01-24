package com.mehar.openweathermap.weatherpollution.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.mehar.openweathermap.weatherpollution.dto.Action;
import com.mehar.openweathermap.weatherpollution.dto.AirQuality;
import com.mehar.openweathermap.weatherpollution.dto.Category;
import com.mehar.openweathermap.weatherpollution.dto.Details;
import com.mehar.openweathermap.weatherpollution.handler.RestCall;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {
	
	@Mock
	private RestCall restCall;
	
	@InjectMocks
	private WeatherServiceImpl weatherServiceImpl;
	
	@Before
	public void setup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field url = WeatherServiceImpl.class.getDeclaredField("baseUrl");
		url.setAccessible(true);
		url.set(weatherServiceImpl, "url-weather-app");	
	}
	
	@Test
	public void details_WhenTempSelected_ReturnTempOfCity() throws JSONException {
		String temp = "{\"coord\":{\"lon\":-74.006,\"lat\":40.7143},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":4.76,\"feels_like\":-0.14,\"temp_min\":3.89,\"temp_max\":6,\"pressure\":1008,\"humidity\":70},\"visibility\":10000,\"wind\":{\"speed\":4.12,\"deg\":260},\"clouds\":{\"all\":1},\"dt\":1610981590,\"sys\":{\"type\":1,\"id\":4610,\"country\":\"US\",\"sunrise\":1610972178,\"sunset\":1611006982},\"timezone\":-18000,\"id\":5128581,\"name\":\"New York\",\"cod\":200}";
		when(restCall.connection(Mockito.anyString())).thenReturn(temp);
		Details details = weatherServiceImpl.details("New York", Action.temp);
		assertNotNull(details.getClimate());
		assertNull(details.getAirQuality());
	}
	
	@Test
	public void details_WhenTempAirSelected_ReturnTempAndAir() throws JSONException {
		String temp = "{\"coord\":{\"lon\":77.2311,\"lat\":28.6128},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":16.11,\"feels_like\":11.35,\"temp_min\":16.11,\"temp_max\":16.11,\"pressure\":987,\"humidity\":7},\"visibility\":10000,\"wind\":{\"speed\":1.69,\"deg\":9},\"clouds\":{\"all\":0},\"dt\":1610990825,\"sys\":{\"type\":3,\"id\":2011697,\"country\":\"IN\",\"sunrise\":1610934259,\"sunset\":1610972291},\"timezone\":19800,\"id\":1261481,\"name\":\"New Delhi\",\"cod\":200}";
		String air = "{\"coord\":{\"lon\":77.2311,\"lat\":28.6128},\"list\":[{\"main\":{\"aqi\":5},\"components\":{\"co\":3471.38,\"no\":48.28,\"no2\":30.85,\"o3\":0,\"so2\":22.41,\"pm2_5\":421.42,\"pm10\":626.34,\"nh3\":16.97},\"dt\":1610989200}]}";
		when(restCall.connection(Mockito.anyString())).thenReturn(temp).thenReturn(air);
		Details details = weatherServiceImpl.details("New Delhi", Action.temp_air);
		assertEquals(Category.SEVERE,details.getAirQuality().getCategory());
	}
	
	
	@Test
	public void airQuality_WhenAirQualityToBeFound_ReturnAirQaulityDetails() throws JSONException {
		String air ="{\"coord\":{\"lon\":-74.006,\"lat\":40.7143},\"list\":[{\"main\":{\"aqi\":1},\"components\":{\"co\":423.91,\"no\":13.08,\"no2\":22.28,\"o3\":20.03,\"so2\":7.63,\"pm2_5\":9.64,\"pm10\":14.3,\"nh3\":1.54},\"dt\":1610982000}]}";
		when(restCall.connection(Mockito.anyString())).thenReturn(air);
		AirQuality airQuality = weatherServiceImpl.airQuality(-74.006f, 40.7143f);
		assertNotNull(airQuality);
		assertEquals(Category.GOOD,airQuality.getCategory());
	}
}
