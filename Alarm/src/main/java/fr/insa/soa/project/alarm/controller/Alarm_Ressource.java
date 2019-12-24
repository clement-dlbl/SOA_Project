package fr.insa.soa.project.alarm.controller;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import om2m.Client;
import om2m.Response;

import obix.Obj;
import obix.io.ObixDecoder;

import fr.insa.soa.project.alarm.sensor.Alarm_sensor;



@RestController
public class Alarm_Ressource {
	
	@GetMapping("/{numFloor}/{numRoom}")
	public String Manage_Room(@PathVariable int numFloor, @PathVariable int numRoom) {
		
		return "You are in room " + numRoom + "on the floor" + numFloor ;
	}
	
	
	@GetMapping("/{numFloor}/{numRoom}/sensors/alarm")
	public Alarm_sensor retrieve_OM2M(@PathVariable int numFloor, @PathVariable int numRoom) throws IOException, XPathExpressionException {
		Client client = new Client();
		Alarm_sensor alarm_sens = new Alarm_sensor();
		
		Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor"+numFloor+"_Manager/ROOM"+numRoom+"/Alarm/la", "admin:admin");
		
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
		alarm_sens.setStatus(obj.get("data").toString());
		alarm_sens.setFloor(numFloor);
		alarm_sens.setRoom(numRoom);
		
		return alarm_sens;
	}
	
}
