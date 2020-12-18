package it.univpm.OpenWeather.filter;

import org.json.simple.JSONArray;

/**
 * Interfaccia per la gestione dei filtri
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public interface Filter <C,S> {
	
	abstract JSONArray filtersCity (JSONArray arrayCity, C city, S state);
}
