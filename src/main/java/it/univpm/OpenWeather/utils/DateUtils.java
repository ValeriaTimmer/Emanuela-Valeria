package it.univpm.OpenWeather.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.OpenWeather.service.*;

import it.univpm.OpenWeather.exception.DataFormatException;
import it.univpm.OpenWeather.exception.UrlException;

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
	
	private DownloadCity d = new DownloadCity();
	
	/**
	 *  DateFormat per il parsing della data in formato: "dd/MM/yyyy"
	 */
	public static DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Formato data del sito OpenWeather
	 */
	private String DATE_FORMAT_I = "yyyy-MM-dd'T'HH:mm:ss";
	
	/**
	 * Formato data desiderato
	 */
	private String DATE_FORMAT_O = "yyyy-MM-dd";
	
	DateFormat formatInput = new SimpleDateFormat(DATE_FORMAT_I);
	DateFormat formatOutput = new SimpleDateFormat(DATE_FORMAT_O);
	

	/**
	 * Metodo che permette di trasformare una data dal formato DATE_FORMAT_I
	 * al formato DATE_FORMAT_0
	 * 
	 * @param date Data da trasformare
	 * @return dateString Data nel formato desiderato
	 */
	public String formatDate (long unixSeconds) {
	
		Date d = new Date (unixSeconds*1000L);
		
		formatOutput.setTimeZone(java.util.TimeZone.getTimeZone("Europe/Rome"));
		
		String dateString = formatOutput.format(d);

		return dateString;
		
	}
	
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
		return formatoData.format(todayDate);
	}
	
	/**
	public static String getDate (String cityName) throws UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException {
		Parser p = new Parser();
		String data = "";
		JSONArray array = p.caricaFile(Config.getName());
		for(Object o:array) {
			if(o instanceof JSONObject) {
				JSONObject ob = (JSONObject)o;
				data = (String) ob.get("date");
			}
		}
		return formatoData.format(data);
	}
	*/
	/**
	 * Metodo che genera la data di ieri
	 * @return String data di ieri
	 */
	public static String yesterday() {
		Calendar yesterday = Calendar.getInstance();
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
			from = today();
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
	public static ArrayList<String> date (String from, String to) throws DataFormatException, ParseException{
		ArrayList<String> d = new ArrayList<String>();
		Long period = getPeriod(from, to);
		for (int i=0; i<=period; i++) {
			d.add(formatoData.format(dateFrom));
			dateFrom.setTime(dateFrom.getTime()+(24*60*60*1000));
		}
		return d;
	}
}
