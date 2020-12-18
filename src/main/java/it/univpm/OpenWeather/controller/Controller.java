package it.univpm.OpenWeather.controller;
import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@GetMapping ("/stats")
	public ResponseEntity <Object> getStats(){
		return new ResponseEntity <> (c.getStats(), HttpStatus.OK);
	}
	*/
	
	/*
	 * Rotta per visualizzare i filtri elaborati sulle città
	 * @return Il vettore delle città filtrate
	@GetMapping ("/filters")
	public Object getFilter (@RequestMapping JSONObject bodyFilter){
		try {
			
		} catch() {
			
		}
	}
	*/

}
