package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.City;

import it.univpm.OpenWeather.exception.*;

import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

/**
 * Interfaccia 
 * 
 * @author Emanuela Saleggia
 * @author ValeriaTimmer
 *
 */

public interface CityService {
	
	/**
	 * Metodo che crea la HashMap con i metadata
	 * @return HashMap 
	 */
	public abstract HashMap <String, String> getMetadata();
	
	/**
	 * Metodo che permette di selezionare le città filtrate
	 * @return JSONArray delle città filtrate
	 */
	//public abstract JSONArray getCityFiltered(String city, String state) throws UrlException, ClassNotFoundException;
	
	public abstract HashMap <String, String> getData (String city) throws IllegalArgumentException;
	/**
	 * Metodo che permette di selezionare le statistiche 
	 * @return JSONArray delle statistiche effettute 
	 * @throws org.json.simple.parser.ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public abstract HashMap <String, String> getStats(String city, String state, String type, String from, String to) 
			throws UrlException, ClassNotFoundException, DataFormatException, ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException;
	
	//public abstract HashMap <String, String> getForecasts (String city, String state);
	/**
	 * Metodo che permette di selezionare in base a degli intervalli di umidità scelti (in percentuale)
	 * @param param1 Estremo inferiore dell'intervallo di umidità
	 * @param param2 Estremo superiore dell'intervallo di umidità
	 * @return JSONArray delle città filtrato
	 */
	//public abstract JSONArray getHumidityFiltered (String param1, String param2) throws UrlException, ClassNotFoundException;
	
	/**
	 * Metodo che permette di selezionare in base a degli intervalli di temperatura scelti (in kelvin)
	 * @param param1 Estremo inferiore dell'intervallo di temperatura
	 * @param param2 Estremo superiore dell'intervallo di temperatura 
	 * @return JSONArray filtrato
	 */
	//public abstract JSONArray getTemperatureFiltered (String param1, String param2) throws UrlException, ClassNotFoundException;
	
	/**
	 * Metodo che permette di selezionare la tipologia di meteo e la città
	 * @param weather Tipo di meteo scelto
	 * @param city Nome della città
	 * @return JSONArray delle città filtrate
	 */
	 //public abstract JSONArray getWeatherFiltered (String weather, String city) throws UrlException, ClassNotFoundException;
	
}
