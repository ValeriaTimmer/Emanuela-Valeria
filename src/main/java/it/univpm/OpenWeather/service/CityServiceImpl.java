package it.univpm.OpenWeather.service;
import it.univpm.OpenWeather.model.*;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CityServiceImpl implements CityService {
	
	private static Map <String, City> cityRepo = new HashMap();
	
	private final AtomicLong counter = new AtomicLong();
	
	public void CityServiceImpl() {
		City c = new City();
		c.setCityName(toString());
		c.setStateCode(toString());
		cityRepo.put(c.getCityName() + c.getStateCode(), c);
	}
	
	@Override
	public Collection <City> getCity() {
		return cityRepo.values();
	}
	

}
