package fr.project_soa.master.ressources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.project_soa.master.Config;
import fr.project_soa.master.model.Window_Sensor;
import fr.project_soa.master.model.Inside_Temp;
import fr.project_soa.master.use_case_2.UC2_main;

@RestController
public class UC2 {
	
	@GetMapping("/Use_Case_2")
	public List<String> getAlarm() {
		//Simulate data base
		UC2_main uc2_main = new UC2_main();
		
		//Instanciate RestTemplate for Rest calls
		RestTemplate restTemplate = new RestTemplate();
		
		Inside_Temp res_in_temp = restTemplate.getForObject(Config.getTemperature_Service() + "/rooms/GEI213/sensors/temperature", Inside_Temp.class);
		
		String string_out_temp = restTemplate.getForObject("https://data.toulouse-metropole.fr/api/records/1.0/search/?dataset=28-station-meteo-carmes&lang=fr&sort=heure_de_paris&fbclid=IwAR1RJ5J5QAvPQW7xmB0JpBpa2bw_dYAqUsOxya0y5qL3DjIc5uIvC9IGQg", String.class);
		
		//////
		Outside_Temp res_out_temp = ;
		//////
		Window_Sensor res_window = restTemplate.getForObject(Config.getWindow_Service() + "/rooms/GEI213/sensors/alarm", Window_Sensor.class);
		
		List<String> res = new ArrayList<String>();
		res.add("Inside temperature : " + res_in_temp);
		res.add("Outside temperature : " + res_out_temp);
		System.out.println("before Triggering Alarm");
		try {
			res.add("What should be windows position ?" + UC2_main.openWindow(res_in_temp, res_out_temp, res_window.getStatus()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
}
