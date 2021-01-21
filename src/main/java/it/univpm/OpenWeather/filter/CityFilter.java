package it.univpm.OpenWeather.filter;

import it.univpm.OpenWeather.exception.*;

import org.json.simple.JSONArray;

/**
 * Classe che estende dalla classe Filter
 * Filtra la citta 
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class CityFilter extends Filter  {
	
	/**
	 * Costruttore 
	 * @param name nome del file
	 */
	public CityFilter(String name) {
		super(name);
	}
	
	
	/**
	 * Metodo che filtra le citta in base al nome
	 * @param city nome della citta
	 * @param name nome del file
	 * @return JSONArray contenente i dati filtrati
	 * @throws FilterException eccezione personalizzata
	 */
	
	public JSONArray filtersCity (Object city, String name) throws FilterException {
		
		if(city == null)throw new FilterException("Nome della città nullo!");
		
		return (JSONArray) utils.getCityFiltered (city.toString(), name);
	}
	

}
