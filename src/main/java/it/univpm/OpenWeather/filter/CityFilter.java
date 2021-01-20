package it.univpm.OpenWeather.filter;

import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.exception.*;

import org.json.simple.JSONArray;

/**
 * Classe che implementa l'interfaccia Filter
 * Filtra la città e lo stato
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class CityFilter extends Filter  {
	
	/**
	 * Costruttore 
	 */
	public CityFilter() {
		super();
	}
	
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia e 
	 * richiama il metodo della classe FiltereUtils
	 * @throws FilterException eccezione personalizzata
	 */
	
	public JSONArray filtersCity (Object city) throws FilterException {
		
		if(city == null)throw new FilterException("Nome della città nullo!");
		
		return (JSONArray) utils.getCityFiltered (city.toString());
	}
	

}
