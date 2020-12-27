package it.univpm.OpenWeather.filter;

import org.json.simple.JSONArray;

import it.univpm.OpenWeather.utils.FilterUtils;

public class TemperatureFilter implements Filter <Object, Object> {
	/**
	 * Array da filtrare
	 */
	private JSONArray arrayTemperature;
	
	/**
	 * Variabile che richiama il metodo della classe FilterUtils
	 */
	private FilterUtils utils;
	
	/**
	 * Costruttore 
	 * @param array Array da filtrare
	 */
	public TemperatureFilter (JSONArray array) {
		this.arrayTemperature = array;
		this.utils = new FilterUtils();
	}
	
	/**
	 * Metodo Getter dell'array
	 * @return arrayTemperature Ritorna l'array
	 */
	public JSONArray getTemperature() {
		return arrayTemperature;
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia e
	 * richiama il metodo della classe FilterUtils
	 * @return Ritorna l'array filtrato
	 */
	@Override
	public JSONArray filtersCity (JSONArray arrayTemperature, Object temp1, Object temp2) {
		return (JSONArray) utils.getTemperatureFiltered(this.getTemperature(), temp1, temp2);
	}
	
}
