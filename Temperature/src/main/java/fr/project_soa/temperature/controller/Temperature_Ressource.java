
package fr.project_soa.temperature.controller;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.project_soa.temperature.sensor.Temperature;



@RestController
public class Temperature_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		
		return "You are in room " + name;
	}
	
	@GetMapping("/rooms/{name}/sensors/temperature/{value}")
	public Temperature Check_Window_Open(@PathVariable String name, @PathVariable float value) {
		Temperature Temperature = new Temperature(name, value);
		//return "The Temperature is located in room " + name +"\n. His status is " + Temperature.getStatus();
		return Temperature;
	}
}
