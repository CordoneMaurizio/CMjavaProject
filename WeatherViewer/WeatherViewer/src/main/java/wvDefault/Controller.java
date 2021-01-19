package wvDefault;

import wvAPI.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {
	
	@GetMapping("/Call/{citta}")
	public Map<String, Object> chiamata() {
		APIOpenWeather API1 = new APIOpenWeather("termoli"); 
		return API1.callCurrent();
	}

}
