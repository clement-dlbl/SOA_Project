package fr.insa.soa.project.master.use_case_1;


import java.io.IOException;
import java.time.LocalTime;

import org.eclipse.om2m.commons.resource.ContentInstance;

import fr.insa.soa.project.master.client.Client;
import fr.insa.soa.project.master.client.Response;
import fr.insa.soa.project.master.mapper.Mapper;
import fr.insa.soa.project.master.mapper.MapperInterface;
import fr.insa.soa.project.master.model.Alarm;

public class UC1_main {
	private static final String ORIGINATOR = "admin:admin";
	private int id = 1;
	private MapperInterface mapper = new Mapper();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String triggerAlarm(boolean presence, String status) throws IOException {
		LocalTime currentTime = LocalTime.now();
		Client client = new Client();
		String message = "";
		
		System.out.println("Trigger Alarm");
		
		
		if (currentTime.getHour()>23 || currentTime.getHour()<7 && presence == true) {
			message = "Trigger Alarm";

			ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Alarm.getDataRep("Alarm", "ON"));
			dataInstance.setContentInfo("application/obix:0");
			
			Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Alarm", mapper.marshal(dataInstance), ORIGINATOR, "4");
			System.out.println("[Master : if : ] Alarm triggered");
			System.out.println(res);
		}else {
			message= "Alarm not triggered";

			
			ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Alarm.getDataRep("Alarm", "OFF"));
			dataInstance.setContentInfo("application/obix:0");
			//fr.project_soa.master.client.Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Alarm", mapper.marshal(dataInstance), ORIGINATOR, "4");
			//System.out.println("[Master : else : ] "+res);
		}
		
		System.out.println("It is : " + currentTime);
		return currentTime.toString()+" : "+message;
	}
	
}
