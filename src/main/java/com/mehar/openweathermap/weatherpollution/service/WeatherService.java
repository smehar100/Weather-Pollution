package com.mehar.openweathermap.weatherpollution.service;

import org.json.JSONException;

import com.mehar.openweathermap.weatherpollution.dto.Action;
import com.mehar.openweathermap.weatherpollution.dto.AirQuality;
import com.mehar.openweathermap.weatherpollution.dto.Details;

public interface WeatherService {
	
	public Details details(String cityName, Action action) throws JSONException;
	public AirQuality airQuality(Float lon, Float lat) throws JSONException;
}
