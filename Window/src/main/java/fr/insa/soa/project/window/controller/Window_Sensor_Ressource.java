package fr.insa.soa.project.window.controller;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.project.window.onem2m_client.Client;
import fr.insa.soa.project.window.onem2m_client.Response;
import fr.insa.soa.project.window.sensor.Window_Sensor;

import obix.Obj;
import obix.io.ObixDecoder; 


@RestController
public class Window_Sensor_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		return "You are in room " + name;
	}
	
	/*@GetMapping("/rooms/{name}/sensors/window_sensor")
	public String Check_Window_Open(@PathVariable String name) {
		Window_Sensor win_sens = new Window_Sensor(name, "closed");
		return "The window sensor is located in room " + name +"\n His status is " + win_sens.getStatus();
	}*/
	
	
	@GetMapping("/rooms/{name}/sensors/window_sensor")	
	public Window_Sensor retrieve_OM2M(@PathVariable String name) throws IOException, XPathExpressionException {
		Client client = new Client();
		Window_Sensor window_sens = new Window_Sensor();
		
		Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Window_Position/la", "admin:admin");
		
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
		window_sens.setStatus(obj.get("data").toString());
		
		return window_sens;
		
		
	}
	
}
