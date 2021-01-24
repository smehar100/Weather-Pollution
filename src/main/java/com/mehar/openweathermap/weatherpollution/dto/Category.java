package com.mehar.openweathermap.weatherpollution.dto;

public enum Category {
	
	GOOD("Good"),
	POOR("Poor"),
	MODERATE("Moderate"),
	SEVERE("Severe");
	
	String value;
	
	Category(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
