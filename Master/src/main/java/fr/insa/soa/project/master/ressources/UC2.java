package fr.insa.soa.project.master.ressources;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.master.model.Window_Sensor;
import fr.insa.soa.project.master.use_cases.UC2_main;
import fr.insa.soa.project.master.model.Config;
import fr.insa.soa.project.master.model.Inside_Temp;

@RestController
public class UC2 {
	
	@GetMapping("/Use_Case_2/{numFloor}/{numRoom}")
	public HashMap<String, String> getTemperature(@PathVariable int numFloor, @PathVariable int numRoom) {
		//Simulate data base
		UC2_main uc2_main = new UC2_main();
		System.out.println("test");
		//Instanciate RestTemplate for Rest calls
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> res = new HashMap<String, String>();		
		
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
            Double temp = arr.getJSONObject(0).getJSONObject("fields").getDouble("temperature_en_degre_c");	
            String date = arr.getJSONObject(0).getJSONObject("fields").getString("heure_utc");
            Instant instant = Instant.now();
            System.out.println(date + instant.toString());
            res.put("dateSample", date);
            res.put("dateUTC", instant.toString());
            
          
            //retreive inside temp
    		Inside_Temp resIntemp = restTemplate.getForObject(Config.getTemperature_Service() + "/"+numFloor+"/"+numRoom+"/sensors/temperature/inside", Inside_Temp.class);
    		//retreive Windows 
    		Window_Sensor res_window = restTemplate.getForObject(Config.getWindow_Service() + "/"+numFloor+"/"+numRoom+"/sensor/window", Window_Sensor.class);
    		res.put("tempIN", String.valueOf(resIntemp.getData()));
    		res.put("tempOUT", String.valueOf(temp));
    		try {
				res.put("state", uc2_main.openWindow(resIntemp.getData(), temp, res_window.getStatus(), numFloor, numRoom, restTemplate));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return res;
	}
	
}
