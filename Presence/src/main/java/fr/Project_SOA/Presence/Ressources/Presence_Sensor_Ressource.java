
package fr.Project_SOA.Presence.Ressources;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import fr.Project_SOA.Presence.Sensor.Presence_Sensor;
import fr.Project_SOA.Presence.oM2M_Client.Client;
import fr.Project_SOA.Presence.oM2M_Client.Response;



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
	
	@GetMapping("/test_OM2M")
	public String Retrieve_OM2M() throws IOException {
		Client client = new Client();
		
		Response resp = client.retrieve("http://127.0.0.1:8080/~/in-cse/in-name/Floor1_Manager/Presence_Detection/cin_413685144", "admin:admin");
		
		
		return resp.getRepresentation();
	}
	
}
