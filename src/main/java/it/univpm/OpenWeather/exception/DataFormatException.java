package it.univpm.OpenWeather.exception;


/**
 * Classe che gestisce l'eccezione che segnala che si è
 * verificato un errore di formato dati
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 * 
 */
public class DataFormatException extends OpenWeatherException {
	/**
	 * Costruttore
	 */
	public DataFormatException() {
		super();
		System.out.println("Formato dati sbagliato");
	}
	
}
