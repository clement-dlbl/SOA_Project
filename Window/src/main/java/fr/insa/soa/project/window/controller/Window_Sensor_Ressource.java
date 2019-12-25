package fr.insa.soa.project.window.controller;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import om2m.Client;
import om2m.Response;
import obix.Obj;
import obix.io.ObixDecoder; 


import fr.insa.soa.project.window.sensor.Window_Sensor;


@RestController
public class Window_Sensor_Ressource {
	
	
	@GetMapping("/{numFloor}/{numRoom}/sensor/window")	
	public Window_Sensor retrieve_OM2M(@PathVariable int numFloor, @PathVariable int numRoom) throws IOException, XPathExpressionException {
		Client client = new Client();
		Window_Sensor window_sens = new Window_Sensor();
		
		Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor"+numFloor+"_Manager/ROOM"+numRoom+"/Window/la", "admin:admin");
		
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
		
		window_sens.setCategory(obj.get("category").toString());
		window_sens.setStatus(obj.get("status").toString());
		window_sens.setFloor(numFloor);
		window_sens.setRoom(numRoom);
		
		return window_sens;
		
		
	}
	
}
