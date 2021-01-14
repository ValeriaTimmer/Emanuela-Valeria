package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.utils.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe che si occupa di leggere i dati dall'API e di salvarli in un file 
 * @author ValeriaTimmer
 *
 */
@Configuration
public class Parser {
	
	
	private String cityName;
	
	private String StateCode;
	
	private String getCityName() {
		return this.cityName;
	}
	
	private void setCityName(String name) {
		this.cityName = name;
	}
	
	private String getStateCode() {
		return this.StateCode;
	}
	
	
	private JSONObject jo = null;
	
	private JSONArray ja = null;
	
	public Parser () {
		this.jo = new JSONObject();
		this.ja = new JSONArray();
	}
	


	public void chiamataAPI (String cityName) {
		
		try {
			URL oracle = new URL ("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName +"&appid=" + Config.getApiKey());
			HttpsURLConnection yc = (HttpsURLConnection) oracle.openConnection();
			yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			BufferedReader in = new BufferedReader (new InputStreamReader (yc.getInputStream()));
			String data = "";
			String line = "";
			try {
				while ((line = in.readLine()) != null) 
					data +=line;
			} finally  {
				in.close();
			}
			
			this.jo = (JSONObject) JSONValue.parseWithException(data);
		
		} catch (MalformedURLException e ) {
			e.printStackTrace();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public void salvaFile (String nome_file) {
		this.chiamataAPI(this.cityName);
		try {
			PrintWriter file_output = new PrintWriter (new BufferedWriter (new FileWriter (nome_file)));
			file_output.println(this.jo);
			file_output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public JSONArray caricaFile (String nome_file) {
		try {
			Scanner file_input = new Scanner (new BufferedReader (new FileReader (nome_file)));
			String str = file_input.nextLine();
			this.jo = (JSONObject) JSONValue.parseWithException(str);
			this.ja.add(jo);
		} catch (IOException  e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ja;
	}
	

}
