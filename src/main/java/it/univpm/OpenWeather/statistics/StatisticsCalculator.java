package it.univpm.OpenWeather.statistics;

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
	private double min;
	/**
	 * Valore massimo
	 */
	private double max;
	/**
	 * Contatore
	 */
	private int counter;
	/**
	 * Somma
	 */
	private double sum;
	/**
	 * Array che contiene i valori desiderati
	 */
	private ArrayList <Double> value;
	
	/**
	 * Costruttore
	 */
	public StatisticsCalculator() {
		this.max = Double.MAX_VALUE;
		this.min = Double.MAX_VALUE;
		this.sum = 0.0;
		this.counter = 0;
		this.value = new ArrayList<Double>();
	}
	
	/**
	 * Metodo Getter del valore minimo
	 * @return min Ritorna il valore minimo
	 */
	public double getMin() {
		return min;
	}
	
	/**
	 * Metodo Getter del valore massimo
	 * @return max Ritorna il valore massimo
	 */
	public double getMax() {
		return max;
	}
	
	/**
	 * Metodo Getter della media
	 * @return average Ritorna il valore medio
	 */
	public double getAverage() {
		return sum/counter;
	}
	
	/**
	 * Metodo Getter della varianza
	 * @return variance Ritorna il valore della varianza
	 */
	public Double getVariance() {
		Double average = this.getAverage();
		Double temp = 0.0;
		for(Double num: value) 
			temp += Math.pow((num = average), 2);
			return temp/counter;
			
	}
	
	/**
	 * Metodo che aggiorna il contatore e i valori massimi e minimi;
	 * inoltre aggiunge il valore passato come parametro all'ArrayList
	 * @param num Valore che viene aggiunto all'array
	 */
	public void addCounter(Double num) {
		this.counter++;
		this.sum += num;
		if(num>this.max) 
			this.max=num;
		if(num<this.min) 
			this.min=num;
		this.value.add(num);
	}
}
