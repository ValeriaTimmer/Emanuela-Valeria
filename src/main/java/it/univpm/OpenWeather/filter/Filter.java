package it.univpm.OpenWeather.filter;

import org.json.simple.JSONArray;

import it.univpm.OpenWeather.exception.FilterException;

import java.util.ArrayList;


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
	 * @param arrayCity Array sul quale si applicano i filtri
	 * @param param1 Primo parametro di filtraggio
	 * @return Array filtrato mediante i parametri specificati
	 */
	abstract JSONArray filtersCity (C city) throws FilterException;
	
}
