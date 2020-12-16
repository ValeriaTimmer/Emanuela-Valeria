package it.univpm.OpenWeather.service;

import java.util.Collection;

import it.univpm.OpenWeather.model.*;

public interface CityService {
	public abstract void createCity ();
	public abstract void updateProduct (City cityName, City stateCode);
	public abstract void deleteProduct (City cityName, City stateCode);
	public abstract Collection <City> getCity();
}
