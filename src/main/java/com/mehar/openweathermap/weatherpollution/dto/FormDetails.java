package com.mehar.openweathermap.weatherpollution.dto;

public class FormDetails {
	
	private String cityName;
	private Action action;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	
}
