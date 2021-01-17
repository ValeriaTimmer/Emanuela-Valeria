package it.univpm.OpenWeather.utils;

import java.io.IOException;

import java.net.MalformedURLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.*;

import it.univpm.OpenWeather.exception.UrlException;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.model.*;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Classe che ha la funzione di salvare i dati ogni ora e di ritornare i dati desiderati
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
@Configuration
public class DataBase {
	
	/**
	 * Variabile della classe Parser
	 */
	@Autowired (required = true)
	private Parser p ;
	
	/**
	 * JSONArray che contiene i dati letti dal file
	 */
	private ArrayList<String>  arr;
	
	/**
	 * JSONArray che contiene i dati desiderati
	 */
	private JSONArray value;
	
	/**
	 * Variabile della classe CityFilter
	 */
	private CityFilter cityFilter;
	
	@Autowired (required = true)
	private DownloadCity d ;
	
	/**
	 * JSONObject
	 */
	private JSONObject obj = new JSONObject();
	
	/**
	 * Costruttore
	 * 
	 * @throws UrlException Eccezione personalizzata
	 * @throws ParseException Errore di Parsing
	 * @throws MalformedURLException Errore formato dell'Url
	 * @throws IOException Errore di I/O
	 */
	public DataBase () throws UrlException, ParseException, MalformedURLException, IOException {
		p = new Parser();
		d = new DownloadCity();
		this.arr = d.getValues(Config.getName());
		//this.cityFilter = new CityFilter();
		//this.addToDataBase();
	}
	
	/**
	 * Metodo che salva ogni ora i dati di alcune città costantemente monitorate
	 * 
	 * @throws UrlException Eccezione personalizzata
	 * @throws ParseException Errore di Parsing
	 * @throws MalformedURLException Errore formato dell'Url
	 * @throws IOException Errore di I/O
	 */
	@Scheduled (cron = "0 0/5 * * * ?") //In questo modo effettua una chiamata ogni minuto
	public void addToDataBase() throws UrlException, ParseException, MalformedURLException, IOException {
		
		final Logger logger = LoggerFactory.getLogger(DataBase.class);
		
		if (Config.getCall()) {
					
				logger.info("Sto recuperando i dati delle città di Roma, Londra, Berlino e Parigi");
				d.saveValues(Config.getName(),"Roma");
				d.saveValues(Config.getName(), "Londra");
				d.saveValues(Config.getName(), "Berlino");
				d.saveValues(Config.getName(), "Parigi");
				
		}

	}
	/*
	public JSONArray ritornaCaricaFile () {
		
		JSONArray jsonArray = new JSONArray();
		jsonArray = p.caricaFile(Config.getName());
		return jsonArray;
	}
	*/
	/**
	 * Metodo che permette di leggere tutti i valori contenuti nell'ArrayList
	 * e seleziona i valori di una città desiderata
	 * 
	 * @param array ArrayList contenente i dati 
	 * @param city Nome della città di cui si vogliono ottenere i dati
	 * @return JSONArray che contiene i dati della città 
	 */
	public JSONArray readFile(String city)  {
		
		JSONObject jsonObject = new JSONObject();
		
		JSONArray list = new JSONArray();
			
		list = p.caricaFile(Config.getName());
			
		for (int i =0; i<list.size(); i++) {

			JSONArray jsonAr = (JSONArray) list.get(i);
			   
				for(Object o : jsonAr) {
					
				   if(o instanceof JSONObject) {
					   
					   JSONObject obj = (JSONObject)o;
					   
					   String citta = (String) obj.get("city");
						
						if (citta.toString().equals(city)){
							
							Double hum = Double.parseDouble(obj.get("humidity").toString());
							Double temp = Double.parseDouble(obj.get("temperature").toString());
							String data = (String) obj.get("date");
							
							jsonObject.put("city", citta);
							jsonObject.put("humidity", hum);
							jsonObject.put("temperature", temp);
							jsonObject.put("date", data);
							
							value.add(jsonObject);
						}
						
					}
					   
				   }
			   }
			
			
		return (JSONArray) p.caricaFile(Config.getName());
		
	}
	
	
	/**
	 * Metodo che ritorna tutti i valori desiderati di una determinata città
	 * 
	 * @param city Città di cui si vogliono ottenere i parametri
	 * @return arr JSONArray che contiene i dati da analizzare
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 * @throws MalformedURLException Errore di formato dell'Url
	 * @throws UrlException Eccezione personalizzata
	 */
	public JSONArray getAllData (String city) throws UrlException, MalformedURLException, ParseException, IOException {
		
		cityFilter = new CityFilter();
		
		this.value = cityFilter.filtersCity(city);
		
		return value;
	}

}
