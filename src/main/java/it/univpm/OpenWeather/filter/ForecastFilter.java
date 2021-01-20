package it.univpm.OpenWeather.filter;
import org.json.simple.JSONArray;

import it.univpm.OpenWeather.exception.FilterException;
import it.univpm.OpenWeather.utils.*;

/**
 * Classe che si occupa del filtraggio delle città per 
 * il calcolo delle previsioni azzeccate
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public class ForecastFilter extends Filter{
    
	
	/**
	 * Costruttore
	 */
	public ForecastFilter() {
		super();
	}
	
	/**
	 * Metodo che richiama il metodo della classe FilterUtils
	 * @param date Data
	 * @param city Nome della città
	 * @param array JSONArray da filtrare
	 * @return JSONArray Array filtrato
	 * @throws FilterException Eccezione personalizzata
	 */
	public JSONArray filtersCity (Object date, Object city, JSONArray array) throws FilterException {
		
		if(city == null || date == null) throw new FilterException ("Parametri di filtraggio nulli!");
		
		return (JSONArray) utils.getDateFiltered (date.toString(), city.toString(), array);
	
	}
}
