package fr.project_soa.master.use_case_1;


import javax.ws.rs.core.Response;
import fr.project_soa.master.client.Client;

import fr.project_soa.master.Config;

import java.io.IOException;
import java.time.*;

public class UC1_main {
	private int id = 1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String triggerAlarm(boolean presence, String status) throws IOException {
		LocalTime currentTime = LocalTime.now();
		Client client = new Client();
		
		if (currentTime.getHour()>23 || currentTime.getHour()<7 && presence == true) {
			System.out.println("Trigger Alarm");
			//Post OM2M cin_Alarm
			
			String cinAlarm = "<m2m:cin xmlns:m2m=\"http://www.onem2m.org/xml/protocols\">"+ 
				    "<cnf>message</cnf>"+
				    "<con>"+
				      "&lt;obj&gt;"+
				        "&lt;str name=&quot;category&quot; val=&quot;Alarm &quot;/&gt;"+
				        "&lt;int name=&quot;data&quot; val=&quot;ON&quot;/&gt;"+
				      "&lt;/obj&gt;"+
				    "</con>"+
				"</m2m:cin>";
			
			fr.project_soa.master.client.Response resp = client.update("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Alarm", cinAlarm, "admin:admin");
			System.out.println(resp.toString());
			System.out.println("Alarm triggered");
		}
		else {
			System.out.println("Alarm not triggered");
			String cinAlarm = "<m2m:cin xmlns:m2m=\"http://www.onem2m.org/xml/protocols\">"+ 
				    "<cnf>message</cnf>"+
				    "<con>"+
				      "&lt;obj&gt;"+
				        "&lt;str name=&quot;category&quot; val=&quot;Alarm &quot;/&gt;"+
				        "&lt;int name=&quot;data&quot; val=&quot;OFF&quot;/&gt;"+
				      "&lt;/obj&gt;"+
				    "</con>"+
				"</m2m:cin>";
			
			fr.project_soa.master.client.Response resp = client.update("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Alarm", cinAlarm, "admin:admin");
			System.out.println(resp.toString());
		}
		
		System.out.println("It is : " + currentTime);
		return currentTime.toString();
	}
	
}
