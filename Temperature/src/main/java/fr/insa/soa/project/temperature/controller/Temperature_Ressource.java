package fr.insa.soa.project.temperature.controller;

import java.io.IOException;
/*import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;*/

import org.eclipse.om2m.commons.resource.ContentInstance;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.project.temperature.mapper.Mapper;
import fr.insa.soa.project.temperature.mapper.MapperInterface;
import fr.insa.soa.project.temperature.model.Temperature_Model;
import fr.insa.soa.project.temperature.onem2m_client.Client;
import fr.insa.soa.project.temperature.onem2m_client.Response;
import fr.insa.soa.project.temperature.sensor.Temperature_sensor;
import obix.Obj;
import obix.io.ObixDecoder;



@RestController
public class Temperature_Ressource {
	
	private static final String ORIGINATOR = "admin:admin";
	private static final int Min = 0;
	private static final int Max = 50;
	private Temperature_Model temperatureModel = new Temperature_Model();
	private Client client = new Client();
	private ContentInstance dataInstance = new ContentInstance();
	private MapperInterface mapper = new Mapper();

	@GetMapping("/rooms/{name}")
	public String Manage_Room_1(@PathVariable String name) {
		
		return "You are in room " + name;
	}
	
	
	//Generate random temperature values
	@GetMapping("/rooms/{idRoom}/sensors/temperature/inside/new")
	Double create_TempInt() throws IOException{
		double temp = Min + (Math.random() * (Max - Min));
	    
	    double temp_round = Math.round(temp * 10.0) / 10.0;
	    
	    System.out.println("temp : "+ temp_round);
		this.dataInstance.setContent(temperatureModel.getDataRep("temperature_int", temp_round, "Celsus"));
		this.dataInstance.setContentInfo("application/obix:0");
		
		
		Response res = this.client.create("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Inside_Temp", this.mapper.marshal(dataInstance), ORIGINATOR, "4");
		System.out.println("[Temp : ] Temp  created in oneM2M");
		System.out.println("[Temp : ] "+res);
		return temp_round;
	}
	
	@GetMapping("/rooms/{idRoom}/sensors/temperature/inside")
	Temperature_sensor retrieve_TempInt() throws IOException{
		Temperature_sensor temp_sens = new Temperature_sensor();

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
		
		temp_sens.setCategory(obj.get("category").toString());
		temp_sens.setStatus(obj.get("data").toString());
		temp_sens.setUnit(obj.get("unit").toString());
		
		return temp_sens;
	}
		
	
}
	
	