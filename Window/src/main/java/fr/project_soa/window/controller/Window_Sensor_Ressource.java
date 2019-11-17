
package fr.project_soa.window.controller;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.project_soa.window.sensor.Window_Sensor;



@RestController
public class Window_Sensor_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		return "You are in room " + name;
	}
	
	@GetMapping("/rooms/{name}/sensors/window_sensor")
	public String Check_Window_Open(@PathVariable String name) {
		Window_Sensor win_sens = new Window_Sensor(name, "closed");
		return "The window sensor is located in room " + name +"\n His status is " + win_sens.getStatus();
	}
}
