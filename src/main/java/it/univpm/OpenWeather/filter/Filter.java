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
	 * variabile della classe FilterUtils
	 */
	protected FilterUtils utils;
	/**
	 * Array contenente le citta
	 */
	protected JSONArray arrayCity;
	
	/**
	 * costruttore di default
	 */
	public Filter() {
		this.utils = new FilterUtils();
	}
	
	/**
	 * costruttore che prende come parametro un JSONArray
	 * @param array
	 */
	public Filter(JSONArray array) {
		this.arrayCity = array;
		this.utils = new FilterUtils();
	}
	
	/**
	 * Metodo Getter dell'array di citta
	 * @return arrayCity Ritorna l'array di citta
	 */
	public JSONArray getCity() {
		return arrayCity;
	}


	
}
