package it.univpm.OpenWeather.controller;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.exception.*;

import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


/**
 *  Rappresenta la classe che gestisce le chiamate al server
 *  
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */

@RestController
public class Controller {
	
	@Autowired(required=true)
    CityServiceImpl c;
	
	/**
	 * Rotta che gestisce la chiamata della rotta "GET/metadata"
	 * @return Il vettore contenete i metadati
	 */
	@SuppressWarnings ("unchecked")
	@RequestMapping (value = "/metadata", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetadata(){
		return new ResponseEntity<>(c.getMetadata(), HttpStatus.OK);
	}
	
	@RequestMapping (value = "/data", method = RequestMethod.POST)
	public ResponseEntity <Object> getData(@RequestBody JSONObject city) throws UrlException, IllegalArgumentException, MalformedURLException, org.json.simple.parser.ParseException, IOException{
		return new ResponseEntity<>(c.getData(city.toString()), HttpStatus.OK);
	}
	
	/**
	 * Rotta che gestisce la chiamata della rotta "POST/stats"
	 * @param city Nome della città
	 * @param state Nome dello stato della città
	 * @param type Parametro sul quale vengono effettuate le statistiche
	 * @param from Data iniziale da quando si vogliono far partire le statistiche
	 * @param to Data finale entro il quale si vogliono visualizzare le statistiche
	 * @return JSONArray contenente le statistiche
	 * @throws StatsNotFoundException Eccezione personalizzata 
	 * @throws UrlException Eccezione personalizzata 
	 * @throws org.json.simple.parser.ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	*/
	
	@RequestMapping (value = "/stats", method = RequestMethod.POST)
	public HashMap <String, String> getStats (@RequestBody JSONObject body)
			throws StatsNotFoundException, UrlException, ClassNotFoundException, ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
			this.c = new CityServiceImpl();
			String city = (String) body.get("city");
			String type = (String) body.get("type");
			String from = (String) body.get("from");
			String to = (String) body.get("to");
		
		return c.getStats(city, type, from, to);
		
	}
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filters/cities"
	 * @param city Nome della città da filtrare
	 * @param state Sigla dello stato da filtrare
	 * @return JSONArray delle città filtrate
	 * @throws FilterNotFoundException Eccezione personalizzata
	 * @throws UrlException Eccezione personalizzata 
	 *
	@RequestMapping(value = "/filters/cities", method = RequestMethod.POST)
	public JSONArray getCityFiltered(@RequestBody JSONObject body) 
			throws FilterNotFoundException, UrlException, ClassNotFoundException {
		String city = (String) body.get("city");
		String state = (String) body.get("country");
		return c.getCityFiltered(city, state);
	}
	*/
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filters/humidity"
	 * @param from Valore minimo dell'intervallo di umidità
	 * @param to Valore massimo dell'intervallo di umidità
	 * @return JSONArray contente i valori di umidità filtrati
	 * @throws FilterNotFoundException Eccezione personalizzata
	 * @throws UrlException Eccezione personalizzata 
	 *
	@RequestMapping (value = "/filters/humidity", method = RequestMethod.POST)
	public JSONArray getHumidityFiltered (@RequestBody JSONObject body) throws FilterNotFoundException, UrlException,
	ClassNotFoundException {
		String from = (String) body.get("from");
		String to = (String) body.get("to");
		if ((from == null | to == null) && (from.compareTo(to) > 0))
			return null;
		else  
			return c.getHumidityFiltered(from, to);
	}
	*/
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filters/temperature"
	 * @param from Valore minimo dell'intervallo di temperatura
	 * @param to Valore massimo dell'intervallo di temperatura	 
	 * @return JSONArray contenete i valori filtrati
	 * @throws FilterNotFoundException Eccezione personalizzata
	 * @throws UrlException Eccezione personalizzata
	 *
	@RequestMapping (value = "/filters/temperature", method = RequestMethod.POST)
	public JSONArray getTemperatureFiltered (@RequestBody JSONObject body) throws FilterNotFoundException, UrlException,
	ClassNotFoundException {
		String from = (String) body.get("from");
		String to = (String) body. get("to");
		if ((from == null | to == null) && (from.compareTo(to) > 0))
			return null;
		else  
			return c.getTemperatureFiltered (from, to);
	}
	*/
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filter/weather"
	 * @param city Nome della città
	 * @param weather Descrizione meteo della città
	 * @return JSONArray contenente i valori filtrati
	 * @throws FilterNotFoundException Eccezione personalizzata
	 * @throws UrlException Eccezione personalizzata
	 *
	@RequestMapping (value = "/filters/weather", method = RequestMethod.POST)
	public JSONArray getWeatherFiltered (@RequestBody JSONObject body) throws FilterNotFoundException, UrlException,
	ClassNotFoundException {
		String city = (String) body.get("city");
		String weather = (String) body.get("description");
		return c.getWeatherFiltered(city,weather);
	}
		*/	
}


