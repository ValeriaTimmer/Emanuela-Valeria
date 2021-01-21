package it.univpm.OpenWeather.utils;

import java.io.IOException;
import org.json.simple.JSONArray;

import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.*;

import it.univpm.OpenWeather.exception.UrlException;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.filter.*;



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
	 * Variabile della classe DownloadCity;
	 */
	@Autowired (required = true)
	private DownloadCity d ;
	
	/**
	 * JSONArray che contiene i dati desiderati
	 */
	private JSONArray value;
	
	/**
	 * Variabile della classe CityFilter;
	 */
	private CityFilter cityFilter;
	
	/**
	 * JSONArray
	 */
	private JSONArray arr;
	
	/**
	 * Costruttore
	 * 
	 * @throws UrlException Eccezione personalizzata
	 * @throws ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 */
	public DataBase () throws UrlException, ParseException, IOException {
		d = new DownloadCity();
		this.arr = d.getValues(Config.getName());
	
	}
	
	/**
	 * Metodo che salva ogni ora i dati di alcune citta costantemente monitorate
	 * 
	 * Le citta monitorate sono ROMA, LONDRA, BERLINO, PARIGI
	 * (i nomi delle citta possono essere modificati nella classe
	 * Config)
	 * 
	 * @throws UrlException Eccezione personalizzata
	 * @throws ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 */
	@Scheduled (cron = "0 0 0/1 * * ?") 
	public void addToDataBase() throws UrlException, ParseException, IOException {
		
		final Logger logger = LoggerFactory.getLogger(DataBase.class);
		
		if (Config.getCall()) {
					
			logger.info("Sto recuperando i dati delle città di " + Config.getCity1() + " , " + Config.getCity2() + " , " 
			+ Config.getCity3() +  " , " + Config.getCity4());
			d.saveValues(Config.getName(),Config.getCity1());
			d.saveValues(Config.getName(),Config.getCity2());
			d.saveValues(Config.getName(),Config.getCity3());
			d.saveValues(Config.getName(),Config.getCity4());
				
		}

	}
	
	/**
	 * Metodo che ritorna tutti i valori desiderati di una determinata citta 
	 * presenti nello storico del DataBase
	 * 
	 * @param city Citta di cui si vogliono ottenere i parametri
	 * @return arr JSONArray che contiene tutti i dati della citta
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 * @throws UrlException Eccezione personalizzata
	 */
	public JSONArray getAllDataStorico (String city) throws UrlException, ParseException, IOException {
		
		cityFilter = new CityFilter(Config.getNameStorico());
		
		this.value = cityFilter.filtersCity(city, Config.getNameStorico());
	   
		return value;
	}
	
	/**
	 * Metodo che ritorna tutti i valori attuali desiderati di una determinata citta 
	 * 
	 * 
	 * @param city Citta di cui si vogliono ottenere i parametri
	 * @return arr JSONArray che contiene tutti i dati della citta
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 * @throws UrlException Eccezione personalizzata
	 */
    public JSONArray getAllData(String city) throws UrlException, ParseException, IOException {
		
		cityFilter = new CityFilter(Config.getName());
		
		this.value = cityFilter.filtersCity(city, Config.getName());
	   
		return value;
	}
		
}
	


