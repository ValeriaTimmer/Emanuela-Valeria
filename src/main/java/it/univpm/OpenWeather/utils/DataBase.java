package it.univpm.OpenWeather.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.*;
import java.awt.*;
import javax.swing.*;

import it.univpm.OpenWeather.exception.UrlException;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.filter.*;

import java.util.ArrayList;





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
	 * Variabile della classe DownloadCity
	 */
	@Autowired (required = true)
	private DownloadCity d ;
	
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
		d = new DownloadCity();
		this.arr = d.getValues(Config.getName());
	
	}
	
	/**
	 * Metodo che salva ogni ora i dati di alcune città costantemente monitorate
	 * 
	 * Le città monitorate sono ROMA, LONDRA, BERLINO, PARIGI
	 * 
	 * @throws UrlException Eccezione personalizzata
	 * @throws ParseException Errore di Parsing
	 * @throws MalformedURLException Errore formato dell'Url
	 * @throws IOException Errore di I/O
	 */
	@Scheduled (cron = "0 0 0/1 * * ?") 
	public void addToDataBase() throws UrlException, ParseException, MalformedURLException, IOException {
		
		final Logger logger = LoggerFactory.getLogger(DataBase.class);
		
		if (Config.getCall()) {
					
				logger.info("Sto recuperando i dati delle città di Roma, Londra, Berlino e Parigi");
				d.saveValues(Config.getName(),"Roma");
				d.saveValues(Config.getName(), "Londra");
				d.saveValues(Config.getName(), "Berlino");
				d.saveValues(Config.getName(), "Parigi");
				
		}

	}
	
	/**
	 * Metodo che ritorna tutti i valori desiderati di una determinata città 
	 * presenti nel dataBase
	 * 
	 * @param city Città di cui si vogliono ottenere i parametri
	 * @return arr JSONArray che contiene tutti i dati della città
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 * @throws MalformedURLException Errore di formato dell'Url
	 * @throws UrlException Eccezione personalizzata
	 */
	public JSONArray getAllData (String city) throws UrlException, MalformedURLException, ParseException, IOException {
		
		cityFilter = new CityFilter();
		
		this.value = cityFilter.filtersCity(city);
	   
		return value;
	}
	
	/**
	 * metodo per salvare le previsioni attuali 
	 * @param file file che contiene le previsioni
	 * @param city nome della città 
	 * @throws IOException errore di I/O
	 * @throws ParseException errore di Parsing
	 */
	public void salvaData(String file, String city) throws IOException, ParseException {
		
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	    out.println(d.Parsing());
	    out.close();
	}
		
}
	


