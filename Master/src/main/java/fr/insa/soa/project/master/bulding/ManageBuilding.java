package fr.insa.soa.project.master.bulding;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageBuilding {
		
	@GetMapping("/addFloor/{num}")
	public String Add_Floor(@PathVariable String name) {
		
		
		
		return "You are in room " + name;
	}
	
	@GetMapping("/addRoom/{num}")
	public String Add_Room(@PathVariable String name) {
		
		return "You are in room " + name;
	}
}
