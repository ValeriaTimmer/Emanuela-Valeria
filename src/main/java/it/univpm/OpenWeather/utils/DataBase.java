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
		this.arr = p.caricaFile(Config.getName());
		//this.cityFilter = new CityFilter();
		this.addToDataBase();
	}
	
	/**
	 * Metodo che salva ogni ora i dati di alcune città costantemente monitorate
	 * 
	 * @throws UrlException Eccezione personalizzata
	 * @throws ParseException Errore di Parsing
	 * @throws MalformedURLException Errore formato dell'Url
	 * @throws IOException Errore di I/O
	 */
	@Scheduled (cron = "0 0/30 * * * ?") //In questo modo effettua una chiamata ogni minuto
	public void addToDataBase() throws UrlException, ParseException, MalformedURLException, IOException {
		
		final Logger logger = LoggerFactory.getLogger(DataBase.class);
		
		if (Config.getCall()) {
					
				logger.info("Sto recuperando i dati delle città di Roma, Londra, Berlino e Parigi");
				p.salvaFile(Config.getName(),"Roma");
				p.salvaFile(Config.getName(), "Londra");
				p.salvaFile(Config.getName(), "Berlino");
				p.salvaFile(Config.getName(), "Parigi");
				
		}

	}
	
	public JSONArray readFile(ArrayList<String> array, String city)  {
		JSONObject jsonObject = new JSONObject();
		Iterator<String> iter = array.iterator();
		while(iter.hasNext()) {
			ArrayList<String> list = new ArrayList<String>();
			list = p.caricaFile(Config.getName());
			for(int i =0; i<list.size(); i++) {
				JSONArray arr = new JSONArray();
			   for(Object o : arr ) {
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
			}
			return value;
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
		
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObject = new JSONObject();
		
		City c = new City (city);
		
		JSONArray array = (JSONArray) JSONValue.parseWithException(ParsingJSON.ParsingToJSONString(p.caricaFile(Config.getName())));
		
		for (Object o : array) {
			
			JSONObject obj = (JSONObject) o;
		
			String citta = (String) obj.get("city");
			
			if (citta.toString().equals(city)){
				
				Double hum = (Double) obj.get("humidity");
				Double temp = (Double) obj.get("temperature");
				Double data = (Double) obj.get("date");
				
				jsonObject.put("city", citta);
				jsonObject.put("humidity", hum);
				jsonObject.put("temperature", temp);
				jsonObject.put("date", data);
				
				value.add(jsonObject);
			}
			
		}
			
			if (value == null | value.isEmpty()) {
				
				jsonObject.put("Errore", "Non riesco a leggere l'array da caricaFile");
				value.add(jsonObject);
			}

		
		
		return value;
	}

}
