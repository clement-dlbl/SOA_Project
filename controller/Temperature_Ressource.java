package fr.insa.soa.project.temperature.controller;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.eclipse.om2m.commons.resource.ContentInstance;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.temperature.sensor.Temperature_sensor;
import fr.insa.soa.project.temperature.mapper.Mapper;
import fr.insa.soa.project.temperature.mapper.MapperInterface;
import fr.insa.soa.project.temperature.onem2m_client.Client;
import fr.insa.soa.project.temperature.onem2m_client.Response;
import obix.Obj;
import obix.Str;
import obix.io.ObixDecoder;
import obix.io.ObixEncoder;



@RestController
public class Temperature_Ressource {
	
	private static final String ORIGINATOR = "admin:admin";



	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		
		return "You are in room " + name;
	}

	
	
	@GetMapping("/temperature/extern")
	String retrieve_TempExt() throws JSONException, IOException{
		
		
		MapperInterface mapper = new Mapper();
		final String uri = "https://data.toulouse-metropole.fr/api/records/1.0/search/?dataset=28-station-meteo-carmes&lang=fr&sort=heure_de_paris&fbclid=IwAR3mFob-pcISDNpt1-nnUAtJs8IEZmnsnu8VbkjXwqK_yzAN74_diGnPzKc";
	    
		ContentInstance dataInstance = new ContentInstance();
	    RestTemplate restTemplate = new RestTemplate();
	    //Retreive data from Metropole DB
	    String result = restTemplate.getForObject(uri, String.class);
	    Client client = new Client();
	    
	    ///Parsing of file and get Temp object on json file
	    JSONObject obj = new JSONObject(result);
	    System.out.println(result);
	    
        JSONArray arr = obj.getJSONArray("records");
        for (int i = 0; i < arr.length(); i++) {
            double temp = arr.getJSONObject(i).getJSONObject("fields").getDouble("temperature_en_degre_c");
            	
        		dataInstance.setContent(Temperature_Ressource.getDataRep("temperature", temp, "Celsus"));
        		dataInstance.setContentInfo("application/obix:0");
        		
        		
				Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Outside_Temp", mapper.marshal(dataInstance), ORIGINATOR, "4");
        		System.out.println("[Temp : if : ] Temp  retrieved");
        		//System.out.println(res);*/
        }
		
	    return result;
	}



	public static String getDataRep(String category, Double temp, String unit) {
		// Create the obix object
		Obj obj = new Obj();
		obj.add(new Str("category", category));
		obj.add(new Str("data", String.valueOf(temp)));
		obj.add(new Str("unit", unit));

		return ObixEncoder.toString(obj);

	}
}
	
	