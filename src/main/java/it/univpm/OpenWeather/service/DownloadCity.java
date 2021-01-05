package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.utils.*;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Classe che serve per parsare il JSON ricevuto dall'API e popola i campi
 * Humidity, Temperature, Weather
 * I campi cityName e stateCode vengono passati come argomento
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
@Configuration
public class DownloadCity {
	
	/*
	 * Stringa con Url da parsare
	 */
	public static String url = "https://api.openweathermap.org/data/2.5/forecast?q=Ancona,IT&appid=bed1a816d94554cecab782b0804bec47";
	
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
	private static String apiKey;
	
	/**
	 * ArrayList che viene popolato con i dati desiderati
	 */
	private ArrayList <City> array;
	
	/**
	 * JSONArray con i dati parsati
	 */
	private JSONArray download;
	
	private static String getApiKey () {
		return apiKey;
	}
	
	
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
	@Bean
	public JSONArray Parser() throws UrlException {
		
		try {
				try {			
					/**
					 * Costruttore della classe Date di java.lang.Object che iniziailizza 
					 * il momento di inizio del metodo
					 * 
					 * @Deprecated : Alloca un oggetto Date e lo inizializza in modo che rappresenti la mezzanotte, 
					 * dell'ora locale, all'inizio del giorno specificato dagli argomenti anno, mese e data.
					 */
					@Deprecated
					Date firstTime = new Date(2021, 01, 01);
					
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
							try {
								
								// URL di OpenWeather da Parsare
								URL OW = new URL (url);
								HttpsURLConnection yc = (HttpsURLConnection) OW.openConnection();
								yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
							
								BufferedReader in = new BufferedReader (new InputStreamReader (yc.getInputStream()));
							    
								String inputLine;
								
								// Criclo while che controlla che il contenuto di input non sia vuoto
								while ((inputLine = in.readLine()) != null) {  
									
									JSONParser parser = new JSONParser();
								
									JSONObject city = (JSONObject) parser.parse("city");
								
									JSONArray list = (JSONArray) parser.parse("list");
												
									array = new ArrayList <City>();
								
									for (Object obj1 : list) {
									
										if (obj1 instanceof JSONObject) {
									
											JSONObject op = (JSONObject) obj1;
									
											try {
										    
												JSONObject main = (JSONObject) op.get("main");
											
												Double hum = (Double) main.get ("humidity");
												humidity = hum;
												
												Double temp = (Double) main.get("temp");
												temperature = temp;
											
												JSONObject w = (JSONObject) op.get("weather");
											
												String description = (String) w.get("description");
												weather = description;
										
												} catch (Exception e) {
													// Errore nel prelevamento dei parametri 
													e.printStackTrace();
												}
										}
									}
									    
									try {
									
										String name = (String) city.get("name");
										cityName = name;
										
										String country = (String) city.get ("country");
										stateCode = country;
									   
									} catch(Exception e) {
										e.printStackTrace();
									}
						
									array = BuildingCity.Building (cityName, stateCode, humidity, temperature, weather);
							
									in.close();
								}
							
							} catch (FileNotFoundException e) {
						    	e.printStackTrace();
						    } catch (IOException e) {
						    	System.out.println ("Errore di I/O");
							  	System.out.println ("Messaggio: " + e.getMessage());
							  	System.out.println ("Causa: " + e.getCause());
						    } catch (ParseException e) {
						    	System.out.println ("Errore di Parsing:");
								System.out.println ("Messaggio: " + e.getMessage());
								System.out.println ("Causa: " + e.getCause());
						    }
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
							System.out.println ("Errore Scheduler");
							System.out.println ("Messaggio: " + e.getMessage());
							System.out.println ("Causa: " + e.getCause());
					} catch (NullPointerException e) {
							// Viene lanciata se task o fistTime sono null
							e.printStackTrace();
					}
				
		        this.download = (JSONArray) JSONValue.parseWithException(ParsingJSON.ParsingToJSON(array));
		        return download;
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}			
}
