package fr.insa.soa.project.master.ressources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.master.Config;
import fr.insa.soa.project.master.model.Window_Sensor;
import fr.insa.soa.project.master.use_cases.UC2_main;
import fr.insa.soa.project.master.model.Inside_Temp;

@RestController
public class UC2 {
	
	@GetMapping("/Use_Case_2")
	public List<String> getTemperature() {
		//Simulate data base
		UC2_main uc2_main = new UC2_main();
		System.out.println("test");
		//Instanciate RestTemplate for Rest calls
		RestTemplate restTemplate = new RestTemplate();
		List<String> res = new ArrayList<String>();
		System.out.println("test2");
		
		
		try {
			//////
			//Outside_Temp res_out_temp;
			//////
			final String uri = "https://data.toulouse-metropole.fr/api/records/1.0/search/?dataset=28-station-meteo-carmes&lang=fr&sort=heure_de_paris&fbclid=IwAR3mFob-pcISDNpt1-nnUAtJs8IEZmnsnu8VbkjXwqK_yzAN74_diGnPzKc";
		    
		
		    //Retreive data from Toulouse Métropole DB
		    String result = restTemplate.getForObject(uri, String.class);
		    ///Parsing of file and get Temp object on json file
		    JSONObject obj = new JSONObject(result);
	        JSONArray arr = obj.getJSONArray("records");
	        
	        for (int i = 0; i < arr.length(); i++) {
	            double temp = arr.getJSONObject(i).getJSONObject("fields").getDouble("temperature_en_degre_c");	
	            
	          
	            //retreive inside temp
	    		Inside_Temp resIntemp = restTemplate.getForObject(Config.getTemperature_Service() + "/rooms/GEI213/sensors/temperature/inside", Inside_Temp.class);
	    		//retreive Windows 
	    		Window_Sensor res_window = restTemplate.getForObject(Config.getWindow_Service() + "/rooms/GEI213/sensors/window_sensor", Window_Sensor.class);
	
	    		try {
					res.add(uc2_main.openWindow(resIntemp.getData(), temp, res_window.getStatus()));
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return res;
	}
	
}
