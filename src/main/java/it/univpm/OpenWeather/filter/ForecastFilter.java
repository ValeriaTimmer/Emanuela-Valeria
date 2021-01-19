package it.univpm.OpenWeather.filter;
import org.json.simple.JSONArray;

import it.univpm.OpenWeather.exception.FilterException;
import it.univpm.OpenWeather.utils.*;

public class ForecastFilter {
    
	/**
	 * oggetto della classe FilterUtils
	 */
	private FilterUtils utils = new FilterUtils();
	
	/**
	 * costruttore
	 */
	public ForecastFilter() {}
	
	/**
	 * metodo che richiama il metodo della classe FilterUtils
	 * @param date data
	 * @param city nome della città
	 * @param array JSONArray da filtrare
	 * @return JSONArray array filtrato
	 */
	public JSONArray filtersCity (Object date, Object city, JSONArray array) throws FilterException{
		if(city == null || date == null) throw new FilterException("parametri di filtraggio nulli!");
		return (JSONArray) utils.getDateFiltered (date.toString(), city.toString(), array);
	}
}
