package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.utils.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
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
	private static String apiKey = "f1c4f31f5fec01d08254bfc5d2b0a250";
	
	/**
	 * ArrayList che viene popolato con i dati desiderati
	 */
	private ArrayList <City> array;
	
	/**
	 * JSONArray con i dati parsati
	 */
	private JSONArray download;
	
	private String fileName = "file.json";
	
	/**
	 * File su cui vengono salvati i dati
	 */
	File file = new File (fileName);
	
	public String getFileName() {
		return this.fileName;
	}
	
	/**
	 * Metodo Getter statico dell'apiKey
	 * @return apiKey Valore della chiave api
	 */
	private static String getApiKey () {
		return apiKey;
	}
	
	public String getCityName () {
		return cityName;
	}
	
	public String getStateCode () {
		return stateCode;
	}
	
	public Double getHumidity () {
		return humidity;
	}
	
	public Double getTemperature () {
		return temperature;
	}
	
	public String getWeather () {
		return weather;
	}
	
	/**
	 * Costruttore che prende in ingresso il nome della città e il suo stato/paese
	 * 
	 * @param Name Nome della città
	 * @param Code Sigla dello Stato/Paese della città
	 */
	public DownloadCity (String Name, String Code) {
		this.cityName = Name;
		this.stateCode = Code;
	}
	
	/**
	 * Costruttore senza parametri
	 */
	public DownloadCity() {
		this.download = new JSONArray();
		this.array = new ArrayList<City>();
	}
	
	/**
	 * Metodo che permette di salvare i dati ogni ora
	 */
	@Bean
	public void ScheduledAtFixedRate() throws UrlException {
				try {			

					/**
					 * Costruttore della classe Date di java.lang.Object che iniziailizza 
					 * il momento di inizio del metodo
					 * 
					 * @Deprecated : Alloca un oggetto Date e lo inizializza in modo che rappresenti la mezzanotte, 
					 * dell'ora locale, all'inizio del giorno specificato dagli argomenti anno, mese e data.
					 */
					Date firstTime = new Date(121, 1, 8);
					
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
					public void run(){ 
							try {
								salvaFile(file.toString());
							} catch (UrlException e) {
								e.printStackTrace();
							}
						}
					};
					
					/*
					* Metodo che ripete il salvataggio ogni ora, a partire da una data scelta (firstTime)
					 */
					timer.scheduleAtFixedRate (task, firstTime, 360000);
					
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
	
	/**
	 * Metodo che permette di salvare un JSONArray in un file binario
	 * 
	 * @param nome_file Nome del file su cui voglio salvare il JSONArray
	 */
	@Bean
	public void salvaFile (String nome_file) throws UrlException {
		try {
		ObjectOutputStream file_output = new ObjectOutputStream (new BufferedOutputStream (new FileOutputStream (nome_file)));
		file_output.writeObject(OpenWeatherParser()); //Scrive il JSONArray Parsato
		file_output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che permette di leggere i dati da un file
	 * 
	 * @param nome_file Nome del file da cui leggere il JSONArray
	 */
	@Bean 
	public JSONArray caricaFile (String nome_file) {
		try {
			ObjectInputStream file_input = new ObjectInputStream (new BufferedInputStream (new FileInputStream(nome_file)));
			this.download = (JSONArray) file_input.readObject();
			return this.download;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo che effettua il Parsing dal sito di OpenWeather
	 * 
	 * @return JSONArray contenete i dati desiderati
	 * @throws UrlException Eccezione personalizzata
	 */
	public JSONArray OpenWeatherParser() throws UrlException {
		
		try {
			
			try {
		
				JSONParser parser = new JSONParser();
		
				URL OW = new URL ("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName +"," + stateCode +"&appid=" + DownloadCity.getApiKey());
				HttpsURLConnection yc = (HttpsURLConnection) OW.openConnection();
				yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
					
				BufferedReader in = new BufferedReader (new InputStreamReader (yc.getInputStream()));
				String inputLine;
		
			
				while ((inputLine = in.readLine()) != null) {
						
					JSONObject city = (JSONObject) parser.parse("city");
					JSONObject list = (JSONObject) parser.parse("list");
					
					JSONArray l = (JSONArray) list.get("list");
						
					for (Object obj : l) {
						
						JSONObject op = (JSONObject) obj;
						JSONObject main = (JSONObject) op.get("main");
							humidity = (Double) main.get("humidity");
							temperature = (Double) main.get("temp");
						JSONObject w = (JSONObject) main.get("weather");
							weather = (String) w.get("description");
						
					}
						
						
					cityName = (String) city.get("name");
					stateCode = (String) city.get("country");
					
					in.close();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();		
			} catch (Exception e) {
				e.printStackTrace();
			}
					
			array = BuildingCity.Building(getCityName(), getStateCode(), getHumidity(), getTemperature(), getWeather());
			
			this.download = (JSONArray) JSONValue.parseWithException(ParsingJSON.ParsingToJSON(this.array));
	        return download;
	        
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
}

