package it.univpm.OpenWeather.statistics;

import it.univpm.OpenWeather.exception.UrlException;
import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.model.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.HashMap;
import org.json.simple.JSONArray;
import java.util.ArrayList;
/**
 * classe astratta che serve per calcolare le statistiche 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public abstract class AbstractStats {

	/**
	 * array contenente i dati
	 */
	protected ArrayList <City> arr;
	
	/**
	 * costruttore
	 * @param array
	 */
	public AbstractStats (ArrayList<City> array) {
		this.arr = array;
	}
	
	/**
	 * metodo getter dell'array con i dati
	 * @return arr array contenente i dati
	 */
	public ArrayList<City> getArray() {
		return this.arr;
	}
	
	/**
	 * metodo astratto che viene poi implementato nella sottoclasse
	 * @param array array contenente le città
	 * @param city nome della città
	 * @param state sigla dello stato della città
	 * @param parameter Parametro(umidità/temperatura) sul quale si effettua la statistica
	 * @param from Data di inizio del periodo su cui si effettuano le statistiche
	 * @param to Data di fine del periodo su cui si effettuano le statistiche
	 * @return ritorna l'array contenente le statistiche effettuate
	 * @throws IOException 
	 * @throws org.json.simple.parser.ParseException 
	 * @throws MalformedURLException 
	 * @throws UrlException 
	 * @throws ParseException 
	 */
	public abstract HashMap<String,String> Statistics(ArrayList <City> list, String city, String parameter, String from, String to ) throws ParseException, UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException;
}
