package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.City;

import it.univpm.OpenWeather.exception.*;

import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
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
	 * metodo che ci permette di ritornare i valori di una determinata città
	 * @param city nome della città
	 * @return HashMap<String,String> con i valori desiderati della città 
	 * @throws IllegalArgumentException
	 * @throws UrlException
	 * @throws MalformedURLException
	 * @throws org.json.simple.parser.ParseException
	 * @throws IOException
	 */
	public abstract JSONArray getData (String city)  throws IllegalArgumentException, UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException;
	/**
	 * Metodo che permette di selezionare le statistiche 
	 * @return HashMap<String,String> delle statistiche effettute 
	 * @throws org.json.simple.parser.ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public abstract HashMap <String, String> getStats(String city, String type, String from, String to) 
			throws UrlException, ClassNotFoundException, DataFormatException, ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException;
	
	//public abstract HashMap <String, String> getForecasts (String city, String state);
	 
	
}
