package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.statistics.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.utils.*;

import java.net.MalformedURLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.time.LocalDate;
import java.text.ParseException;
import java.io.*;
import org.json.simple.JSONValue;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Classe che implementa l'interfaccia
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
@Service
public class CityServiceImpl implements CityService {
	
	private Stats s;
	
	private Forecasts f;
	
	private JSONObject obj = new JSONObject();
	
	/**
	 * Variabile della classe DownloadCity
	 */
	private City c;
	
	private JSONArray array;
	
	private DataBase dB = new DataBase();
	
	
	
	/**
	 * Costruttore
	 */
	public CityServiceImpl() throws UrlException, MalformedURLException, IOException, org.json.simple.parser.ParseException,
	ClassNotFoundException {
	}
	
	/**
	 * Variabile della classe Stats
	 */
	//private Stats s = new Stats();
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return c HashMap contenente i metadata
	 */
	@Override
	public HashMap <String, String > getMetadata(){
		HashMap <String, String> c = new HashMap <String,String>();
		c.put("city", "Contiene il nome della città");
		c.put("country", "Contiene la sigla dello stato corrispondente alla città");
		c.put("humidity", "Contiene il valore dell'umidità in percentuale");
		c.put("temp", "Contiene il valore della temperatura in Celsius");
		c.put("from", "Data iniziale del periodo scelto");
		c.put("to", "Data finale del periodo scelto");
		c.put("min", "Contiene il valore minimo dell'umidità o della temperatura in base al periodo scelto");
		c.put("max", "Contiene il valore massimo dell'umidità o della temperatura in base al periodo scelto");
		c.put("avg", "Contiene il valore medio dell'umidità o della temperatura in base al periodo scelto");
		c.put("var", "Contiene il valore della varianza dell'umidità o della temperatura in base al periodo scelto");
		return c;
	}
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return data HashMap contenete i dati relativi ad una città
	 */
	@Override 
	public JSONArray getData(String city) throws IllegalArgumentException, UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException { 
		JSONArray data = new JSONArray();
		Parser p = new Parser();
		DownloadCity d = new DownloadCity();
		c = new City (city);
		DataBase db = new DataBase();
		//data = dB.getAllData(city);
		data = d.Parsing();
		return data;
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return JSONArray contenente le statistiche filtrate
	 * @throws org.json.simple.parser.ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Override 
	public HashMap <String, String> getStats (String city, String type, String from, String to ) 
			throws UrlException, ClassNotFoundException,
	 ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		this.c = new City(city);
		
		DataBase dB = new DataBase ();
		
		Parser p = new Parser();
		
		//this.array = p.caricaFile(Config.getName());
	
		this.s = new Stats(dB.getAllData(c.getCityName()));

		return  s.Statistics(s.getArray(), c.getCityName(), type, from, to);
		
	}
	
	//@Override
	//public HashMap <String, String> getForecasts (String city, String state){
		//return ;
	//}

}
