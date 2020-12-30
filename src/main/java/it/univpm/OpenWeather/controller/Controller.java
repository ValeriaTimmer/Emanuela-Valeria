package it.univpm.OpenWeather.controller;

import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.exception.*;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@Autowired
	CityService c;
	
	/**
	 * Rotta che gestisce la chiamata della rotta "GET/metadata"
	 * @return Il vettore contenete i metadati
	 */
	@RequestMapping (value = "metadata", method = RequestMethod.GET)
	public ResponseEntity <Object> getMetadata(){
		return new ResponseEntity<> (c.getMetadata(), HttpStatus.OK);
	}
	
	/**
	 * Rotta che gestisce la chiamata della rotta "POST/stats/humidity"
	 * @param hum Parametro sul quale vengono effettuate le statistiche
	 * @param period Periodo nel quale si vogliono effettuare le statistiche
	 * @return Il vettore contenete le statistiche effettuate
	 * @throws IOException Se ci sono problemi di I/O
	 *
	@RequestMapping (value = "/stats/humidity", method = RequestMethod.POST)
	public ResponseEntity <Object> getStatisticsHumidity ( @RequestParam (value = "from", defaultValue = "null") String from,
			@RequestParam (value = "to", defaultValue = "null") String to,
			@RequestBody String filter) throws StatsNotFoundException, DataFormatException {
		return new ResponseEntity <> (c.getStatisticsHumidity("humidity", from, to), HttpStatus.OK);
	}
	/
	 * 
	 */
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/stats/temperature"
	 * @param temp Parametro sul quale vengono effettuate le statistiche
	 * @param period Periodo nel quale si vogliono effettuare le statistiche
	 * @return Il vettore contenete le statistiche effettuate
	 * @throws IOException Se ci sono problemi di I/O
	 *
	@RequestMapping (value = "/stats/temperature", method = RequestMethod.POST)
	public ResponseEntity <Object> getStatisticsTemperature ( @RequestParam (value = "from", defaultValue = "null") String from,
			@RequestParam (value = "to", defaultValue = "null") String to,
			@RequestBody String filter) throws StatsNotFoundException, DataFormatException {
		return new ResponseEntity <> (c.getStatisticsTemperature("temperature", from, to), HttpStatus.OK);
	}
	/
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filters/cities"
	 * @param city Nome della città da filtrare
	 * @param state Sigla dello stato da filtrare
	 * @param filter Body del filtro richiesto
	 * @return Lista delle città filtrate 
	 * @throws IOException Se ci sono problemi di I/O
	 */
	@RequestMapping (value = "/filters/cities", method = RequestMethod.POST)
	public ResponseEntity <Object> getCityFiltered(@RequestParam (value = "city", defaultValue = "null")String city,
			@RequestParam (value = "state", defaultValue = "null") String state,
			@RequestBody String filter) throws FilterNotFoundException {
		return new ResponseEntity <>(c.getCityFiltered(city, state), HttpStatus.OK);
	}
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filters/humidity"
	 * @param from Valore minimo dell'intervallo di umidità
	 * @param to Valore massimo dell'intervallo di umidità
	 * @param filter Body del filtro richiesto
	 * @return Lista contenente i valori filtrati
	 * @throws IOException Se ci sono problemi di I/O
	 */
	@RequestMapping (value = "/filters/humidity", method = RequestMethod.POST)
	public ResponseEntity<Object> getHumidityFiltered (@RequestParam (value ="from", defaultValue = "0.0") String from,
			@RequestParam (value = "to", defaultValue = "100.0") String to, 
			@RequestBody String filter) throws FilterNotFoundException {
		return new ResponseEntity<> (c.getHumidityFiltered (from, to), HttpStatus.OK);
	}
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filters/temperature"
	 * @param from Valore minimo dell'intervallo di temperatura
	 * @param to Valore massimo dell'intervallo di temperatura
	 * @param filter Body del filtro richiesto
	 * @return Lista contenete i valori filtrati
	 * @throws IOException Se ci sono problemi di I/O
	 */
	@RequestMapping (value = "/filters/temperature", method = RequestMethod.POST)
	public ResponseEntity <Object> getTemperatureFiltered (@RequestParam (value = "from", defaultValue = "253.15") String from,
			@RequestParam (value = "to", defaultValue = "318.15") String to,
			@RequestBody String filter) throws FilterNotFoundException {
		return new ResponseEntity<> (c.getTemperatureFiltered(from, to), HttpStatus.OK);
	}
	
	/**
	 * Metodo che gestisce la chiamata della rotta "POST/filter/weather"
	 * @param city nome della città
	 * @param weather tempo della città
	 * @param filter Body del filtro richiesto
	 * @return Lista contenente i valori filtrati
	 * @throws IOException se ci sono problemi di I/O
	 */
	@RequestMapping(value = "/filters/weather", method = RequestMethod.POST)
	public ResponseEntity <Object> getWeatherFiltered (@RequestParam (value = "city", defaultValue ="null")String weather,
			@RequestParam(value = "weather", defaultValue = "null")String city,
			@RequestBody String filter) throws FilterNotFoundException {
		return new ResponseEntity<> (c.getWeatherFiltered(city,weather), HttpStatus.OK);
	}
			
}


