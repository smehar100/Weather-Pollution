package com.mehar.openweathermap.weatherpollution.service.impl;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.mehar.openweathermap.weatherpollution.dto.Action;
import com.mehar.openweathermap.weatherpollution.dto.AirQuality;
import com.mehar.openweathermap.weatherpollution.dto.Category;
import com.mehar.openweathermap.weatherpollution.dto.Climate;
import com.mehar.openweathermap.weatherpollution.dto.Coordinate;
import com.mehar.openweathermap.weatherpollution.dto.Details;
import com.mehar.openweathermap.weatherpollution.handler.RestCall;
import com.mehar.openweathermap.weatherpollution.service.WeatherService;


@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	RestCall restCall;
	
	@Value("${weather.url}")
	private String baseUrl ;
	
	@Value("${api.key}")
	private String apiKey ;
	
	@Override
	public Details details(String cityName, Action action) throws JSONException {
		String url = baseUrl + "/weather";
		Details details = new Details();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
		        .queryParam("appid", apiKey)
		        .queryParam("units", "metric")
		        .queryParam("q", cityName);
		
		String data = restCall.connection(builder.toUriString().replaceAll("%20", " ")); 
		JSONObject json = new JSONObject(data);
		Gson gson = new Gson();
		details.setClimate(gson.fromJson(json.get("main").toString(), Climate.class));
		if(action.equals(Action.temp_air)) {
			Coordinate coordinate = gson.fromJson(json.get("coord").toString(), Coordinate.class);
			details.setAirQuality(airQuality(coordinate.getLon(), coordinate.getLat()));
		}
		return details;
	}

	@Override
	public AirQuality airQuality(Float lon, Float lat) throws JSONException  {
		
		String url = baseUrl + "/air_pollution";
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
		        .queryParam("appid", apiKey)
		        .queryParam("lat", lat)
		        .queryParam("lon", lon);
		
		String data = restCall.connection(builder.toUriString()); 
		JSONObject json = new JSONObject(data);

		JSONArray ob = (JSONArray) json.get("list");
		json = (JSONObject) ob.get(0);
		AirQuality air =new Gson().fromJson(json.get("components").toString(), AirQuality.class);
		calculateCategory(air);
		return air;
	}
	
	private void calculateCategory(AirQuality air) {
		if(air.getPm2_5() <= 60 && air.getPm10() <= 100) {
			air.setCategory(Category.GOOD);
		}else if(air.getPm2_5() >= 91 && air.getPm2_5() <=250  && air.getPm10() <= 430 && air.getPm10() >= 251) {
			air.setCategory(Category.POOR);
		}else if(air.getPm2_5() > 250 && air.getPm10() > 430) {
			air.setCategory(Category.SEVERE);
		}else {
			air.setCategory(Category.MODERATE);
		}
	}

}
