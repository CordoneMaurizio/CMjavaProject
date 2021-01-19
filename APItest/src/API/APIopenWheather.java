package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.URLConnection;
import java.net.URL;

import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import Json.*;

public class APIopenWheather {
	

	static String APIkey = "eaab0d56e8e9ef6b813a372c16f220e6";
	
	public HashMap<String, Object> objCurrent;
	public HashMap<String, Object> objForecast;
	public HashMap<String, Object> objHistory;
	
	private String città;
	private String lat;
	private String lon;
	
	Mapper map = new Mapper();
	
	public APIopenWheather(String città) {
		this.città = città;	
		callCurrent();
		setCoord();
	}
	
	private void setCoord() {
		
		HashMap<String, Object> mapCoord = map.jsonToMap(objCurrent.get("coord").toString());
		
		this.lat =  mapCoord.get("lat").toString();
		this.lon =  mapCoord.get("lon").toString();
		
	}
	public void getInfo() {
		System.out.println(
				"Nome città:     " + città + "\n" +
				"-- latitudine:  " + lat + "\n" +
				"-- longitudine: " + lon + "\n");
			
	}
	
	public void visHistory() {
		
		System.out.println(objCurrent);
		System.out.println(objHistory);
		
	}
	
    public void visForecast() {
		
		System.out.println(objForecast);
		
	}
	
	public HashMap<String, Object> callCurrent() {
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
	
	public HashMap<String, Object> callForecast() {
		try {
			String urlString ="http://api.openweathermap.org/data/2.5/forecast?q=" + this.città + "&appid=" + APIkey + "&units=metric";
			URL url = new URL(urlString);
			URLConnection Connection = url.openConnection();
			
			String data = "";
			BufferedReader lettore = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
			String line = "";
			while((line = lettore.readLine()) != null) {
				data += line;
			}
			lettore.close();
		
			objForecast = map.jsonToMap(data.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return objForecast;
	}
	
	public HashMap<String, Object> callHistory() {
		
		long epoch = System.currentTimeMillis()/1000;
		JSONArray arrayTemp = new JSONArray();
		String data = "";	
		try {
			
				String urlString ="https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + this.lat + "&lon=" + this.lon + "&dt=" + (epoch - (1*86400)) + "&appid=" + APIkey + "&units=metric";
				URL url = new URL(urlString);
				URLConnection Connection  = url.openConnection();
				BufferedReader lettore = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
				
				
				String line = "";
				while((line = lettore.readLine()) != null) {
					data += line;
				}
					lettore.close();
						
	            
				JSONObject objTemp = (JSONObject)JSONValue.parseWithException(data);
				arrayTemp.add(objTemp);	
				
				urlString ="https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + this.lat + "&lon=" + this.lon + "&dt=" + (epoch - (2*86400)) + "&appid=" + APIkey + "&units=metric";
			    url = new URL(urlString);
			    Connection  = url.openConnection();
				lettore = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
				
				
				line = "";
				while((line = lettore.readLine()) != null) {
					data += line;
				}
					lettore.close();
						
	            
				objTemp = (JSONObject)JSONValue.parseWithException(data);
				arrayTemp.add(objTemp);	
				
				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	objHistory = map.jsonToMap(arrayTemp.toString());	
	return objHistory;
		
	}

}
