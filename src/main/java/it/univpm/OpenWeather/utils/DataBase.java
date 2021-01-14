package it.univpm.OpenWeather.utils;

import java.io.IOException;

import java.net.MalformedURLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import it.univpm.OpenWeather.exception.UrlException;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.model.*;
import java.util.ArrayList;

@Component 
public class DataBase {
	
	@Autowired (required = true)
	Parser p ;
	
	private JSONArray  arr = new JSONArray();
	private JSONArray value = new JSONArray();
	
	@Autowired (required = true)
	DownloadCity download = new DownloadCity();
	
	JSONObject obj = new JSONObject();
	
	public DataBase () throws UrlException, ParseException, MalformedURLException, IOException {
		p = new Parser();
		this.addToDataBase();
	}
	
	@Scheduled (cron = "0 0 */2 * * ?")
	public void addToDataBase() throws UrlException, ParseException, MalformedURLException, IOException {
		
		if (Config.getCall()) {
			
				p.chiamataAPI("Ancona");
				p.salvaFile(Config.getName());
				download.Parsing(p.caricaFile(Config.getName()));
				obj.put("date", DateUtils.today());
				obj.put("values", download);
				this.arr.add(obj);
			}
	}
		
		
	
	/**
	 * Metodo che ritorna tutti i valori desiderati di una determinata città
	 * 
	 * @param city Città di cui si vogliono ottenere i parametri
	 * @return arr JSONArray che contiene i dati da analizzare
	 */
	public JSONArray getAllData (String city) {

		ArrayList <String> msg = new ArrayList<String>();
		
			for (Object o : this.arr) {
			
			JSONObject obj = new JSONObject();
			
			String name = (String) obj.get("city");
			
			if (name.equals(city)) {
				
				String date = (String) obj.get("date");
				String state = (String) obj.get("country");
				Double humidity = Double.parseDouble( obj.get("humidity").toString());
				Double temp = Double.parseDouble( obj.get("temp").toString());
				String weather = (String) obj.get("weather");
				
				obj.put("date", date);
				obj.put("city", city);
				obj.put("country", state);
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
	/**
	 * Metodo che permette di salvare i dati ogni ora
	 */
	
	 /**
	public void ScheduledAtFixedRate() throws UrlException {
		try {			

					/**
					 * Costruttore della classe Date di java.lang.Object che iniziailizza 
					 * il momento di inizio del metodo
					 * 
					 * @Deprecated : Alloca un oggetto Date e lo inizializza in modo che rappresenti la mezzanotte, 
					 * dell'ora locale, all'inizio del giorno specificato dagli argomenti anno, mese e data.
					 *
					Date firstTime = new Date(121, 1, 8);
					/
					/**
					 * Costruttore della classe Timer di java.lang.Object
					 *
					Timer timer = new Timer();
					/
					/*
					 * Costruttore della classe TimerTask che effettua l'override del metodo run
					 * dell'interfaccia Runnable di java.lang.Object
					 *
					TimerTask task = new TimerTask() {
					/
					/*
					 * Metodo che salva le informazioni delle città scelte in un arrayList
					 * Effettua l'override del metodo run() della classe TimerTask
					 *
						@Override
					public void run(){ 
							p.chiamataAPI();
						}
					};
					/
					/*
					* Metodo che ripete il salvataggio ogni ora, a partire da una data scelta (firstTime)
					 *
					timer.scheduleAtFixedRate (task, firstTime, 30000);
					
					} catch (IllegalArgumentException e) {
							// Viene lanciata se firstTime.getTime() < 0 o period <= 0
							e.printStackTrace();
					} catch (IllegalStateException e) {
							// Viene lanciata se l'attività era già pianificata o annullata,
							// il timer è stato annullato o il thread del timer è stato terminato
							System.out.println ("Errore Scheduler");
							System.out.println ("Messaggio: " + e.getMessage());
							System.out.println ("Causa: " + e.getCause());
					} catch (NullPointerException e) {
							// Viene lanciata se task o fistTime sono null
							e.printStackTrace();
					}
				}
	*/

}
