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
	
	/**
	 * Metodo che viene implementato dalle singole classi 
	 * @param arrayCity Array sul quale si applicano i filtri
	 * @param param1 Primo parametro di filtraggio
	 * @param param2 Secondo parametro di filtraggio 
	 * @return Array filtrato mediante i parametri specificati
	 */
	abstract JSONArray filtersCity (JSONArray arrayCity, C city, S period);
	
}
