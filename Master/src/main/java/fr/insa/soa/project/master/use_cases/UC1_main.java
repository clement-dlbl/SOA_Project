package fr.insa.soa.project.master.use_cases;


import java.io.IOException;
import java.time.LocalTime;


import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


import fr.insa.soa.project.master.model.Config;

public class UC1_main {
	private int id = 1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String triggerAlarm(boolean presence, String status, int room, int floor, RestTemplate restTemplate) throws IOException, JSONException {
		LocalTime currentTime = LocalTime.now();
		String status1 = "";
		String res = "";
		
		System.out.println("Alarm");
		
		 HttpHeaders headers = new HttpHeaders();
	      headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	      headers.setContentType(MediaType.APPLICATION_JSON);

	      // Data attached to the request.
	      System.out.println("It is : " + currentTime);
		if (currentTime.getHour()>23 || currentTime.getHour()<7 && presence == true) {
			System.out.println("IF");
			status1 = "ON";
			HttpEntity<String> requestBody = new HttpEntity<>(status1, headers);
			res =  restTemplate.postForObject(Config.getAlarm_Service() + "/"+floor+"/"+room+"/sensors/alarm/new", requestBody, String.class);
			System.out.println("AlarmIF");
		}else {
			System.out.println("ELSE");
			status1= "OFF";
			HttpEntity<String> requestBody = new HttpEntity<>(status1, headers);
			res = restTemplate.postForObject(Config.getAlarm_Service() + "/"+floor+"/"+room+"/sensors/alarm/new", requestBody, String.class);
			System.out.println("AlarmElse:" +res);
		}
		
		
		return status1;
	}
	
}
