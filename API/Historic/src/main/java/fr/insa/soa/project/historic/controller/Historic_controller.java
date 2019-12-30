package fr.insa.soa.project.historic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import java.io.StringWriter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.soa.project.historic.model.Historic_model;
import fr.insa.soa.project.historic.model.Log;


@RestController
public class Historic_controller {
	
	@PostMapping(path ="/historic/new", consumes = "application/json", produces = "application/json")
	public void addLogToHistoric(@RequestBody HashMap<String, Object> payload) throws IOException{
		System.out.println("Sevice Log");
		Log log = new Log();
		
		String status = (String) payload.get("status");
		String service = (String) payload.get("source");
		log.setLog(service, "new", status);
		System.out.println("Hist : "+ log.toString());
		Historic_model.addLogToHistoric(log);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/historic")	
	public String retrieveAllHistoric() {
		JsonObjectBuilder mainObj = Json.createObjectBuilder();
		JsonArrayBuilder ja = Json.createArrayBuilder();

		Set cles = Historic_model.gethistoric().keySet();
		Iterator it = cles.iterator();
		int i = 0;
		while (it.hasNext()){
			JsonObjectBuilder job = Json.createObjectBuilder();
			System.out.print(i++);
			Object cle = it.next();
			Log valeur = Historic_model.gethistoric().get(cle);
			job.add(valeur.getDateId(), valeur.getDateValue());
			job.add(valeur.getLogTypeId(), valeur.getLogTypeValue());
			job.add(valeur.getSourceId(), valeur.getSourceValue());
			job.add(valeur.getLogValueId(), valeur.getLogValueValue());
			JsonObject jo = job.build();
			System.out.println(jo.toString());
			ja.add(jo);
			System.out.println(ja.toString());
		
		}
		JsonArray array = ja.build();
		mainObj.add("historic", array);
		JsonObject empObj = mainObj.build();
	    StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(stringWriter);
        jsonWriter.writeObject(empObj);
        jsonWriter.close();
		return stringWriter.toString();
	}
	
}
