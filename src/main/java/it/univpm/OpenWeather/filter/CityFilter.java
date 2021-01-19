package it.univpm.OpenWeather.filter;

import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.exception.*;

import org.json.simple.JSONArray;
import java.util.ArrayList;

/**
 * Classe che implementa l'interfaccia Filter
 * Filtra la città e lo stato
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class CityFilter implements Filter <Object> {
	
	/**
	 * Array contenente le città
	 */
	private JSONArray arrayCity;
	
	/**
	 * Variabile usata per richiamare il metodo della classe FilterUtils
	 */
	private FilterUtils utils = new FilterUtils();
	
	public CityFilter() {}
	
	/**
	 * Costruttore 
	 */
	public CityFilter(JSONArray array) {
		this.arrayCity = array;
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
	 * @throws FilterException eccezione personalizzata
	 */
	@Override
	public JSONArray filtersCity (Object city) throws FilterException {
		if(city == null)throw new FilterException("Nome della città nullo!");
		return (JSONArray) utils.getCityFiltered (city.toString());
	}
	

}
