package it.univpm.OpenWeather.utils;

import java.util.ArrayList;
import java.lang.Math;

/**
 * Classe che calcola le statistiche
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 * 
 */

public class StatisticsCalculator {
	/**
     * Valore minimo
     */
	private static double min;
	/**
	 * Valore massimo
	 */
	private static double max;
	/**
	 * Somma
	 */
	private static double sum;
	
	/**
	 * Costruttore
	 *
	 */
	public StatisticsCalculator() {
		max = -Double.MAX_VALUE;
		min = Double.MAX_VALUE;
		sum = 0.0;
	}
	
	/**
	 * Metodo Getter del valore minimo
	 * @param a ArrayList che contiene tutti i dati su cui calcolare il minimo
	 * @return min Ritorna il valore minimo
	 */
	public Double getMin (ArrayList <Double> a) {
		for (int i = 0; i < a.size(); i++)
			if (a.get(i) < min)
				min = a.get(i);
		return min;
	}
	
	/**
	 * Metodo Getter del valore massimo
	 * @param a ArrayList che contiene tutti i dati su cui calcolare il massimo
	 * @return max Ritorna il valore massimo
	 */
	public Double getMax(ArrayList <Double> a) {
		for (int i = 0; i< a.size(); i++)
			if (a.get(i) > max)
				max = a.get(i);
		return max;
	}
	
	/**
	 * Metodo Getter della media
	 * @return average Ritorna il valore medio
	 *
	public double getAverage() {
		return sum/counter;
	}
	
	/
	 * Metodo Getter per il calcolo della media
	 * @param a ArrayList che contiene tutti i dati su cui calcolare la media
	 * @return valore Double della media
	 */
	public Double getAverage (ArrayList <Double> a) {
		for (int i = 0; i < a.size(); i++)
			sum += a.get(i);
		return (Double) sum/a.size();
	}
	
	/**
	 * Metodo Getter per il calcolo della varianza
	 * @param a ArrayList che contiene tutti i dati su cui calcolare la varianza
	 * @return valore Double della varianza
	 */
	public Double getVariance (ArrayList <Double> a) {
		Double avg = getAverage(a);
		Double eps = 0.0;
		for (int i = 0; i < a.size(); i++)
			eps += (a.get(i) - avg) * (a.get(i) - avg);
		return eps/a.size();
	}
	
	/**
	 * Metodo Getter della varianza
	 * @return variance Ritorna il valore della varianza
	 *
	public Double getVariance() {
		Double average = this.getAverage();
		Double t = 0.0;
		for(Double num: value) 
			t += Math.pow((num = average), 2);
			return t/counter;
			
	}
	
	**
	 * Metodo che aggiorna il contatore e i valori massimi e minimi;
	 * inoltre aggiunge il valore passato come parametro all'ArrayList
	 * @param num Valore che viene aggiunto all'array
	 *
	public void addCounter(Double num) {
		this.counter++;
		this.sum += num;
		if(num>this.max) 
			this.max=num;
		if(num<this.min) 
			this.min=num;
		this.value.add(num);
	}
	*/
}
