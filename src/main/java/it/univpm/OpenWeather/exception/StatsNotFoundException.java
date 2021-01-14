package it.univpm.OpenWeather.exception;


/**
 * Classe di eccezione personalizzata per le statistiche
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class StatsNotFoundException extends OpenWeatherException{

	/**
	 * Costruttore
	 */
	public StatsNotFoundException () {
		super();
		System.out.println ("La statistisca non è esistente!");
	}
}
