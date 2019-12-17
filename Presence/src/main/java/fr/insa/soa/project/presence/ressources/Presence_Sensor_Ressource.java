package fr.insa.soa.project.presence.ressources;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.project.presence.sensor.Presence_Sensor;
import fr.insa.soa.project.presence.onem2m_client.Client;
import fr.insa.soa.project.presence.onem2m_client.Response;

import obix.Obj;
import obix.io.ObixDecoder;




@RestController
public class Presence_Sensor_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String manage_Room_1(@PathVariable String name) {
		return "You are in room " + name;
	}
	
	@GetMapping("/rooms/{name}/sensors/presence")
	public Presence_Sensor check_Window_Open(@PathVariable String name) {
		Presence_Sensor presence_sens = new Presence_Sensor(name, false);
		//return "The presence sensor is located in room " + name +"\n. His status is " + presence_sens.isPresence();
		return presence_sens;
	}
	
	
	
	@GetMapping("/test_OM2M/{name}")
	public Presence_Sensor retrieve_OM2M(@PathVariable String name) throws IOException, XPathExpressionException {
		Client client = new Client();
		Presence_Sensor presence_sens = new Presence_Sensor();
		
		Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Presence_Detection/la", "admin:admin");
		
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
		
		presence_sens.setCategory(obj.get("category").toString());
		presence_sens.setPresence(obj.get("data").toString() == "true");
		
		return presence_sens;
		
		
	}
}
