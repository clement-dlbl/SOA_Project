
package fr.Project_SOA.Alarm.Controller;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.Project_SOA.Alarm.Sensor.Alarm;



@RestController
public class Alarm_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		return "You are in room " + name;
	}
	
	@GetMapping("/rooms/{name}/sensors/alarm")
	public Alarm Check_Window_Open(@PathVariable String name) {
		Alarm alarm = new Alarm(name, "off");
		//return "The alarm is located in room " + name +"\n. His status is " + alarm.getStatus();
		return alarm;
	}
}
