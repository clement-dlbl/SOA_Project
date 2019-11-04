
package fr.Project_SOA.Presence.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import fr.Project_SOA.Presence.Sensor.Presence_Sensor;



@RestController
public class Presence_Sensor_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		return "You are in room " + name;
	}
	
	@GetMapping("/rooms/{name}/sensors/presence")
	public Presence_Sensor Check_Window_Open(@PathVariable String name) {
		Presence_Sensor presence_sens = new Presence_Sensor(name, false);
		//return "The presence sensor is located in room " + name +"\n. His status is " + presence_sens.isPresence();
		return presence_sens;
	}
}
