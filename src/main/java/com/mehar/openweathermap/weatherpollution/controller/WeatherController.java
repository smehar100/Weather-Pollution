package com.mehar.openweathermap.weatherpollution.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mehar.openweathermap.weatherpollution.dto.FormDetails;
import com.mehar.openweathermap.weatherpollution.handler.RestCall;
import com.mehar.openweathermap.weatherpollution.service.WeatherService;


@Controller
@RequestMapping(value="/details")
public class WeatherController {
	
	@Autowired
	RestCall restCall;
	
	@Autowired
	WeatherService weatherService;
	
	@PostMapping(value="/submit")
	public ModelAndView submit(@ModelAttribute("input") FormDetails formDetails) throws JSONException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("cityName", formDetails.getCityName());
		mv.addObject("data", weatherService.details(formDetails.getCityName(),formDetails.getAction()));
		mv.setViewName("display");
		return mv;
	}
}
