package it.univpm.OpenWeather.filter;

import it.univpm.OpenWeather.utils.FilterUtils;
import it.univpm.OpenWeather.exception.DataFormatException;
import it.univpm.OpenWeather.exception.FilterException;
import it.univpm.OpenWeather.exception.StatsException;
import it.univpm.OpenWeather.exception.UrlException;

import java.io.IOException;

import org.json.simple.JSONArray;

import com.sun.el.parser.ParseException;


/**
 * Classe che implementa l'interfaccia Filter
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class TypeFilter extends Filter{
	
	/**
	 * Costruttore 
	 * @param array Array da filtrare
	 */
	public TypeFilter(JSONArray array) {
		super(array);
	}
	
	
	/**
	 * Metodo che richiama il 
	 * metodo della classe FilterUtils
	 * @param type Parametro di cui si vogliono ottenere i dati
	 * @return Ritorna l'array filtrato 
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws StatsException eccezione personalizzata
	 * @throws IOException errore di I/O
	 * @throws ParseException errore di Parsing
	 * @throws DataFormatException eccezione personalizzata
	 * @throws UrlException eccezione personalizzata
	 */
	public JSONArray filtersCity (String city, String type, String from, String to) throws UrlException, DataFormatException, ParseException, IOException, StatsException, org.json.simple.parser.ParseException{
		
		if (type == "" || city == "") throw new FilterException ("Parametri nulli!");
		
		return (JSONArray) utils.getTypeFiltered (city,  type, from,  to);
		
	}

}
