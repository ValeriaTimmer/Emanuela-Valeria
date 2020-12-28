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
	
	public abstract void createCity(DownloadCity city);
	
	/**
	 * Metodo che permette di selezionare le città filtrate
	 * @return insieme delle città filtrate
	 */
	public abstract Collection <City> getCityFiltered(String city, String state);
	
	/**
	 * Metodo che permette di selezionare le statistiche filtrate
	 * @return insieme delle statistiche effettute
	 */
	public abstract Collection <City> getStatisticsFiltered(String hum, String period);
	
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
	
}
