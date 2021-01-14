package it.univpm.OpenWeather.utils;

import java.io.IOException;

import java.net.MalformedURLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import org.slf4j.*;

import it.univpm.OpenWeather.exception.UrlException;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.model.*;
import java.util.ArrayList;
/**
 * classe che si permette di ottenere i dati desiderati
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
@Configuration
public class DataBase {
	
	@Autowired (required = true)
	private Parser p ;
	
	/**
	 * JSONArray che contiene i dati letti dal file
	 */
	private JSONArray  arr;
	
	/**
	 * JSONArray che contiene i dati desiderati
	 */
	private JSONArray value;
	
	@Autowired (required = true)
	DownloadCity download = new DownloadCity();
	
	/**
	 * JSONObject
	 */
	private JSONObject obj = new JSONObject();
	
	/**
	 * costruttore
	 * @throws UrlException eccezione personalizzata
	 * @throws ParseException errore di Parsing
	 * @throws MalformedURLException eccezione che viene lanciata se l'url è sbagliato
	 * @throws IOException errore di I/O
	 */
	public DataBase () throws UrlException, ParseException, MalformedURLException, IOException {
		p = new Parser();
		this.arr = new JSONArray();
		this.value = new JSONArray();
		this.addToDataBase();
	}
	
	/**
	 * metodo che salva ogni ora i dati di alcune città monitorate
	 * @throws UrlException
	 * @throws ParseException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@Scheduled (cron = "0 0/1 * * * ?") //In questo modo effettua una chiamata ogni minuto
	public void addToDataBase() throws UrlException, ParseException, MalformedURLException, IOException {
		
		final Logger logger = LoggerFactory.getLogger(DataBase.class);
		
		if (Config.getCall()) {
				logger.info("Sto recuperando i dati della città di Ancona");
				p.chiamataAPI("Ancona");
				p.salvaFile(Config.getName(), "Ancona");
			}
	}
		
		
	
	/**
	 * Metodo che ritorna tutti i valori desiderati di una determinata città
	 * 
	 * @param city Città di cui si vogliono ottenere i parametri
	 * @return arr JSONArray che contiene i dati da analizzare
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws MalformedURLException 
	 * @throws UrlException 
	 */
	public JSONArray getAllData (String city) throws UrlException, MalformedURLException, ParseException, IOException {

		ArrayList <String> msg = new ArrayList<String>();
		
		this.arr = download.Parsing(p.caricaFile(Config.getName()));
				
			for (Object o : this.arr) {
			
			JSONObject obj = new JSONObject();
			
			String name = (String) obj.get("city");
			
			if (name.equals(city)) {
				
				//String date = (String) obj.get("date");
				Double humidity = Double.parseDouble( obj.get("humidity").toString());
				Double temp = Double.parseDouble( obj.get("temp").toString());
				String weather = (String) obj.get("weather");
				
				//obj.put("date", date);
				obj.put("city", city);
				obj.put("humidity", humidity);
				obj.put("temperature", temp);
				obj.put("weather", weather);
				
				value.add(obj);
			}
			
			else 
				
				msg.add("La città attualmente non è monitorata, non sono presenti dati nel DataBase");
				value.add( msg);
		}
		
		return value;
	}

}
