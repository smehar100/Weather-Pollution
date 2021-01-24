package com.mehar.openweathermap.weatherpollution.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCall {

	RestTemplate restTemplate = new RestTemplate();
	
	private Logger logger = LoggerFactory.getLogger(RestCall.class); 
	
	
	public  String connection(String url) {
		try {
			return restTemplate.getForObject(url, String.class);
		}catch(Exception exception){
			logger.error("Error Occured while fetching details ",exception);
			throw exception;
		}
	}


}
