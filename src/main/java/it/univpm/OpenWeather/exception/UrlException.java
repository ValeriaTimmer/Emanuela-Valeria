package it.univpm.OpenWeather.exception;

public class UrlException extends Exception {
	
	/**
	 * Numero che viene utilizzato durante la deserializzazione per verificare che il mittente 
	 * e il destinatario di un oggetto serializzato abbiano caricato classi per quell'oggetto 
	 * compatibili rispetto alla serializzazione. Se il destinatario ha caricato una classe 
	 * per l'oggetto che ha una diversa serialVersionUIDda quella della corrispondente classe 
	 * del mittente, la deserializzazione si tradurrà in un InvalidClassException 
	 */
	private static final long serialVersionUID = 1L;
	
	public UrlException() {
		super();
		System.out.println ("Errore di connesione al sito");
	}

}
