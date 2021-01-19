package wvAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.springframework.stereotype.Component;

import wvUtilità.Mapper;

@Component
public class APIOpenWeather {
	
static String APIkey = "eaab0d56e8e9ef6b813a372c16f220e6";
	
	public Map<String, Object> objCurrent;
	public Map<String, Object> objForecast;
	public Map<String, Object> objHistory;
	
	public String città;
	public String lat;
	public String lon;
	
	Mapper map = new Mapper();
	
	public APIOpenWeather(String città) {
		this.città = città;	
		callCurrent();
		setCoord();
		getInfo();
	}
	
	private void setCoord() {
		
	    Map<String, Object> mapCoord = map.jsonToMap(objCurrent.get("coord").toString());
		
		this.lat =  mapCoord.get("lat").toString();
		this.lon =  mapCoord.get("lon").toString();
		
	}
	public void getInfo() {
		System.out.println(
				"Nome città:     " + città + "\n" +
				"-- latitudine:  " + lat + "\n" +
				"-- longitudine: " + lon + "\n");
			
	}
	
	public Map<String, Object> callCurrent() {
		try {
			String urlString ="http://api.openweathermap.org/data/2.5/weather?q=" + this.città + "&appid=" + APIkey + "&units=metric";
			URL url = new URL(urlString);
			URLConnection Connection = url.openConnection();
			
			String data = "";
			BufferedReader lettore = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
			String line = "";
			while((line = lettore.readLine()) != null) {
				data += line;
			}
			lettore.close();
			
			objCurrent = map.jsonToMap(data.toString());
				
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return objCurrent;
		}
	
	
	
}