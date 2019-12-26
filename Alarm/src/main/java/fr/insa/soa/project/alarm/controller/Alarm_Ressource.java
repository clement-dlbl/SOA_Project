package fr.insa.soa.project.alarm.controller;

import java.io.IOException;
import java.time.LocalTime;

import javax.xml.xpath.XPathExpressionException;

import org.eclipse.om2m.commons.resource.ContentInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.project.alarm.sensor.Alarm_sensor;
import om2m.Client;
import om2m.Response;
import om2m_mapper.Mapper;
import om2m_mapper.MapperInterface;
import obix.Obj;
import obix.Str;
import obix.io.ObixDecoder;
import obix.io.ObixEncoder;



@RestController
public class Alarm_Ressource {
	@PostMapping(path ="/{floor}/{room}/sensors/alarm/new/", consumes = "application/json", produces = "application/json")
	public String pushto_OM2M(@RequestBody String requestUserDetails, @PathVariable int floor, @PathVariable int room) throws IOException{
		System.out.println("Sevice Alarm");
		Alarm_sensor alarm_sens = new Alarm_sensor();
		ContentInstance dataInstance = new ContentInstance();
		MapperInterface mapper = new Mapper();
		final String ORIGINATOR = "admin:admin";
		LocalTime currentTime = LocalTime.now();
		
		dataInstance.setContent(getDataRep("Floor"+floor+"_Manager/ROOM"+room,"Alarm", requestUserDetails));
		dataInstance.setContentInfo("application/obix:0");
		
		Client client = new Client();
		Response res = client .create("http://localhost:8080/~/in-cse/in-name/Floor"+floor+"_Manager/ROOM"+room+"/Alarm", mapper.marshal(dataInstance), ORIGINATOR, "4");
		alarm_sens.adddatatoHistory(currentTime.toString()+","+requestUserDetails);
		System.out.println(res);
		System.out.println(currentTime.toString()+","+requestUserDetails);
		return currentTime.toString()+","+requestUserDetails;
	}	
	
	//Create obix object to insert in oneM2M tree
		public static String getDataRep(String location, String category, String state) {
			// Create the obix object
			Obj obj = new Obj();
			obj.add(new Str("location", location));
			obj.add(new Str("category", category));
			obj.add(new Str("state", state));

			return ObixEncoder.toString(obj);

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
		System.out.print("After replace");
		System.out.println(utf8);
		
		/* Cutting the string */
		int begin = utf8.indexOf("<obj>");
		int end = utf8.indexOf("</obj>");
		String obix_XML = utf8.substring(begin, end+6);
		
		System.out.println("Obix object");
		System.out.println(obix_XML);
		
		/*using oBIX library*/
		
		Obj obj = ObixDecoder.fromString(obix_XML);
		
		alarm_sens.setLocation(obj.get("location").toString());
		alarm_sens.setCategory(obj.get("category").toString());
		alarm_sens.setStatus(obj.get("state").toString());
		
		return alarm_sens;
	}
	
	
}
