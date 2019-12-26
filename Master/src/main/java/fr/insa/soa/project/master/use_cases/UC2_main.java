package fr.insa.soa.project.master.use_cases;


import java.io.IOException;

import org.eclipse.om2m.commons.resource.ContentInstance;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.master.model.Config;
import fr.insa.soa.project.master.model.Window_Sensor;

import om2m.Client;
import om2m.Response;
import om2m_mapper.Mapper;
import om2m_mapper.MapperInterface;

public class UC2_main {
	private static final String ORIGINATOR = "admin:admin";
	private int id = 1;
	private MapperInterface mapper = new Mapper();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String openWindow(Double d, Double temp, String window_status, int floor, int room, RestTemplate restTemplate) throws IOException {
		
		Client client = new Client();
		String message = "";
		String res = "";
		HttpHeaders headers = new HttpHeaders();
	      headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	      headers.setContentType(MediaType.APPLICATION_JSON);

	      // Data attached to the request.
		
		System.out.println("Aeration - Open Window");
		
		
		if (d > temp && window_status == "CLOSE") {
			message = "Window open";

			String status = "OPEN";
			HttpEntity<String> requestBody = new HttpEntity<>(status, headers);
			/*ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Window_Sensor.getDataRep("Floor"+floor+"_Manager/ROOM"+room,"Window", "OPEN"));
			dataInstance.setContentInfo("application/obix:0");
			
			Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor"+floor+"_Manager/ROOM"+room+"/Window", mapper.marshal(dataInstance), ORIGINATOR, "4");
			System.out.println("[Master : if : ] Window Opened");
			System.out.println(res);*/
			res =  restTemplate.postForObject(Config.getWindow_Service() + "/"+floor+"/"+room+"/sensors/alarm/new", requestBody, String.class);

			
		}else {
			message= "Window close";
			String status = "CLOSED";
			
			
			/*ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Window_Sensor.getDataRep("Floor"+floor+"_Manager/ROOM"+room, "Window", "CLOSE"));
			dataInstance.setContentInfo("application/obix:0");
			Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor"+floor+"_Manager/ROOM"+room+"/Window", mapper.marshal(dataInstance), ORIGINATOR, "4");
			System.out.println("[Master : else : ] "+res);*/
			HttpEntity<String> requestBody = new HttpEntity<>(status, headers);
			res =  restTemplate.postForObject(Config.getWindow_Service() + "/"+floor+"/"+room+"/sensors/alarm/new", requestBody, String.class);

		}
		System.out.println(message);
		
		return "TempOut:"+temp+", TempIN:"+d+", "+message;
		
	}
	
}
