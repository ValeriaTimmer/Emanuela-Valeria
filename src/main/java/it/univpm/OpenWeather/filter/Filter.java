package it.univpm.OpenWeather.filter;

import org.json.simple.JSONArray;

import it.univpm.OpenWeather.utils.FilterUtils;


/**
 * Superclasse per la gestione dei filtri
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class Filter {
	
	/**
	 * Variabile della classe FilterUtils
	 */
	protected FilterUtils utils;
	/**
	 * Array contenente le citta
	 */
	protected JSONArray arrayCity;
	
	/**
	 * Costruttore di default
	 */
	public Filter(String name) {
		this.utils = new FilterUtils(name);
	}
	
	/**
	 * Costruttore che prende come parametro un JSONArray
	 * @param array JSONArray
	 * @param name nome del file
	 */
	public Filter(JSONArray array, String name) {
		this.arrayCity = array;
		this.utils = new FilterUtils(name);
	}
	
	/**
	 * Metodo Getter dell'array di citta
	 * @return arrayCity Ritorna l'array di citta
	 */
	public JSONArray getCity() {
		return arrayCity;
	}


	
}
