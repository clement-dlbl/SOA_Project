package fr.insa.soa.project.historic.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.project.historic.model.Historic_model;


@RestController
public class Historic_controller {
	
	@PostMapping(path ="/historic/new", consumes = "application/json", produces = "application/json")
	public void addLogToHistoric(@RequestBody HashMap<String, Object> payload) throws IOException{
		System.out.println("Sevice Log");
		
		String status = (String) payload.get("status");
		String service = (String) payload.get("source");
		System.out.println("Hist : "+ status + service);
		Historic_model.addLogToHistoric(service , "new:"+status);
	}

	/*@GetMapping("/{numFloor}/{numRoom}/{service}/historic")	
	public HashMap<String, String> retrieveServiceHistoric(@PathVariable int numFloor, @PathVariable int numRoom) {
		return Historic_model.gethistoric();
	}*/
	@GetMapping("/historic")	
	public HashMap<String, String> retrieveAllHistoric() {
		return Historic_model.gethistoric();
	}
	
}
