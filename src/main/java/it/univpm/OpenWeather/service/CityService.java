package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.City;

import java.util.Collection;

/**
 * Interfaccia 
 * 
 * @author Emanuela Saleggia
 * @author ValeriaTimmer
 *
 */
public interface CityService {
	
	/**
	 * Metodo che permette di selezionare le città filtrate
	 * @return insieme delle città filtrate
	 */
	public abstract Collection <City> getCityFiltered(String city, String state);
	
	/**
	 * Metodo che permette di selezionare le statistiche dell'umidità
	 * @return insieme delle statistiche effettute sull'umidità
	 */
	public abstract Collection <City> getStatisticsHumidity(String hum, String period);
	
	/**
	 * Metodo che permette di selezionare le statistiche della temperatura
	 * @return insieme delle statistiche effettute sulla temperatura
	 */
	public abstract Collection <City> getStatisticsTemperature (String temp, String period);
	
	/**
	 * Metodo che permette di selezionare le città in base all'umidità
	 * @param param1 Estremo inferiore dell'intervallo di umidità
	 * @param param2 Estremo superiore dell'intervallo di umidità
	 * @return insieme delle città filtrate 
	 */
	public abstract Collection <City> getHumidityFiltered (String param1, String param2);
	
	/**
	 * Metodo che permette di selezionare le città in base alla temperatura
	 * @param param1 Estremo inferiore dell'intervallo di temperatura
	 * @param param2 Estremo superiore dell'intervallo di temperatura 
	 * @return insieme delle città filtrate 
	 */
	public abstract Collection <City> getTemperatureFiltered (String param1, String param2);
	
	/**
	 * Metodo che permette di selezionare la tipologia di meteo e la città
	 * @param weather Tipo di meteo scelto
	 * @param city Nome della città
	 * @return insieme delle città filtrate
	 */
	 public abstract Collection <City> getWeatherFiltered (String weather, String city);
	
}
