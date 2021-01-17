package it.univpm.OpenWeather.utils;


/**
 * Classe che riguarda metodi statici 
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public class Config {
	
	/**
	 * Nome del file su cui vengono salvati i dati 
	 */
	private static String fileName = "parsing.json";
	/**
	 * Chiave privata per accedere al sito di OpenWeather
	 */
	private static String apiKey = "bed1a816d94554cecab782b0804bec47" ;
	
	/**
	 * Variabile Booleana che riguarda le chiamate dello @Scheduled
	 * Se call = true allora lo @Scheduled effettua le chiamate al sito di OpenWeather
	 * Se call = false allora lo @Scheduled non effettua le chiamate al sito di OpenWeather
	 */
	private static Boolean call = true;
	
	/**
	 * Metodo Getter del nome del file
	 * @return fileName  Ritorna il nome del file
	 */
	public static String getName() {
		return fileName;
	}
	
	
	/**
	 * Metodo Setter del nome del file
	 * @param name Nome del file
	 */
	private static void setName (String name) {
		Config.fileName = name;
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
	 * Metodo Getter del valore booleano utilizzato dallo @Scheduled
	 * @return call Valore della variabile booleana
	 */
	public static Boolean getCall() {
		return call;
	}
	
	/**
	 * Metodo Setter del valore booleano utilizzato dallo @Scheduled
	 * @param value Valore che deve assumere la variabile (true/false)
	 */
	public static void setCall(Boolean value) {
		Config.call = value;
	}

	
}
