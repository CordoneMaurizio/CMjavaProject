import java.util.Scanner;
import java.util.Map;

import API.*;
import Json.*;
public class APIcall {
	
	public static void main(String[] args ) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("inserire città");
		String nome = input.next();
		input.close();
		
		APIopenWheather API1 = new APIopenWheather(nome);
		
		API1.getInfo();
		API1.callForecast();
		API1.callHistory();
		API1.visHistory();
		API1.visForecast();
		
		//Mapper map = new Mapper();
		//Map<String, Object> main = map.jsonToMap(API1.objCurrent.get("main").toString());
		//System.out.println(map.jsonToMap(main.get("humidity").toString()));
	}

}
