package it.univpm.OpenWeather.filter;

import org.json.simple.JSONArray;

import it.univpm.OpenWeather.exception.FilterException;


/**
 * Interfaccia per la gestione dei filtri
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public interface Filter <C> {
	
	/**
	 * Metodo che viene implementato dalle singole classi 
	 * @param city Parametro di filtraggio
	 * @return Array filtrato mediante i parametri specificati
	 * @throws FilterException Eccezione personalizzata
	 */
	abstract JSONArray filtersCity (C city) throws FilterException;
	
}
