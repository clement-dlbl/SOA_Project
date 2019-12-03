
package fr.project_soa.temperature.controller;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import fr.project_soa.temperature.onem2m_client.Client;
import fr.project_soa.temperature.onem2m_client.Response;
import fr.project_soa.temperature.sensor.Inside_Temp;

import obix.Obj;
import obix.io.ObixDecoder; 


@RestController
public class Temperature_Ressource {
	
	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		
		return "You are in room " + name;
	}
	
	/*@GetMapping("/rooms/{name}/sensors/temperature/{value}")
	public Temperature Check_Window_Open(@PathVariable String name, @PathVariable float value) {
		Temperature Temperature = new Temperature(name, value);
		//return "The Temperature is located in room " + name +"\n. His status is " + Temperature.getStatus();
		return Temperature;
	}*/
	
	
	@GetMapping("/rooms/{name}/sensors/temperature/")
	public Inside_Temp retrieve_OM2M(@PathVariable String name) throws IOException, XPathExpressionException {
		Client client = new Client();
		Inside_Temp inside_temp_sens = new Inside_Temp();
		
		Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Inside_Temp/la", "admin:admin");
		
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
		
		inside_temp_sens.setCategory(obj.get("category").toString());
		inside_temp_sens.setData(obj.get("data"));
		inside_temp_sens.setUnit(obj.get("unit").toString());
		
		return inside_temp_sens;
	}
}
