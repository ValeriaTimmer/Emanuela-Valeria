package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;

/**
 * Interfaccia per le statistiche
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 * @param <T> parametro generico
 */
public abstract class Stats <T, P> {
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
		 * @param period Periodo sul quale si vuole effettuare la statistica
		 * @return ritorna l'array contenente le statistiche effettuate
		 */
		
	abstract JSONArray Statistics(JSONArray arrayCity, T parameter, P from);
		
}
