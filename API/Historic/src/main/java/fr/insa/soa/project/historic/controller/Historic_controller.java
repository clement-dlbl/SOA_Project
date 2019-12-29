package fr.insa.soa.project.historic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
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
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		Set cles = Historic_model.gethistoric().keySet();
		Iterator it = cles.iterator();
		int i = 0;
		while (it.hasNext()){
		   System.out.print(i++);
		   Object cle = it.next();
		   Log valeur = Historic_model.gethistoric().get(cle);
		   jo.put(valeur.getDateId(), valeur.getDateValue());
		   jo.put(valeur.getLogTypeId(), valeur.getLogTypeValue());
		   jo.put(valeur.getSourceId(), valeur.getSourceValue());
		   jo.put(valeur.getLogValueId(), valeur.getLogValueValue());
		   System.out.println(jo.toString());
		   ja.put(jo);
		
		}
		JSONObject mainObj = new JSONObject();
		mainObj.put("historic", ja);
		System.out.println(mainObj.toString());
		return mainObj.toString();
	}
	
}
