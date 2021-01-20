package it.univpm.OpenWeather.filter;

import org.json.simple.JSONArray;

import it.univpm.OpenWeather.exception.FilterException;
import it.univpm.OpenWeather.utils.FilterUtils;


/**
 * Interfaccia per la gestione dei filtri
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class Filter {
	
	/**
	 * variabile della classe @it.univpm.OpenWeather.utils.FilterUtils
	 */
	protected FilterUtils utils;
	/**
	 * Array contenente le città
	 */
	protected JSONArray arrayCity;
	
	/**
	 * costruttore di default
	 */
	public Filter() {}
	
	/**
	 * costruttore che prende come parametro un JSONArray
	 * @param array
	 */
	public Filter(JSONArray array) {
		this.arrayCity = array;
		this.utils = new FilterUtils();
	}
	
	/**
	 * Metodo Getter dell'array di città
	 * @return arrayCity Ritorna l'array di città
	 */
	public JSONArray getCity() {
		return arrayCity;
	}


	
}
