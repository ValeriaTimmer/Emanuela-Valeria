package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;

/**
 * Interfaccia per le statistiche
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 * @param <T> parametro generico
 */
public interface Stats <T> {
		/**
		 * metodo astratto che viene implementato a seconda dell'esigenza
		 * @param ArrayCity array contenente le città
		 * @param Humidity umidità su quale si effettua la statistica
		 * @return ritorna l'array contenente le statistiche effettuate sull'umidità
		 */
		abstract JSONArray StatisticsHumidity(JSONArray arrayCity, T humidity);
		
}
