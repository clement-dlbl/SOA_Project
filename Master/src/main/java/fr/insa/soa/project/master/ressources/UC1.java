package fr.insa.soa.project.master.ressources;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.master.model.Alarm;
import fr.insa.soa.project.master.model.Config;
import fr.insa.soa.project.master.model.Presence_Sensor;
import fr.insa.soa.project.master.use_cases.UC1_main;

@RestController
public class UC1 {
	
	@GetMapping("/Use_Case_1/{numFloor}/{numRoom}")
	public ArrayList<String> getAlarm(@PathVariable int numFloor, @PathVariable int numRoom) throws JSONException {
		//Simulate data base
		UC1_main uc1_main = new UC1_main();
		
		//Instanciate RestTemplate for Rest calls
		RestTemplate restTemplate = new RestTemplate();
		
		Presence_Sensor res_presence = restTemplate.getForObject(Config.getPresence_Service() + "/"+numFloor+"/"+numRoom+"/sensors/presence", Presence_Sensor.class);
		Alarm res_alarm = restTemplate.getForObject(Config.getAlarm_Service() + "/"+numFloor+"/"+numRoom+"/sensors/alarm", Alarm.class);
		ArrayList<String> res = new ArrayList<String>();
		res.add("presence : "+res_presence.isPresence());
		res.add("oldState : "+res_alarm.getStatus());
		
		try {
			res.add("state :"+uc1_main.triggerAlarm(res_presence.isPresence(),res_alarm.getStatus(), numRoom, numFloor, restTemplate));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
}
