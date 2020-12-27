package it.univpm.OpenWeather.controller;
import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.statistics.*;
import it.univpm.OpenWeather.utils.StatsUtils;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * Rotta per visualizzare le città
	 * @return Il vettore contenente le città
	 */
	@GetMapping ("/cities")
	public ResponseEntity <Object> getCity(){
		return new ResponseEntity <>(c.getCity(), HttpStatus.OK);
		}
	
	/**
	 * Rotta per visualizzare le statistiche elaborate sulle città
	 * @return Il vettore contenete le statistiche effettuate
	 *
	 */
	// @GetMapping ("/stats")
	// public ResponseEntity <Object> getStats(@RequestParam ()){
	//return new ResponseEntity <> (c.getStats(), HttpStatus.OK);
	
	
	
	/**
	 * Metodo che gestisce la POST nella rotta "/filters/humidity"
	 * @param from Valore minimo dell'intervallo di umidità
	 * @param to Valore massimo dell'intervallo di umidità
	 * @param filter Body del filtro richiesto
	 * @return ArrayList contenente i valori filtrati
	 */
	@RequestMapping (value = "/filters/humidity", method = RequestMethod.POST)
	public ResponseEntity<Object> getFiltered (@RequestParam (value ="from", defaultValue = "0.0") String from,
			@RequestParam (value = "to", defaultValue = "100.0") String to, 
			@RequestBody String filter) {
		return new ResponseEntity<> (c.getFiltered (from, to), HttpStatus.OK);
	}
}


