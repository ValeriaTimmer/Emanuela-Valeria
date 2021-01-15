package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

/**
 * Classe che si occupa di leggere i dati dall'API e di salvarli in un file 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 */
@Component
public class Parser {
	
	private JSONArray download;
	
	private String date;
	
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
	
	private JSONObject obj = new JSONObject();
	
	/**
	 * Descrizione del meteo della città
	 */
	private String weather;

	/**
	 * metodo getter del nome della città
	 * @return cityName nome della città
	 */
	public String getCityName() {
		return this.cityName;
	}
	
	/**
	 * metodo setter del nome della città
	 * @param name nome della città
	 */
	public void setCityName(String name) {
		this.cityName = name;
	}
	
	/**
	 * JSONObject su cui vengono salvati i dati
	 */
	private JSONObject jo = null;
	
	/**
	 * JSONArray che contiene i dati da leggere
	 */
	private JSONArray ja = null;
	
	private City c;
	
	/**
	 * costruttore
	 */
	public Parser () {
		this.jo = new JSONObject();
		this.ja = new JSONArray();
		this.c = new City();
	}
	
	/**
	 * metodo per convertire la temperatura da Kelvin in Celsius
	 */
	public int getTemperaturaInCelsius(double temperatura) {
		return (int)(temperatura - 273.15);
	}
	
    /**
     * metodo che effettua il collegamento con il sito dell'api di OpenWeather
     * @param cityName città di cui si vogliono ottenere le informazioni
     */
	public void chiamataAPI (String cityName) {
		
		JSONParser parser = new JSONParser();
		
		ArrayList<City> list = new ArrayList<City>();
			
		
		try {
			URLConnection openConnection = new URL ("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName +"&appid=" + Config.getApiKey()).openConnection();
			openConnection.addRequestProperty ( " User-Agent " , " Mozilla / 5.0 (Windows NT 6.1; WOW64; rv: 25.0) Gecko / 20100101 Firefox / 25.0 " );
			BufferedReader in = new BufferedReader (new InputStreamReader (openConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				
							
						JSONObject citta = (JSONObject) parser.parse("city");
						this.cityName = (String) citta.get("name");
						
						JSONObject main = (JSONObject) citta.get("main");
						this.humidity = Double.parseDouble(main.get("humidity").toString());
						Double temp = Double.parseDouble(main.get("temp").toString());
						this.temperature = this.getTemperaturaInCelsius(temp);

						JSONArray lista = (JSONArray) citta.get("list");
					     
								for(Object ob: lista) {
									
									if(ob instanceof JSONObject) {
									
										JSONObject op = (JSONObject) ob;
										
										String date = (String) op.get("dt_txt");
										this.date = DateUtils.formatoData.format(date);
										
										JSONArray weather = (JSONArray) op.get("weather");
									
										for(Object o1: weather){
											
										  if(o1 instanceof JSONObject) {
											  JSONObject op2 = (JSONObject)o1;
											  this.weather = (String) op2.get("description");
										  }
									  }
								  }
							  }
							}
			list = BuildingCity.Building(this.cityName, this.humidity, this.temperature, this.weather);
			this.download = (JSONArray) JSONValue.parseWithException(ParsingJSON.ParsingToJSON(list));
			obj.put ("date", this.date);
			download.add(obj);
		
		} catch (MalformedURLException e ) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
    /**
     * metodo per salvare i dati presi dalla chiamata al sito in un file
     * @param nome_file nome del file su cui si vanno a salvare i dati
     */
	public void salvaFile (String nome_file, String cityName) {
		this.chiamataAPI(cityName);
		try {
			FileWriter file_output = new FileWriter (nome_file,true);
			file_output.write(this.ja.toJSONString());
			file_output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * metodo per leggere i dati contenuti nel file
     * @param nome_file nome del file contenente i dati da leggere
     * @return ja JSONArray contenente i dati letti
     */
	public JSONArray caricaFile (String nome_file) {
		
		try {
			Scanner file_input = new Scanner (new BufferedReader (new FileReader (nome_file)));
			FileReader reader = new FileReader (nome_file);
			String str = file_input.nextLine();
			this.ja = (JSONArray) JSONValue.parseWithException(str);

		} catch (IOException  e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.ja;
	}

}
