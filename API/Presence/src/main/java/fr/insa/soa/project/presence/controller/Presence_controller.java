package fr.insa.soa.project.presence.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.project.presence.model.Presence_model;
import om2m.Client;
import om2m.Response;

import obix.Obj;
import obix.io.ObixDecoder;

@RestController
public class Presence_controller {
	
	@GetMapping("/{numFloor}/{numRoom}/sensors/presence")
	public Presence_controller check_Window_Open(@PathVariable int numFloor, @PathVariable int numRoom) throws IOException {
		Presence_controller presence_sens = new Presence_controller();
		Client client = new Client();
		
		Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor"+numFloor+"_Manager/ROOM"+numRoom+"/Presence/la", "admin:admin");
		
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
		
		presence_sens.setLocation(obj.get("location").toString());
		presence_sens.setCategory(obj.get("category").toString());
		presence_sens.setStatus(obj.get("state").toString());
		
		return presence_sens;
		
		
	}
}
