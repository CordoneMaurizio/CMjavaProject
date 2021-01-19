package wvUtilità;
import java.util.Map;

import org.springframework.stereotype.Component;

import java.util.HashMap;

import com.google.gson.*;
import com.google.gson.reflect.*;

/*@author CordoneMaurizio
 * 
 * La classe permette di convertire una variabile di tipo stringa formattata come JSON
 * in una mappa di tipo<Stringa, Oggetto>
 */
@Component
public class Mapper {

		public Mapper() {}

		public Map<String, Object> jsonToMap(String str){
				
				Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>(){}.getType());
				return map;
		}
		
}
