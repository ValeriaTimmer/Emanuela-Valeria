package it.univpm.OpenWeather.controller;

import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.statistics.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	private DownloadCity d;
	
	@Autowired
	CityService c;
	
	/**
	 * Rotta che gestisce la chiamata della rotta "GET/metadata"
	 * @return Il vettore contenete i metadati
	 */
	@RequestMapping (value = "/metadata", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetadata(){
		return new ResponseEntity<>(c.getMetadata(), HttpStatus.OK);
	}
	
	/**
	 * Rotta che gestisce la chiamata della rotta "POST/stats"
	 * @param type Parametro sul quale vengono effettuate le statistiche
	 * @param from Data iniziale da quando si vogliono far partire le statistiche
	 * @param to Data finale entro il quale si vogliono visualizzare le statistiche
	 * @return JSONArray contenente le statistiche
	 * @throws StatsNotFoundException Eccezione personalizzata 
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws UrlException Eccezione personalizzata 
	 */
	@GetMapping (value = "/stats")
	public JSONArray getStats (@RequestParam (value = "type", defaultValue = "humidity") String type, 
			@RequestParam (value = "from", defaultValue = "null") String from,
			@RequestParam (value = "to", defaultValue = "null") String to) throws StatsNotFoundException, DataFormatException,
	UrlException {
		return c.getStats(type, from, to);
	}
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filters/cities"
	 * @param city Nome della città da filtrare
	 * @param state Sigla dello stato da filtrare
	 * @return Città filtrate
	 * @throws FilterNotFoundException Eccezione personalizzata
	 * @throws UrlException Eccezione personalizzata 
	 */
	@PostMapping("/filters/cities")
	public JSONArray getCityFiltered(@RequestParam (value = "city", defaultValue = "null") String city,
			@RequestParam (value = "state", defaultValue = "null") String state) 
					throws FilterNotFoundException, UrlException {
		return c.getCityFiltered(city, state);
	}
	
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filters/humidity"
	 * @param from Valore minimo dell'intervallo di umidità
	 * @param to Valore massimo dell'intervallo di umidità
	 * @param filter Body del filtro richiesto
	 * @return Umidità filtrata 
	 * @throws FilterNotFoundException Eccezione personalizzata
	 * @throws UrlException Eccezione personalizzata 
	 */
	@PostMapping (value = "/filters/humidity")
	public JSONArray getHumidityFiltered (@RequestParam (value ="from", defaultValue = "0.0") String from,
			@RequestParam (value = "to", defaultValue = "100.0") String to) throws FilterNotFoundException, UrlException {
		return c.getHumidityFiltered(from, to);
	}
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filters/temperature"
	 * @param from Valore minimo dell'intervallo di temperatura
	 * @param to Valore massimo dell'intervallo di temperatura
	 * @param filter Body del filtro richiesto
	 * @return Lista contenete i valori filtrati
	 * @throws IOException Se ci sono problemi di I/O
	 */
	@PostMapping (value = "/filters/temperature")
	public JSONArray getTemperatureFiltered (@RequestParam (value = "from", defaultValue = "253.15") String from,
			@RequestParam (value = "to", defaultValue = "318.15") String to) throws FilterNotFoundException, UrlException {
		return c.getTemperatureFiltered (from, to);
	}
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filter/weather"
	 * @param city nome della città
	 * @param weather tempo della città
	 * @param filter Body del filtro richiesto
	 * @return Lista contenente i valori filtrati
	 * @throws IOException se ci sono problemi di I/O
	 */
	@PostMapping (value = "/filters/weather")
	public JSONArray getWeatherFiltered (@RequestParam (value = "city", defaultValue ="null")String weather,
			@RequestParam(value = "weather", defaultValue = "null")String city) throws FilterNotFoundException, UrlException {
		return c.getWeatherFiltered(city,weather);
	}
			
}


