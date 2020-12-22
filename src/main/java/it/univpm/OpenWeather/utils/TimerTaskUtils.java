package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.service.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskUtils {
	
	ArrayList <City> downloadCity = new ArrayList <City>(); 
	
	public TimerTaskUtils() {}
	
	public void run () {
		
		City c = new City();
		downloadCity = BuildingCity.Building(c);
	}
}
