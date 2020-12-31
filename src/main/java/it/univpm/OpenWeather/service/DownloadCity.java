package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.utils.ParsingJSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import java.lang.Object;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Classe che serve per parsare il JSON ricevuto dall'API e popola i campi
 * Humidity, Temperature, Weather
 * I campi cityName e stateCode vengono passati come argomento
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public class DownloadCity {
	/*
	 * Nome della città
	 */
	private String cityName;
	
	/*
	 * Nome del paese/stato della città
	 */
	private String stateCode;
	
	/**
	 * Umidità della città in percentuale
	 */
	private double humidity;
	
	/**
	 * Temperatura della città in kelvin
	 */
	 private double temperature;
	 /**
	 * Descrizione del meteo della città
	 */
	private String weather;
	/**
	 * Chiave API privata 
	 */
	private String apiKey;
	
	private ArrayList<City> array;
	
	/**
	 * Costruttore che prende in ingresso il nome della città e il suo stato/paese
	 * 
	 * @param Name Nome della città
	 * @param Code Sigla dello Stato/Paese della città
	 */
	
	public DownloadCity (String Name, String Code, String apiKey) {
		this.cityName = Name;
		this.stateCode = Code;
		this.apiKey = apiKey;
	}
	
	/**
	 * Costruttore senza parametri
	 */
	public DownloadCity() {}
	
	/**
	 * Metodo che permette di Parsare i campi desiderati del JSON 
	 */
	public JSONArray Parser() {
		
		JSONParser parser = new JSONParser();
		
			try {
			
				// URL di OpenWeather da Parsare
				URL OW = new URL ("api.openweathermap.org/data/2.5/forecast?q=" + this.cityName + 
				"," + this.stateCode + "&appid=" + "20fde6cbf830453bd9f2066b10f681ce");
				HttpsURLConnection yc = (HttpsURLConnection) OW.openConnection();
				yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			
				BufferedReader in = new BufferedReader (new InputStreamReader (yc.getInputStream()));
			
				String inputLine;
				
				// Creo un ciclo while che controlla che il mio contenuto di input non sia vuoto
				while ((inputLine = in.readLine()) != null) {
				
				
				
				// Analizzo l'oggetto city del JSON: Parse del contenuto dell'oggetto city
				JSONObject city = (JSONObject) parser.parse("city");
				
				// Analizzo l'oggetto list del JSON: Parse del contenuto dell'oggetto list
				JSONObject list = (JSONObject) parser.parse("list");
				
				// Analizzo l'array list del JSON: Parse del contenuto dell'array list
				JSONArray arr = (JSONArray) list.get("list");
				
				// Analizzo l'array weather (oggetto dell'array
				// list) del JSON: Parse del contenuto dell'array weather
				JSONArray arr2 = (JSONArray) list.get("weather");
				
				array = new ArrayList<City>();
				
				// Loop per ogni item dell'array list
				for (Object obj1 : arr) {
					
					if (obj1 instanceof JSONObject) {
					
						JSONObject op = (JSONObject) obj1;
					
						try {
						
							this.humidity = Double.parseDouble(op.get ("humidity").toString());
							this.temperature = Double.parseDouble(op.get("temp").toString());
							
							for (Object obj2 : arr2) {
								JSONObject op2 = (JSONObject) obj2;
								this.weather = (String) op2.get("description");
								}
						} catch (Exception e) {
						// Errore nel prelevamento dei parametri 
							e.printStackTrace();
						}
				
				
						this.cityName = (String) city.get("name");
						this.stateCode = (String) city.get ("country");
									
						try {
							
							/**
							 * Costruttore della classe Date di java.lang.Object che iniziailizza 
							 * il momento di inizio del metodo
							 * 
							 * @Deprecated : Alloca un oggetto Date e lo inizializza in modo che rappresenti la mezzanotte, 
							 * dell'ora locale, all'inizio del giorno specificato dagli argomenti anno, mese e data.
							 */
							@Deprecated
							Date firstTime = new Date(2020, 12, 1);
					
							/**
							 * Costruttore della classe Timer di java.lang.Object
							 */
							Timer timer = new Timer();
					
							/*
							 * Costruttore della classe TimerTask che effettua l'override del metodo run
							 * dell'interfaccia Runnable di java.lang.Object
							 */
							TimerTask task = new TimerTask() {

								/*
								 * Metodo che salva le informazioni delle città scelte in un arrayList
								 * Effettua l'override del metodo run() della classe TimerTask
								 */
								@Override
								public void run() {
									
									if ((cityName.equals("Ancona") & stateCode.equals("IT")) |
											cityName.equals("Londra") & stateCode.equals("GB")) 
										array = BuildingCity.Building (cityName, stateCode, humidity, temperature, weather);
								
								}
							 };
					
							/*
							 * Metodo che ripete il salvataggio ogni ora, a partire da una data scelta (firstTime)
							 */
							timer.scheduleAtFixedRate (task, firstTime, 3600000);
					
						} catch (IllegalArgumentException e) {
							// Viene lanciata se firstTime.getTime() < 0 o period <= 0
							e.printStackTrace();
						} catch (IllegalStateException e) {
							// Viene lanciata se l'attività era già pianificata o annullata,
							// il timer è stato annullato o il thread del timer è stato terminato
							e.printStackTrace();
						} catch (NullPointerException e) {
							// Viene lanciata se task o fistTime sono null
							e.printStackTrace();
					}
				
				}
			}
			in.close();
		
		JSONArray download = new JSONArray();
		download = (JSONArray) JSONValue.parseWithException(ParsingJSON.ParsingToJSON(array));
					
		return download;
			
		}
		
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		} catch (ParseException e) {
		e.printStackTrace();
		}
		
			return null;
	}
}
