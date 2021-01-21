package it.univpm.OpenWeather.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe che riguarda metodi statici 
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public class Config {
	
	/**
	 * Nome del file su cui vengono salvati i nuovi dati 
	 */
	private static String fileName = "parsing.json";
	
	/**
	 * Nome del file che contiene i dati storici
	 */
	private static String fileStorico = "fileValue.json";
	
	/**
	 * Chiave privata per accedere al sito di OpenWeather
	 */
	private static String apiKey = "bed1a816d94554cecab782b0804bec47" ;
	
	/**
	 * nome della prima citta
	 */
	private static String city1 = "Roma";
	
	/**
	 * nome della seconda citta
	 */
	private static String city2 = "Londra";
	
	/**
	 * nome della terza citta
	 */
	private static String city3 = "Berlino";
	
	/**
	 * nome della quarta citta
	 */
	private static String city4 = "Parigi";
	/**
	 * Variabile Booleana che riguarda le chiamate dello Scheduled
	 * Se call = true allora lo Scheduled effettua le chiamate al sito di OpenWeather
	 * Se call = false allora lo Scheduled non effettua le chiamate al sito di OpenWeather
	 */
	private static Boolean call = true;
	
	/**
	 * Formato data desiderato
	 */
	private static String DATE_FORMAT_O = "yyyy-MM-dd";
	
	/**
	 *  DateFormat per il parsing della data 
	 */
	public static DateFormat formatoData = new SimpleDateFormat(Config.getDateFormat());
	
	/**
	 * Metodo Getter del nome del file
	 * @return fileName  Ritorna il nome del file
	 */
	public static String getName() {
		return fileName;
	}
	
	/**
	 * Metodo gettere del nome del file
	 * che contiene lo storico dei dati
	 * @return fileStorico Ritorna il nome del file
	 */
	public static String getNameStorico() {
		return fileStorico;
	}
	
	/**
	 * Metodo Setter del nome del file
	 * @param name Nome del file
	 */
	private static void setName (String name) {
		Config.fileName = name;
	}
	
	/**
	 * Metodo Setter del nome del file
	 * che contiene lo storico dei dati
	 * @param name Nome del file
	 */
	private static void setNameStorico(String name) {
		Config.fileStorico = name;
	}
	
	/**
	 * metodo getter della prima citta
	 * @return city1
	 */
	public static String getCity1() {
		return city1;
	}
	
	/**
	 * metodo getter della seconda citta
	 * @return city2
	 */
	public static String getCity2() {
		return city2;
	}
	
	/**
	 * metodo getter della terza citta
	 * @return city3
	 */
	public static String getCity3() {
		return city3;
	}
	
	/**
	 * metodo gettere della quarta citta
	 * @return city4
	 */
	public static String getCity4() {
		return city4;
	}
	/**
	 * Metodo Getter della chiave API
	 * @return apiKey Valore della chiave privata
	 */
	public static String getApiKey () {
		return apiKey;
	}
	
	/**
	 * Metod Setter della chiave API
	 * @param api Valore della chiave privata
	 */
	public static void setApiKey (String api) {
		Config.apiKey = api;
	}
	
	/**
	 * Metodo Getter del valore booleano utilizzato dallo Scheduled
	 * @return call Valore della variabile booleana
	 */
	public static Boolean getCall() {
		return call;
	}
	
	/**
	 * Metodo Setter del valore booleano utilizzato dallo Scheduled
	 * @param value Valore che deve assumere la variabile (true/false)
	 */
	public static void setCall(Boolean value) {
		Config.call = value;
	}
	
	/**
	 * Metodo Getter del formato Data
	 * @return DATE_FORMAT_O Formato data di Output
	 */
	public static String getDateFormat() {
		return DATE_FORMAT_O;
	}
	
	/**
	 * Metodo Settere del formato Data
	 * @param dateFormat Formato data 
	 */
	public static void setDateFormat(String dateFormat) {
		Config.DATE_FORMAT_O = dateFormat;
	}
	
}
