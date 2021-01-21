package it.univpm.OpenWeather.exception;

/**
 * Classe di eccezione personalizzata per le statistiche
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class StatsException extends Exception{

	/**
	 * Numero che viene utilizzato durante la deserializzazione per verificare che il mittente 
	 * e il destinatario di un oggetto serializzato abbiano caricato classi per quell'oggetto 
	 * compatibili rispetto alla serializzazione. Se il destinatario ha caricato una classe 
	 * per l'oggetto che ha una diversa serialVersionUIDda quella della corrispondente classe 
	 * del mittente, la deserializzazione si tradurra in un InvalidClassException 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore
	 */
	public StatsException (String message) {
		super(message);
	}
	
}
