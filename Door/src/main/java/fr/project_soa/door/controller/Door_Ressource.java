
package fr.project_soa.door.controller;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.project_soa.door.sensor.Door;



@RestController
public class Door_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		
		return "You are in room " + name;
	}
	
	@GetMapping("/rooms/{name}/sensors/door")
	public Door Check_Window_Open(@PathVariable String name) {
		Door door = new Door(name, "closed");
		//return "The door is located in room " + name +"\n. His status is " + door.getStatus();
		return door;
	}
}
