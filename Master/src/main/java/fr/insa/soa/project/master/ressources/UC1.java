package fr.insa.soa.project.master.ressources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.master.Config;
import fr.insa.soa.project.master.model.Alarm;
import fr.insa.soa.project.master.model.Presence_Sensor;
import fr.insa.soa.project.master.use_case_1.UC1_main;

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
		System.out.println("before Triggering Alarm");
		try {
			res.add("Is alarm launched ?" + uc1_main.triggerAlarm(res_presence.isPresence(), res_alarm.getStatus()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
}
