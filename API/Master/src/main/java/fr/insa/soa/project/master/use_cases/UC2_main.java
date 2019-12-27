package fr.insa.soa.project.master.use_cases;


import java.io.IOException;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import fr.insa.soa.project.master.model.Config;

public class UC2_main {

	public String openWindow(Double d, Double temp, String window_status, int floor, int room, RestTemplate restTemplate) throws IOException {
		
		String status = "";
		String res = "";
		HttpHeaders headers = new HttpHeaders();
	      headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	      headers.setContentType(MediaType.APPLICATION_JSON);

	      // Data attached to the request.
		
		System.out.println("Aeration - Open Window");
		
		
		if (d > temp && window_status == "CLOSE") {
			status = "OPEN";
			HttpEntity<String> requestBody = new HttpEntity<>(status, headers);
			res =  restTemplate.postForObject(Config.getWindow_Service() + "/"+floor+"/"+room+"/sensors/window/new", requestBody, String.class);
			
		}else {
			status = "CLOSED";
			HttpEntity<String> requestBody = new HttpEntity<>(status, headers);
			res =  restTemplate.postForObject(Config.getWindow_Service() + "/"+floor+"/"+room+"/sensors/window/new/", requestBody, String.class);

		}
		System.out.println(res);
		
		return status;
		
	}
	
}
