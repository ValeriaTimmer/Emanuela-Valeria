package it.univpm.OpenWeather.controller;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.exception.*;

import java.util.HashMap;
import java.util.ArrayList;
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
	
	/**
	 * rotta che gestisce la chiamata della rotta "GET/data"
	 * @param city nome della città
	 * @return JSONArray contenente le previsioni attuali e quelle dei successivi 5 giorni
	 * @throws UrlException eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws IOException errore di I/O
	 */
	@RequestMapping (value = "/data", method = RequestMethod.GET)
	public ResponseEntity<Object> getData(@RequestParam ( value = "city", defaultValue = "")String city) throws UrlException,
	org.json.simple.parser.ParseException, IOException{
		return new ResponseEntity<>(c.getData(city), HttpStatus.OK);
	}
	
	/**
	 * Rotta che gestisce la chiamata "GET/dailystats"
	 * @param city nome della citta
	 * @return HashMap contenente i dati della citta
	 * @throws UrlException eccezione personalizzata
	 * @throws IllegalArgumentException eccezione che indica che è stato passato un argomento errato
	 * @throws MalformedURLException eccezione che viene lanciata nel caso in cui l'URL non e corretto
	 * @throws org.json.simple.parser.ParseException eccezione che viene lanciata nel caso di un errore di Parsing
	 * @throws IOException errore di I/O
	 * @throws StatsException eccezione personalizzata
	 * @throws com.sun.el.parser.ParseException 
	 */
	
	@RequestMapping (value = "/dailystats", method = RequestMethod.GET)
	public ResponseEntity <Object> getData(@RequestParam (value = "City", defaultValue = "") String city,
			@RequestParam(value = "type", defaultValue = "humidity") String type,
			@RequestParam(value = "from", defaultValue = "") String from,
			@RequestParam(value = "to", defaultValue = "")String to) 
					throws UrlException, IllegalArgumentException, MalformedURLException,
					org.json.simple.parser.ParseException, IOException, ParseException, StatsException, com.sun.el.parser.ParseException{
		return new ResponseEntity<>(c.getDailyStats(city.toString(), type.toString(), from.toString(), to.toString()), HttpStatus.OK);
	}
	
	/**
	 * Rotta che gestisce la chiamata "POST/stats"
	 * @return HashMap contenente le statistiche
	 * @throws StatsException Eccezione personalizzata 
	 * @throws UrlException Eccezione personalizzata 
	 * @throws ClassNotFoundException
	 * @throws org.json.simple.parser.ParseException eccezione che viene lanciata nel caso di un errore di Parsing
	 * @throws IOException errore di I/O
	 * @throws FileNotFoundException eccezione che viene lanciata quando non viene trovato il file
	 * @throws ParseException errore di Parsing
	*/
	
	@RequestMapping (value = "/stats", method = RequestMethod.POST)
	public HashMap <String, String> getStats (@RequestBody JSONObject body)
			throws StatsException, UrlException, ClassNotFoundException, ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
			this.c = new CityServiceImpl();
			String city = (String) body.get("city");
			String type = (String) body.get("type");
			String from = (String) body.get("from");
			String to = (String) body.get("to");
		
		return c.getStats(city, type, from, to);
		/*
		 * for(int i=0; i<array.size(); i++){
		 * JSONObject obj = (JSONObject) array.get(i);
		 * String city = (String) obj.get("name");
		 * 
		 * map.put("values", c.getStats(city, type, from, to).toString();
		 * 
		 * }
		 * 
		 * return map;
		 */
	}
	
	
	/**
	 * Rotta che gestisce la chiamata "POST/forecasts"
	 * @param date data
	 * @param city nome della citta
	 * @return JSONObject contenente il valore del contatore
	 * @throws UrlException Eccezione personalizzata
	 * @throws MalformedURLException eccezione che viene lanciata nel caso in cui l'url non e corretto
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws IOException errore di I/O
	 */
	@RequestMapping(value = "/forecasts", method = RequestMethod.POST)
	public ResponseEntity <Object> getForecasts (@RequestParam(value = "date", defaultValue = "")String date,
			@RequestParam(value = "city", defaultValue = "")String city) throws UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException{
		return new ResponseEntity<>(c.getForecasts(date.toString(), city.toString()), HttpStatus.OK);
	}
}


