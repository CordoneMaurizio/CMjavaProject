package Json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class JsonOperator {
	
	private JSONObject obj;
	
	public JsonOperator(String str) {
		
		obj = (JSONObject)JSONValue.parse(str);
	}
	
	public JsonOperator() {
	}
	
	public void writeJson(String file) {

        try {
        	
        	System.out.println("Scrittura JSON nel file: " + file);
            BufferedWriter jsonFileWriter = new BufferedWriter(new FileWriter(file));
            jsonFileWriter.write(obj.toJSONString());
            jsonFileWriter.flush();
            jsonFileWriter.close();
            System.out.println("... scrittura completata");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void readJson(String file) {
        JSONParser parser = new JSONParser();

        try {
            System.out.println("Reading JSON file from Java program");
            BufferedReader jsonFileReader = new BufferedReader(new FileReader(file));
            obj = (JSONObject) parser.parse(jsonFileReader);
            
        } catch (IOException e){
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
