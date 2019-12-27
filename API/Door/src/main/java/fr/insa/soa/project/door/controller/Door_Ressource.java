package fr.insa.soa.project.door.controller;

import java.io.IOException;
import java.time.LocalTime;

import javax.xml.xpath.XPathExpressionException;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.project.door.model.Door_model;
import om2m.Client;
import om2m.Response;
import obix.Obj;
import obix.io.ObixDecoder;



@RestController
public class Door_Ressource {
	
	
	@GetMapping("/rooms/{name}/sensors/door")
	public Door_model retrieve_OM2M(@PathVariable int numFloor, @PathVariable int numRoom) throws IOException, XPathExpressionException {
		Client client = new Client();
		Door_model door_actuator = new Door_model();
		LocalTime currentTime = LocalTime.now();
		
		Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor"+numFloor+"_Manager/ROOM"+numRoom+"/Door/la", "admin:admin");
		
		// System.out.println(resp.getRepresentation());
		String utf8 = resp.getRepresentation().replace("&lt;", "<");
		utf8 = utf8.replace("&quot;", "\"");
		utf8 = utf8.replace("&gt;", ">");
		System.out.println(utf8);
		
		/* Cutting the string */
		int begin = utf8.indexOf("<obj>");
		int end = utf8.indexOf("</obj>");
		String obix_XML = utf8.substring(begin, end+6);
		
		
		System.out.println(obix_XML);
		
		/*using oBIX library*/
		Obj obj = ObixDecoder.fromString(obix_XML);
		
		door_actuator.setLocation(obj.get("location").toString());
		door_actuator.setCategory(obj.get("category").toString());
		door_actuator.setStatus(obj.get("state").toString());
		door_actuator.adddatatoHistory(currentTime .toString() + door_actuator.toString());
		
		return door_actuator;
	}
	
}
