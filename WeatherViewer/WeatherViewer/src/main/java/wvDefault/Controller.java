package wvDefault;

import wvAPI.*;
import wvUtilità.Mapper;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	Mapper map = new Mapper();
	
	@GetMapping("/Call")
	public JSONObject chiamata() {
		APIOpenWeather API = new APIOpenWeather("termoli");
		return API.callCurrent();
		
	}

}
