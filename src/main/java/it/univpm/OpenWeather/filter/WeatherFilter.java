package it.univpm.OpenWeather.filter;

import org.json.simple.JSONArray;

import it.univpm.OpenWeather.utils.FilterUtils;
import it.univpm.OpenWeather.service.DownloadCity;

public class WeatherFilter implements Filter<Object,Object> {
	
	/**
	 * array da filtrare
	 */
	private JSONArray arrayWeather;
	
	/**
	 * variabile che richiama il metodo della classe FilterUtils
	 */
    private FilterUtils utils;
    
    public WeatherFilter(JSONArray array) {
    	this.arrayWeather = array;
    	this.utils = new FilterUtils();
    }
    
    /**
     * metodo getter
     * @return arrayWeather
     */
    public JSONArray getWeather() {
    	return arrayWeather;
    }
    
    /**
     * metodo che effettua l'override del metodo dell'interfaccia
     * e richiama il metodo della classe FilterUtils
     * @return ritorna l'array filtrato
     */
    @Override
    public JSONArray filtersCity(JSONArray arrayCity, Object city, Object weather) {
    	return(JSONArray) utils.getWeatherFiltered(this.getWeather(), city, weather);
    }
    
    
	
}
