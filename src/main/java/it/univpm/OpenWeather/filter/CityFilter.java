package it.univpm.OpenWeather.filter;

import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.service.*;

import org.json.simple.JSONArray;

/**
 * Classe che implementa l'interfaccia Filter
 * Filtra la città e lo stato
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class CityFilter implements Filter <Object, Object> {
	
	/**
	 * Array contenente le città
	 */
	private JSONArray arrayCity;
	
	/**
	 * Variabile usata per richiamare il metodo della classe FilterUtils
	 */
	private FilterUtils utils;
	
	/**
	 * Costruttore 
	 */
	public CityFilter(JSONArray array) {
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
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia e 
	 * richiama il metodo della classe FiltereUtils
	 */
	@Override
	public JSONArray filtersCity (JSONArray arrayCity, Object city, Object state) {
		//return (JSONArray) utils.getCityFiltered (this.getCity(), city, state);
		return arrayCity;
	}
	

}
