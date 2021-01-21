package it.univpm.OpenWeather.filter;

import it.univpm.OpenWeather.exception.DataFormatException;
import it.univpm.OpenWeather.exception.FilterException;
import it.univpm.OpenWeather.exception.StatsException;
import it.univpm.OpenWeather.exception.UrlException;

import java.io.IOException;

import org.json.simple.JSONArray;

import com.sun.el.parser.ParseException;


/**
 * Classe che estende dalla classe Filter
 * e si occupa di effettuare le statistiche con periodicità giornaliera
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class TypeFilter extends Filter{
	
	/**
	 * Costruttore 
	 * @param name nome del file
	 */
	public TypeFilter(String name) {
		super(name);
	}
	
	
	/**
	 * Metodo che si occupa del filtraggio in base ai dati inseriti
	 * @param city nome della citta
	 * @param type Parametro di cui si vogliono ottenere i dati
	 * @param from data di inizio del periodo
	 * @param to data di fine del periodo
	 * @return JSONArray Ritorna l'array filtrato 
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws StatsException eccezione personalizzata
	 * @throws IOException errore di I/O
	 * @throws DataFormatException eccezione personalizzata
	 * @throws UrlException eccezione personalizzata
	 */
	public JSONArray filtersCity (String city, String type, String from, String to) 
			throws UrlException, DataFormatException, IOException, StatsException, 
			org.json.simple.parser.ParseException{
		
		if (type == "" || city == "") throw new FilterException ("Parametri nulli!");
		
		return (JSONArray) utils.getTypeFiltered (city,  type, from,  to);
		
	}

}
