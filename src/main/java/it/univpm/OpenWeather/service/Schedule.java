package it.univpm.OpenWeather.service;

import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Date;
import it.univpm.OpenWeather.model.City;

/**
 * Classa che pianifica l'attività specificata da ripetere ogni ora
 * 
 * @author Emanuela Saleggia
 * @author ValeriaTimmer
 *
 */

public class Schedule {
	
	/**
	 * Costruttore della classe 
	 */
	public Schedule() { 
		try {
			/**
			 * Costruttore della classe Date di java.lang.Object che iniziailizza 
			 * il momento di inizio del metodo
			 * 
			 * @Deprecated : Alloca un oggetto Date e lo inizializza in modo che rappresenti la mezzanotte, 
			 * dell'ora locale, all'inizio del giorno specificato dagli argomenti anno, mese e data.
			 */
			@Deprecated
			Date firstTime = new Date(2020, 12, 1);
	
			/**
			 * Costruttore della classe Timer di java.lang.Object
			 */
			Timer timer = new Timer();
	
			/*
			 * Costruttore della classe TimerTask che effettua l'override del metodo run
			 * dell'interfaccia Runnable di java.lang.Object
			 */
			TimerTask task = new TimerTask() {

				/*
				 * Metodo che salva le informazioni delle città scelte in un arrayList
				 * Effettua l'override del metodo run() della classe TimerTask
				 */
				@Override
				public void run() {
					
					/*
					 * ArrayList sul quale verranno salvate le informazioni utilizzando
					 * il metodo Building della classe BuildingCity 
					*/
					ArrayList <City> downloadCity = new ArrayList <City>(); 
			
					// Città 
					City c1 = new City();
					c1.getInformation("Ancona", "IT");
					downloadCity = BuildingCity.Building(c1);

			
					City c2 = new City();
					c2.getInformation("Londra", "GB");
					downloadCity = BuildingCity.Building(c2);
			
					City c3 = new City();
					c3.getInformation("Berlino", "DE");
					downloadCity = BuildingCity.Building(c3);
				}
			};
	
			/*
			 * Metodo che ripete il salvataggio ogni ora, a partire da una data scelta (firstTime)
			 */
			timer.scheduleAtFixedRate (task, firstTime, 3600000);
	
		} catch (IllegalArgumentException e) {
			// Viene lanciata se firstTime.getTime() < 0 o period <= 0
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// Viene lanciata se l'attività era già pianificata o annullata,
			// il timer è stato annullato o il thread del timer è stato terminato
			e.printStackTrace();
		} catch (NullPointerException e) {
			// Viene lanciata se task o fistTime sono null
			e.printStackTrace();
		}
	}
	
}



