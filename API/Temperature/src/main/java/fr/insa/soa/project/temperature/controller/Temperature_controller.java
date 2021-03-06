package fr.insa.soa.project.temperature.controller;


import java.io.IOException;
import java.util.HashMap;

import org.eclipse.om2m.commons.resource.ContentInstance;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.temperature.model.Temperature_model;
import obix.Obj;
import obix.Str;
import obix.io.ObixDecoder;
import obix.io.ObixEncoder;
import om2m.Client;
import om2m.Response;
import om2m_mapper.Mapper;
import om2m_mapper.MapperInterface;




@RestController
public class Temperature_controller {
	
	private static final String ORIGINATOR = "admin:admin";
	private static final int Min = 15;
	private static final int Max = 29;
	private Client client = new Client();
	private ContentInstance dataInstance = new ContentInstance();
	private MapperInterface mapper = new Mapper();


	
	
	//Generate random temperature values
	@GetMapping("/{numFloor}/{numRoom}/sensors/temperature/inside/new")
	Double create_TempInt(@PathVariable int numFloor, @PathVariable int numRoom) throws IOException{
		double temp = Min + (Math.random() * (Max - Min));
	    
	    double temp_round = Math.round(temp * 10.0) / 10.0;
	    
	    System.out.println("temp : "+ temp_round);
		this.dataInstance.setContent(getDataRep("Floor"+numFloor+"_Manager/ROOM"+numRoom, "temperature", temp_round, "Celsus"));
		this.dataInstance.setContentInfo("application/obix:0");
		
		
		Response res = this.client.create("http://localhost:8080/~/in-cse/in-name/Floor"+numFloor+"_Manager/ROOM"+numRoom+"/Inside_Temp", this.mapper.marshal(dataInstance), ORIGINATOR, "4");
		System.out.println("[Temp : ] Temp  created in oneM2M");
		System.out.println("[Temp : ] "+res);
		
		final String HISTORICURL = "http://localhost:8000";
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		
		
	    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	    

		HashMap<String, Object> json = new HashMap<String, Object>();
		json.put("source", "temperatureIn");
		json.put("status", String.valueOf(temp_round));
		
		HttpEntity<HashMap<String, Object>> requestBody = new HttpEntity<>(json , headers);
		Object authorizationResponse = restTemplate.postForObject(HISTORICURL+"/historic/new", requestBody, Object.class);
		System.out.println("Window test : "+(String) authorizationResponse);
		return temp_round;
	}
	
	@GetMapping("/{numFloor}/{numRoom}/sensors/temperature/inside")
	Temperature_model retrieve_TempInt(@PathVariable int numFloor, @PathVariable int numRoom) throws IOException{
		Temperature_model temp_sens = new Temperature_model();

		Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor"+numFloor+"_Manager/ROOM"+numRoom+"/Inside_Temp/la", "admin:admin");
		
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
		
		temp_sens.setLocation(obj.get("location").toString());
		temp_sens.setCategory(obj.get("category").toString());
		temp_sens.setData(obj.get("data").toString());
		temp_sens.setUnit(obj.get("unit").toString());
		
		return temp_sens;
	}
		
	
	
	
	public String getDataRep(String location, String category, Double data, String unit) {
		// Create the obix object
		Obj obj = new Obj();
		obj.add(new Str("location", location));
		obj.add(new Str("category", category));
		obj.add(new Str("data", String.valueOf(data)));
		obj.add(new Str("unit", unit));

		return ObixEncoder.toString(obj);

	}
	
}
	
	