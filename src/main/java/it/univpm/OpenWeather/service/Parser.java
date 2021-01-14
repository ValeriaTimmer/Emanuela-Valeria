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
import org.springframework.stereotype.Component;

/**
 * Classe che si occupa di leggere i dati dall'API e di salvarli in un file 
 * @author ValeriaTimmer
 *
 */
@Component
public class Parser {

	private String cityName;
	
	private String StateCode;
	
	public String getCityName() {
		return this.cityName;
	}
	
	public void setCityName(String name) {
		this.cityName = name;
	}
	
	public String getStateCode() {
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
			URLConnection openConnection = new URL ("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName +"&appid=" + Config.getApiKey()).openConnection();
			openConnection.addRequestProperty ( " User-Agent " , " Mozilla / 5.0 (Windows NT 6.1; WOW64; rv: 25.0) Gecko / 20100101 Firefox / 25.0 " );
			InputStream in = openConnection.getInputStream();
			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);
				
				while ((line = buf.readLine()) != null) 
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
		this.chiamataAPI("Ancona");
		try {
			FileWriter file_output = new FileWriter (nome_file,true);
			file_output.append(this.jo.toJSONString());
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
