package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;

/**
 * Interfaccia per le statistiche
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 * @param <T> parametro generico
 */
public abstract class Stats <T, P, Q> {
	/**
	 * array contenente le città
	 */
     protected JSONArray arrayCity;
    
    /**
     * costruttore
     * @param arrayCity
     */
     public Stats(JSONArray arrayCity) {
    	this.arrayCity = arrayCity;
     }
		/**
		 * Metodo astratto che viene implementato a seconda dell'esigenza
		 * @param arrayCity Array contenente le città
		 * @param parameter parametro(umidità/temperatura) sul quale si effettua la statistica
		 * @param from data d'inizio del periodo sul quale si effettuano le statistiche
		 * @param to data di fine del periodo sul quale si effettuano le statistiche
		 * @return ritorna l'array contenente le statistiche effettuate
		 */
		
	abstract JSONArray Statistics(JSONArray arrayCity, T parameter, P from, Q to);
		
}
