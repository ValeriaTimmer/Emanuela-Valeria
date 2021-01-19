package it.univpm.OpenWeather.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import it.univpm.OpenWeather.exception.DataFormatException;


/**
 * Classe che gestisce le date
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class DateUtils {
	
	/**
	 * Data di inizio
	 */
	private static Date dateFrom;
	
	/**
	 * Data di fine
	 */
	private static Date dateTo;
	
	/**
	 * Metodo che genera la data odierna
	 * @return String data odierna
	 */
	public static String today() {
		Calendar today = Calendar.getInstance();
		today.clear (Calendar.HOUR);
		today.clear (Calendar.MINUTE);
		today.clear (Calendar.SECOND);
		Date todayDate = today.getTime();
		return Config.formatoData.format(todayDate);
	}
	
	/**
	 * Metodo che genera la data di ieri
	 * @return String data di ieri
	 */
	public static String yesterday() {
		Calendar yesterday = Calendar.getInstance();
		return Config.formatoData.format(yesterday);
	}
	
	/**
	 * Metodo che verifica se le date sono state inserite correttamente
	 * Se non sono state inserite, assumono il valore della data di ieri (from) e di oggi (to)
	 * @param from Data iniziale
	 * @param to Data finale
	 * @throws DataFormatException Eccezione personalizzata 
	 * @throws ParseException Errore di parsing
	 */
	private static void dateCheck (String from, String to) throws DataFormatException {	
		
		if (from.equals("") && to.equals("")) {
			from = today();
			to = today();
		}
		else if (to.equals("")) {
			to = today();
		}
	
		try {
			
			dateFrom = Config.formatoData.parse(from);
			dateTo = Config.formatoData.parse(to);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

	}
	
	/**
	 * Metodo che ritorna il valore del periodo in base alle date inserite
	 * @param from Data iniziale
	 * @param to Data finale
	 * @return Periodo 
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws ParseException Errore di parsing
	 */
	public static long getPeriod (String from, String to) throws DataFormatException {
		
		dateCheck(from, to);
		if (dateTo.before(dateFrom))
			throw new DataFormatException ("La data finale precede quella iniziale!", 0);
		
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
	public static ArrayList<String> date (String from, String to) throws DataFormatException {
		
		ArrayList<String> d = new ArrayList<String>();
		
		Long period = getPeriod(from, to);
		
		for (int i=0; i<=period; i++) {
			
			d.add(Config.formatoData.format(dateFrom));
			
			dateFrom.setTime(dateFrom.getTime()+(24*60*60*1000));
		
		}
		
		return d;
	}
}
