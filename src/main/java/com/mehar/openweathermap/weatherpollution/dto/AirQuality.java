package com.mehar.openweathermap.weatherpollution.dto;

public class AirQuality {
	
	private float pm2_5;
	private float pm10;
	private Category category;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public float getPm2_5() {
		return pm2_5;
	}
	public void setPm2_5(float pm2_5) {
		this.pm2_5 = pm2_5;
	}
	public float getPm10() {
		return pm10;
	}
	public void setPm10(float pm10) {
		this.pm10 = pm10;
	}
	
	

}
