package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;

/**
 * Interfaccia per le statistiche
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 * @param <T> parametro generico
 */
public interface Stats <T, P> {
		
		/**
		 * Metodo astratto che viene implementato a seconda dell'esigenza
		 * @param arrayCity Array contenente le città
		 * @param humidity Umidità su quale si effettua la statistica
		 * @param period Periodo sul quale si vuole effettuare la statistica
		 * @return ritorna l'array contenente le statistiche effettuate sull'umidità
		 */
		
	abstract JSONArray StatisticsHumidity(JSONArray arrayCity, T humidity, P period);
		
}
