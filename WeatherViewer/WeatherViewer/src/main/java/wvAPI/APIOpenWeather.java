package wvAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Component;

import wvUtilità.Mapper;

@Component
public class APIOpenWeather {
	
static String APIkey = "eaab0d56e8e9ef6b813a372c16f220e6";
	
	public JSONObject objCurrent;
	public JSONObject objForecast;
	public JSONObject objHistory;

	public String città;
	public Double lat;
	public Double lon;
	
	Mapper map = new Mapper();
	
	public APIOpenWeather(String città) {
		this.città = città;	
		callCurrent();
		setCoord();
	}
	
	private void setCoord() {
		
		JSONObject coord = map.objParser(objCurrent, "coord");
		
		this.lat =  (Double)coord.get("lat");
		this.lon =  (Double)coord.get("lon");
		
	}
	public void getInfo() {
		System.out.println(
				"Nome città:     " + città + "\n" +
				"-- latitudine:  " + lat + "\n" +
				"-- longitudine: " + lon + "\n");
			
	}
	
	public JSONObject callCurrent() {
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
			
			objCurrent = (JSONObject)JSONValue.parseWithException(data);
				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		//objCurrent = (JSONObject)objCurrent.get("coord");
		//return (Double)objCurrent.get("lat");
		return objCurrent;
		
		}
	
	
	
}