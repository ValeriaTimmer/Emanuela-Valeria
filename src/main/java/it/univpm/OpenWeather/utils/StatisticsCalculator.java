package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.exception.*;

import java.util.ArrayList;

import java.lang.Math;

/**
 * Classe che effettua i calcoli per le statistiche
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 * 
 */

public class StatisticsCalculator {
	
	/**
     * Valore minimo
     */
	private Double min;
	
	/**
	 * Valore massimo
	 */
	private Double max;
	
	/**
	 * Somma
	 */
	private Double sum;
	
	/**
	 * Costruttore
	 *
	 */
	public StatisticsCalculator() {
		this.max = -Double.MAX_VALUE;
		this.min = Double.MAX_VALUE;
		this.sum = 0.0;
	}
	
	/**
	 * Metodo Getter del valore minimo
	 * @param a ArrayList che contiene tutti i dati su cui calcolare il minimo
	 * @return min Ritorna il valore minimo
	 */
	public Double getMin (ArrayList <Double> a) throws StatsException {
		
		for (int i = 0; i < a.size(); i++)
			
			if (a.get(i) < min)
				
				min = a.get(i);
		
		if(min == Double.MAX_VALUE)throw new StatsException("Non sono stati prelevati dati!");
		
		return min;
	}
	
	/**
	 * Metodo Getter del valore massimo
	 * @param a ArrayList che contiene tutti i dati su cui calcolare il massimo
	 * @return max Ritorna il valore massimo
	 */
	public Double getMax(ArrayList <Double> a)  throws StatsException{
		
		for (int i = 0; i< a.size(); i++)
			
			if (a.get(i) > max)
				
				max = a.get(i);
		
		if(max == -Double.MAX_VALUE) throw new StatsException("Non sono stati prelevati dati!");
		
		return max;
	}
	
	/**
	 * Metodo Getter per il calcolo della media
	 * (I valori restituiti vengono arrotondati alla seconda cifra significativa)
	 * @param a ArrayList che contiene tutti i dati su cui calcolare la media
	 * @return valore Double della media
	 */
	public Double getAverage (ArrayList <Double> a) throws StatsException {
		
		for (int i = 0; i < a.size(); i++)
			
			sum += a.get(i);
		
		return (double) Math.round((sum/a.size())*100d)/100d;
	}
	
	/**
	 * Metodo Getter per il calcolo della varianza
	 * (I valori restituiti vengono arrotondati alla seconda cifra significativa)
	 * @param a ArrayList che contiene tutti i dati su cui calcolare la varianza
	 * @return valore Double della varianza
	 */
	public Double getVariance (ArrayList <Double> a) throws StatsException {
		
		Double avg = getAverage(a);
		
		Double eps = 0.0;
		
		for (int i = 0; i < a.size(); i++)
			
			eps += (a.get(i) - avg) * (a.get(i) - avg);
		
		return (double) Math.round((eps/a.size())*100d)/100d;
	}
	
}
