package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Iterator;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;

import org.json.simple.JSONValue;
//import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.lang.reflect.Array;
import java.lang.Number;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.StandardOpenOption;
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
 */
 

@Component
public class DownloadCity {
	
	private Parser p = new Parser();
	
	private String date;
	
	private JSONObject obj = new JSONObject();
	
	private JSONObject jsonOb = new JSONObject();
	
	private JSONArray download;
	
	private JSONArray value;
	
	/*
	 * Nome della città
	 */
	private String cityName;
	
	/**
	 * Umidità della città in percentuale
	 */
	private double humidity;
	
	/**
	 * Temperatura della città in kelvin
	 */
	private double temperature;
	
	
	/**
	 * Costruttore che prende in ingresso il nome della città e il suo stato/paese
	 * 
	 * @param Name Nome della città
	 * @param Code Sigla dello Stato/Paese della città
	 */
	public DownloadCity (String Name) {
		this.cityName = Name;
		this.obj = new JSONObject();
		this.p = new Parser();
	}
	
	/**
	 * costruttore senza parametri
	 */
	public DownloadCity() {
	}
	
	
	public String getCityName () {
		return cityName;
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
	

	/**
	 * metodo per convertire la temperatura da Kelvin in Celsius
	 */
	public int getTemperaturaInCelsius(double temperatura) {
		return (int)(temperatura - 273.15);
	}

	/**
	 * Metodo che effettua il Parsing dei dati desiderati dal sito di OpenWeather
	 * 
	 * @return JSONObject contenete i dati parsati
	 * @throws UrlException Eccezione personalizzata
	 * @throws IOException Errore di I/O
	 * @throws MalformedURLException 
	 
	public ArrayList<Object> Parsing (String nome_file) throws UrlException, ParseException, MalformedURLException, IOException {
		
		JSONParser parser = new JSONParser();
		
		ArrayList<Object> list = new ArrayList<Object>();
			
		this.download = p.caricaFile(Config.getName());
		
		for (Object o : this.download) {
			
					if(o instanceof JSONObject) {
			
				       JSONObject obj = (JSONObject) o;
					
				       this.cityName = (String) obj.get("city");
					   this.date = (String) obj.get("date");
					   this.humidity = Double.parseDouble(obj.get("humidity").toString());
					   this.temperature = Double.parseDouble(obj.get("temperature").toString());
					}
				}
			
		list = BuildingCity.Building(this.cityName, this.humidity, this.temperature, this.date);
		//this.download = (JSONArray) JSONValue.parseWithException(ParsingJSON.ParsingToJSONObject(list));
		//return this.download;
		return list;
	}
	*/
}