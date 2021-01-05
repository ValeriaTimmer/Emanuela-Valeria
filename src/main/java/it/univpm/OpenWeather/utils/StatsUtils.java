package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.exception.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.ParseException;
import java.util.Vector;
import java.util.Date;
import java.time.LocalDate;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DateFormat;


import it.univpm.OpenWeather.statistics.StatisticsCalculator;

public class StatsUtils {
	/**
	 * Array che contiene le statistiche riguardanti la temperatura
	 */
	private static JSONArray stats = new JSONArray ();
	
	/**
	 * Data di inizio
	 */
	private static Date dateFrom;
	
	/**
	 * Data di fine
	 */
	private static Date dateTo;
	
	/**
	 *  DateFormat per il parsing della data in formato: "dd/MM/yy"
	 */
	private static DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Metodo che genera la data odierna
	 * @return String data odierna
	 */
	private static String today() {
		Date today = new Date();
		return formatoData.format(today);
	}
	
	/**
	 * Metodo che genera la data di ieri
	 * @return String data di ieri
	 */
	private static String yesterday() {
		Date yesterday = new Date();
		return formatoData.format(yesterday);
	}
	
	/**
	 * Metodo che verifica se le date sono state inserite correttamente
	 * Se non sono state inserite, assumono il valore della data di ieri (from) e di oggi (to)
	 * @param from Data iniziale
	 * @param to Data finale
	 * @throws DataFormatException Eccezione personalizzata 
	 * @throws ParseException Errore di parsing
	 */
	private static void dateCheck (String from, String to) throws DataFormatException, ParseException {	
		
		if (from.equals("") && to.equals("")) {
			from = yesterday();
			to = today();
		}
		else if (to.equals("")) {
			to = today();
		}
		
		dateFrom = formatoData.parse(from);
		dateTo = formatoData.parse(to);

	}
	
	/**
	 * Metodo che ritorna il valore del periodo in base alle date inserite
	 * @param from Data iniziale
	 * @param to Data finale
	 * @return Periodo 
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws ParseException Errore di parsing
	 */
	public static long getPeriod (String from, String to) throws DataFormatException, ParseException {
		dateCheck(from, to);
		if (dateTo.before(dateFrom)) throw new DataFormatException();
		return ((dateTo.getTime()- dateFrom.getTime()) / (24*60*60*1000));
	}
	
	/**
	 * Metodo che inserisce in un vettore le date comprese tra 
	 * la data di inizio e la data di fine
	 * @param from Data iniziale
	 * @param to Data finale
	 * @return d Vettore contenente le date
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws ParseException Errore di parsing
	 */
	public static JSONArray date (String from, String to) throws DataFormatException, ParseException{
		JSONArray d = new JSONArray();
		Long period = getPeriod(from, to);
		for (int i=0; i<=period; i++) {
			d.add(formatoData.format(dateFrom));
			dateFrom.setTime(dateFrom.getTime()+(24*60*60*1000));
		}
		return d;
	}
	
	/**
	 * Metodo che effettua le statistiche 
	 * @param array Array sul quale vengono effettuate le statistiche
	 * @param type Tipo di dato sul quale si vogliono effettuare le statistiche
	 * (temperature / humidity)
	 * @param from Data dal quale si vogliono effettuare le statistiche
	 * @param to Data fino al quale si vogliono effettuare le statistiche
	 * @return stats Array contenente le statistiche 
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws ParseException Errore di parsing
	 */
	public JSONArray getStats(JSONArray array, String type, String from, String to) throws DataFormatException, ParseException{
		
		JSONObject objectStats = new JSONObject();
		
		StatisticsCalculator calc = new StatisticsCalculator();

		String datainiziale = formatoData.format(from);
		
        String datafinale = formatoData.format(to);
        
        JSONArray allDates = date(datainiziale, datafinale);
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = new JSONObject();
				
				try {
						
					if (type.equals("temperature")) {
						
						for (Object obj1 : allDates) {
							
							if (obj1 instanceof Object) {
							
								Double value = (Double) o1.get("temperature");
								calc.addCounter(value);
								
							}
						}
					}
					else if (type.equals("humidity")) {
						
						for (Object obj2 : allDates) {
							
							if (obj2 instanceof Object) {
								
								Double value = (Double) o1.get("humidity");
								calc.addCounter(value);
								
							}
						}
						
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
				
		}
		
		try {
			
			objectStats.put ("min", calc.getMin());
			objectStats.put ("max", calc.getMax());
			objectStats.put ("avg", calc.getAverage());
			objectStats.put ("var", calc.getVariance());
		
			stats.add(objectStats);
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return stats;
	}
}
