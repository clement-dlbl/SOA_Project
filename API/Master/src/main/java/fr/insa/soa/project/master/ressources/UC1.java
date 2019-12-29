package fr.insa.soa.project.master.ressources;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import org.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.alarm.model.Alarm_model;
import fr.insa.soa.project.master.model.Config;
import fr.insa.soa.project.master.use_cases.UC1_main;
import fr.insa.soa.project.presence.model.Presence_model;

@RestController
public class UC1 {
	@CrossOrigin(origins = "*")
	@GetMapping("/Use_Case_1/{numFloor}/{numRoom}")
	public HashMap<String, String> getAlarm(@PathVariable int numFloor, @PathVariable int numRoom) throws JSONException {
		//Simulate data base
		UC1_main uc1_main = new UC1_main();
		
		//Instanciate RestTemplate for Rest calls
		RestTemplate restTemplate = new RestTemplate();
		
		Presence_model res_presence = restTemplate.getForObject(Config.getPresence_Service() + "/"+numFloor+"/"+numRoom+"/sensors/presence", Presence_model.class);
		Alarm_model res_alarm = restTemplate.getForObject(Config.getAlarm_Service() + "/"+numFloor+"/"+numRoom+"/sensors/alarm", Alarm_model.class);
		HashMap<String, String> res = new HashMap<String, String>();
		res.put("presence", res_presence.getStatus());
		res.put("oldState", res_alarm.getStatus());
		
		try {
			Instant instant = Instant.now();
			res.put("date", instant.toString());
			res.put("state", uc1_main.triggerAlarm(res_presence.getStatus() == "true",res_alarm.getStatus(), numRoom, numFloor, restTemplate));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
}
