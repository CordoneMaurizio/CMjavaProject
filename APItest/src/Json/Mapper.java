package Json;

import java.util.Map;
import java.util.HashMap;

import com.google.gson.*;
import com.google.gson.reflect.*;

public class Mapper {
	
	public Mapper() {
		
	}

	public HashMap<String, Object> jsonToMap(String str){
			
			HashMap<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>(){}.getType());
			return map;
	}
	
}
