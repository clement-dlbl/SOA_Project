package fr.insa.soa.project.master.ressources;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.master.use_cases.UC2_main;
import fr.insa.soa.project.temperature.model.Temperature_model;
import fr.insa.soa.project.window.model.Window_model;
import fr.insa.soa.project.master.model.Config;

@RestController
public class UC2 {
	@CrossOrigin(origins = "*")
	@GetMapping("/Use_Case_2/{numFloor}/{numRoom}")
	public HashMap<String, String> getTemperature(@PathVariable int numFloor, @PathVariable int numRoom) {
		//Simulate data base
		UC2_main uc2_main = new UC2_main();
		//Instanciate RestTemplate for Rest calls
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> res = new HashMap<String, String>();
		
		final String HISTORICURL = "http://localhost:8000";
		HttpHeaders headers = new HttpHeaders();
		
		
			//System.out.println(resIntempLast.toString());

			//Generate an Inside Temp  http://localhost:8082/2/200/sensors/temperature/inside/new
			Double val = restTemplate.getForObject(Config.getTemperature_Service()+"/"+numFloor+"/"+numRoom+"/sensors/temperature/inside/new", Double.class);
			System.out.println(val);
		    //////
			//Outside_Temp res_out_temp;
			//////
			final String uri = "https://data.toulouse-metropole.fr/api/records/1.0/search/?dataset=28-station-meteo-carmes&lang=fr&sort=heure_de_paris&fbclid=IwAR3mFob-pcISDNpt1-nnUAtJs8IEZmnsnu8VbkjXwqK_yzAN74_diGnPzKc";
		    
		
		    //Retreive data from Toulouse Métropole DB
		    String result = restTemplate.getForObject(uri, String.class);
		    
		    ///Parsing of file and get Temp object on json file
		    JSONObject obj = new JSONObject(result);
	        JSONArray arr = obj.getJSONArray("records");
            Double temp = arr.getJSONObject(0).getJSONObject("fields").getDouble("temperature_en_degre_c");	
            String date = arr.getJSONObject(0).getJSONObject("fields").getString("heure_utc");
            Instant instant = Instant.now();
            System.out.println(date + instant.toString());
            res.put("dateSample", date);
            res.put("dateUTC", instant.toString());
    		
            //Add to historic
    		
    		
    	    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
    	    headers.setContentType(MediaType.APPLICATION_JSON);
    	    
    	    

    		HashMap<String, Object> json = new HashMap<String, Object>();
    		json.put("source", "tempeartureOut");
    		json.put("status", String.valueOf(temp));
    		
    		HttpEntity<HashMap<String, Object>> requestBody = new HttpEntity<>(json , headers);
    		Object authorizationResponse = restTemplate.postForObject(HISTORICURL+"/historic/new", requestBody, Object.class);
    		System.out.println("Alarm test : "+(String) authorizationResponse);
            
          
            //retreive  last  inside temp
            Temperature_model resIntempLast = restTemplate.getForObject(Config.getTemperature_Service()+"/"+numFloor+"/"+numRoom+"/sensors/temperature/inside",Temperature_model.class);
    		//retreive Windows 
    		Window_model res_window = restTemplate.getForObject(Config.getWindow_Service()+"/"+numFloor+"/"+numRoom+"/sensor/window", Window_model.class);
    		res.put("tempIN", String.valueOf(resIntempLast.getData()));
    		res.put("tempOUT", String.valueOf(temp));
    		try {
				res.put("state", uc2_main.openWindow(Double.valueOf(resIntempLast.getData()), temp, res_window.getStatus(), numFloor, numRoom, restTemplate));
			} catch (IOException e) {
				e.printStackTrace();
			}
		return res;
		}
	
}
