package com.mehar.openweathermap.weatherpollution.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mehar.openweathermap.weatherpollution.controller.WeatherController;
import com.mehar.openweathermap.weatherpollution.dto.AirQuality;
import com.mehar.openweathermap.weatherpollution.dto.Category;
import com.mehar.openweathermap.weatherpollution.dto.Climate;
import com.mehar.openweathermap.weatherpollution.dto.Details;
import com.mehar.openweathermap.weatherpollution.service.WeatherService;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

	@InjectMocks
	private WeatherController weatherController;

	@Mock
	private WeatherService weatherService;

	@Test
	public void submit_whenCalledShouldReturnViewWithDetails() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
		when(weatherService.details(Mockito.any(),Mockito.any())).thenReturn(formDetails());
		mockMvc.perform(post("/details/submit"))
		.andExpect(view().name("display"))
		.andExpect(status().isOk());

	}

	private Details formDetails() {
		Details details =  new Details();
		details.setClimate(formClimateDetails());
		details.setAirQuality(formAirQaulityDetails());
		return details;
	}

	private Climate formClimateDetails() {
		Climate climate = new Climate();
		climate.setHumidity(28);
		climate.setMaxTemp(18.99f);
		climate.setMinTemp(18.99f);
		climate.setPressure(1016);
		climate.setTemp(14.68f);
		return climate;
	}

	private AirQuality formAirQaulityDetails() {
		AirQuality airQuality =  new AirQuality();
		airQuality.setCategory(Category.GOOD);
		airQuality.setPm10(15.23f);
		airQuality.setPm2_5(10f);
		return airQuality;
		

	}



}
