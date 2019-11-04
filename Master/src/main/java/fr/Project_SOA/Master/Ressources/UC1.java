package fr.Project_SOA.Master.Ressources;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import fr.Project_SOA.Master.Config;
import fr.Project_SOA.Master.Model.*;
import fr.Project_SOA.Master.Use_Case_1.UC1_main;

@RestController
public class UC1 {
	
	@GetMapping("/Use_Case_1")
	public List<String> getAlarm() {
		//Simulate data base
		UC1_main uc1_main = new UC1_main();
		
		//Instanciate RestTemplate for Rest calls
		RestTemplate restTemplate = new RestTemplate();
		
		Presence_Sensor res_presence = restTemplate.getForObject(Config.getPresence_Service() + "/rooms/GEI213/sensors/presence", Presence_Sensor.class);
		Alarm res_alarm = restTemplate.getForObject(Config.getAlarm_Service() + "/rooms/GEI213/sensors/alarm", Alarm.class);
		
		List<String> res = new ArrayList<String>();
		res.add("Presence status : " + res_presence.isPresence());
		res.add("Alarm status : " + res_alarm.getStatus());
		
		res.add("Is alarm launched ?" + uc1_main.triggerAlarm(res_presence.isPresence(), res_alarm.getStatus()));
		
		return res;
	}
	
}
