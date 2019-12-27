package fr.insa.soa.project.window.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;

import javax.xml.xpath.XPathExpressionException;

import org.eclipse.om2m.commons.resource.ContentInstance;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.window.model.Window_model;
import om2m.Client;
import om2m.Response;
import om2m_mapper.Mapper;
import om2m_mapper.MapperInterface;
import obix.Obj;
import obix.Str;
import obix.io.ObixDecoder;
import obix.io.ObixEncoder;


@RestController
public class Window_controller {
	
	@PostMapping(path ="/{floor}/{room}/sensors/window/new/", consumes = "application/json", produces = "application/json")
	public String pushto_OM2M(@RequestBody String requestUserDetails, @PathVariable int floor, @PathVariable int room) throws IOException, JSONException{
		System.out.println("Sevice Window");
		ContentInstance dataInstance = new ContentInstance();
		MapperInterface mapper = new Mapper();
		final String ORIGINATOR = "admin:admin";
		LocalTime currentTime = LocalTime.now();
		final String HISTORICURL = "http://localhost:8000";
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		
		dataInstance.setContent(getDataRep("Floor"+floor+"_Manager/ROOM"+room,"Window", requestUserDetails));
		dataInstance.setContentInfo("application/obix:0");
		
		Client client = new Client();
		Response res = client .create("http://localhost:8080/~/in-cse/in-name/Floor"+floor+"_Manager/ROOM"+room+"/Window", mapper.marshal(dataInstance), ORIGINATOR, "4");
		System.out.println(res);
		
		
		
		//Add new window status in historic
	    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    

		HashMap<String, Object> json = new HashMap<String, Object>();
		json.put("source", "window");
		json.put("status", requestUserDetails);
		
		HttpEntity<HashMap<String, Object>> requestBody = new HttpEntity<>(json , headers);
		Object authorizationResponse = restTemplate.postForObject(HISTORICURL+"/historic/new", requestBody, Object.class);
		System.out.println("Alarm test : "+(String) authorizationResponse);
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


	@GetMapping("/{numFloor}/{numRoom}/sensor/window")	
	public Window_model retrieve_OM2M(@PathVariable int numFloor, @PathVariable int numRoom) throws IOException, XPathExpressionException {
		Client client = new Client();
		Window_model window_sens = new Window_model();

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
		
		window_sens.setLocation(obj.get("location").toString());
		window_sens.setCategory(obj.get("category").toString());
		window_sens.setStatus(obj.get("state").toString());
		
		return window_sens;
		
		
	}
	
}
