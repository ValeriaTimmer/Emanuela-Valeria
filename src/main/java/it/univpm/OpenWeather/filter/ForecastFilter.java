package it.univpm.OpenWeather.filter;
import org.json.simple.JSONArray;

import it.univpm.OpenWeather.exception.FilterException;

/**
 * Classe che si occupa del filtraggio delle citta per 
 * il calcolo delle previsioni azzeccate ed estende dalla classe Filter
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public class ForecastFilter extends Filter{
    
	
	/**
	 * Costruttore
	 * @param name nome del file
	 */
	public ForecastFilter(String name) {
		super(name);
	}
	
	/**
	 * Metodo che effettua il filtraggio in base ai parametri inseriti
	 * @param date Data
	 * @param city Nome della citta
	 * @param array JSONArray da filtrare
	 * @return JSONArray Array filtrato
	 * @throws FilterException Eccezione personalizzata
	 */
	public JSONArray filtersCity (Object date, Object city, JSONArray array) throws FilterException {
		
		if(city == null || date == null) throw new FilterException ("Parametri di filtraggio nulli!");
		
		return (JSONArray) utils.getDateFiltered (date.toString(), city.toString(), array);
	
	}
}
