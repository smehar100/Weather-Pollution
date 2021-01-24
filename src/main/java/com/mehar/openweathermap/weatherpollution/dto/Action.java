package com.mehar.openweathermap.weatherpollution.dto;

public enum Action {
	
	
	temp("temp"),
	temp_air("temp_air"),
	air("air");
	
	private String value;

	Action(String value){
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
