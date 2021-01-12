package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.util.Scanner;

import org.json.simple.JSONValue;
//import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.Number;
import java.net.URL;
import java.net.URLConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

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
	
	private City c;
	
	private JSONObject obj;
	
	private JSONObject main;
	
	private JSONArray download;
	
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
	private static String apiKey = "bed1a816d94554cecab782b0804bec47";
	
	//private String path = "‪C://Users//Emanuela//file.json.txt";
	
	/**
	 * File su cui vengono salvati i dati
	 */
	File file = new File ("file.json");

	private JSONArray list;
	
	
	/**
	 * Costruttore che prende in ingresso il nome della città e il suo stato/paese
	 * 
	 * @param Name Nome della città
	 * @param Code Sigla dello Stato/Paese della città
	 */
	public DownloadCity (String Name, String Code) {
		this.cityName = Name;
		this.stateCode = Code;
		this.obj = new JSONObject();
	}
	
	/**
	 * costruttore senza parametri
	 */
	public DownloadCity() {
	}
	
	/**
	 * Metodo Getter statico dell'apiKey
	 * @return apiKey Valore della chiave api
	 */
	
	private static String getApiKey () {
		return apiKey;
	}
	
	public File getFile() {
		return this.file;
	}
	
	public String getCityName () {
		return cityName;
	}
	
	public String getStateCode () {
		return stateCode;
	}
	
	public double getHumidity () {
		return humidity;
	}
	
	public void setHumidity(double humidity){
		this.humidity = humidity;
	}
	
	public JSONObject getArray() {
		return obj;
	}
	
	public void setArray(JSONObject obj) {
		this.obj = obj;
	}
	
	public Double getTemperature () {
		return temperature;
	}
	
	public String getWeather () {
		return weather;
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
								salvaFile(getFile());
							} catch (UrlException e) {
								e.printStackTrace();
							} catch (ParseException e) {
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
	 * @throws ParseException 
	 */
	@Bean
	public void salvaFile (File file) throws UrlException, ParseException {
		try {
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			file_output.println(chiamataAPI());
			file_output.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che permette di leggere i dati da un file
	 * 
	 * @param nome_file Nome del file da cui leggere il JSONArray
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	
	public JSONArray caricaFile (File file) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException {
		  file = getFile();
		  BufferedReader input_file = new BufferedReader(new FileReader(file));
		  String str = input_file.readLine();
		  this.obj = (JSONObject) JSONValue.parseWithException(str);
		  JSONObject citta = (JSONObject) obj.get("city");
		  this.cityName = (String) citta.get("name");
		  this.stateCode = (String) citta.get("country");
		  JSONArray lista = (JSONArray) obj.get("list");
         
		  
		  for(Object ob: lista)
		  {
			  if(ob instanceof JSONObject)
			  {
				  JSONObject op = (JSONObject) ob;
				  JSONObject main = (JSONObject) op.get("main");
				  //String hum = (String) main.get("humidity");
				  //String temp = (String) main.get("temp");
				  this.humidity = Double.parseDouble(main.get("humidity").toString());
				  this.temperature = Double.parseDouble(main.get("temp").toString());
				  JSONArray weather = (JSONArray) op.get("weather");
				  for(Object o: weather)
				  {
					  if(o instanceof JSONObject)
					  {
						  JSONObject op2 = (JSONObject)o;
						  this.weather = (String) op2.get("description");
					  }
				  }
			  }
		  }
		  input_file.close();
		  JSONObject dati = new JSONObject();
		  dati.put("citta", this.getCityName());
		  dati.put("country",this.getStateCode());
		  dati.put("humidity",this.humidity);
		  dati.put("temperature", this.temperature);
		  dati.put("tempo", this.weather);
		  this.download.add(dati);
		  return this.download;
	}
	
	
	/**
	 * Metodo che effettua il Parsing dal sito di OpenWeather
	 * 
	 * @return JSONArray contenete i dati desiderati
	 * @throws UrlException Eccezione personalizzata
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public JSONObject chiamataAPI() throws UrlException, ParseException, MalformedURLException, IOException {
		
			URLConnection openConnection = new URL("https://api.openweathermap.org/data/2.5/forecast?q=" + getCityName() + "," + getStateCode() + "&appid=" + getApiKey() ).openConnection();
			InputStream in = openConnection.getInputStream();
			
			String informazioni = "";
			String inputLine = "";
			
			try {
				InputStreamReader inR = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( inR );
				
				while(( inputLine = buf.readLine())!=null){
					informazioni += inputLine;
				}
			}finally {
				in.close();
			}
					this.obj = (JSONObject) JSONValue.parseWithException(informazioni);
					/*
					JSONObject citta = (JSONObject) obj.get("city");
					  this.cityName = (String) citta.get("name");
					  this.stateCode = (String) citta.get("country");
					  JSONArray lista = (JSONArray) obj.get("list");
                     
					  
					  for(Object ob: lista)
					  {
						  if(ob instanceof JSONObject)
						  {
							  this.main = (JSONObject) ob;
							  Iterator<Integer> iter = this.main.keySet().iterator();
							  while(iter.hasNext()) { 
								  this.humidity = (Integer) main.get("humidity");
							  }
						  }
					  }
				}
			}finally {
				in.close();
			}
					  
					  JSONObject dati = new JSONObject();
					  dati.put("citta", this.cityName);
					  dati.put("country",this.stateCode);
					  dati.put("humidity",this.humidity);
					  this.download.add(dati);
				
			return download;
			//this.obj = (JSONObject) JSONValue.parseWithException(informazioni);
			 * */
			 
			return obj;
		/*
     try {
			
			try {
		
				JSONParser parser = new JSONParser();
		
				URL OW = new URL ("https://api.openweathermap.org/data/2.5/forecast?q=Ancona,IT&appid=bed1a816d94554cecab782b0804bec47");
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
						Integer humidity = (Integer) main.get("humidity");
							//temperature = (Double) main.get("temp");
						//JSONObject w = (JSONObject) main.get("weather");
							//weather = (String) w.get("description");
						this.setHumidity(humidity);
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
			
			JSONArray download = new JSONArray();
			download = (JSONArray) ;
	        return download;
	        
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	*/
	}
}

