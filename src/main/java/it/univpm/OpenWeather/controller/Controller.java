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
	
	/**
	 * Rotta che gestisce la chiamata "POST/data"
	 * @param city nome della città
	 * @return HashMap<String,String> contenente i dati della città
	 * @throws UrlException eccezione personalizzata
	 * @throws IllegalArgumentException eccezione che indica che è stato passato un argomento errato
	 * @throws MalformedURLException eccezione che viene lanciata nel caso in cui l'URL non è corretto
	 * @throws org.json.simple.parser.ParseException eccezione che viene lanciata nel caso di un errore di Parsing
	 * @throws IOException errore di I/O
	 */
	@RequestMapping (value = "/data", method = RequestMethod.POST)
	public ResponseEntity <Object> getData(@RequestParam (value = "City", defaultValue = "") String city,
			@RequestParam(value = "type", defaultValue = "humidity") String type) 
					throws UrlException, IllegalArgumentException, MalformedURLException,
					org.json.simple.parser.ParseException, IOException, ParseException{
		return new ResponseEntity<>(c.getData(city.toString(), type.toString()), HttpStatus.OK);
	}
	
	/**
	 * Rotta che gestisce la chiamata "POST/stats"
	 * @param city Nome della città
	 * @param type Parametro sul quale vengono effettuate le statistiche
	 * @param from Data iniziale da quando si vogliono far partire le statistiche
	 * @param to Data finale entro il quale si vogliono visualizzare le statistiche
	 * @return HashMap<String,String> contenente le statistiche
	 * @throws StatsNotFoundException Eccezione personalizzata 
	 * @throws UrlException Eccezione personalizzata 
	 * @throws org.json.simple.parser.ParseException eccezione che viene lanciata nel caso di un errore di Parsing
	 * @throws IOException errore di I/O
	 * @throws FileNotFoundException eccezione che viene lanciata quando non viene trovato il file
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
	 * metodo che gestisce la chiamata "POST/salvaData
	 * @param city nome della città
	 * @return JSONArray contenente le previsioni attuali
	 * @throws UrlException
	 * @throws IllegalArgumentException
	 * @throws MalformedURLException
	 * @throws org.json.simple.parser.ParseException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/salvaData", method = RequestMethod.POST)
	public ResponseEntity <Object> getSalvaData(@RequestParam (value = "City", defaultValue = "") String city) 
					throws UrlException, IllegalArgumentException, MalformedURLException,
					org.json.simple.parser.ParseException, IOException, ParseException{
		return new ResponseEntity<>(c.getSalvaData(city.toString()), HttpStatus.OK);
	}
	
	
	/**
	 * Rotta che gestisce la chiamata "POST/forecasts"
	 * @param date data
	 * @param city nome della città
	 * @return JSONObject contenente il valore del contatore
	 * @throws UrlException Eccezione personalizzata
	 * @throws MalformedURLException eccezione che viene lanciata nel caso in cui l'url non è corretto
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws IOException errore di I/O
	 */
	@RequestMapping(value = "/forecasts", method = RequestMethod.POST)
	public ResponseEntity <Object> getForecasts (@RequestParam(value = "date", defaultValue = "")String date,
			@RequestParam(value = "city", defaultValue = "")String city) throws UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException{
		return new ResponseEntity<>(c.getForecasts(date.toString(), city.toString()), HttpStatus.OK);
	}
}


