package fr.insa.soa.project.alarm.controller;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import fr.insa.soa.project.alarm.sensor.Alarm_sensor;
import fr.insa.soa.project.alarm.onem2m_client.Client;
import fr.insa.soa.project.alarm.onem2m_client.Response;
import obix.Obj;
import obix.io.ObixDecoder;



@RestController
public class Alarm_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		
		return "You are in room " + name;
	}
	
	
	@GetMapping("/rooms/{name}/sensors/alarm")
	public Alarm_sensor retrieve_OM2M(@PathVariable String name) throws IOException, XPathExpressionException {
		Client client = new Client();
		Alarm_sensor alarm_sens = new Alarm_sensor();
		
		Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Alarm/la", "admin:admin");
		
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
		
		alarm_sens.setCategory(obj.get("category").toString());
		//alarm_sens.setPresence(obj.get("data").toString());
		
		return alarm_sens;
	}
	
}