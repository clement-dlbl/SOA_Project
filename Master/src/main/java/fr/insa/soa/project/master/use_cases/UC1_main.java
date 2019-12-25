package fr.insa.soa.project.master.use_cases;


import java.io.IOException;
import java.time.LocalTime;

import org.eclipse.om2m.commons.resource.ContentInstance;
import fr.insa.soa.project.master.model.Alarm;

import om2m.Client;
import om2m.Response;
import om2m_mapper.Mapper;
import om2m_mapper.MapperInterface;

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

	public String triggerAlarm(boolean presence, String status, int room, int floor) throws IOException {
		LocalTime currentTime = LocalTime.now();
		Client client = new Client();
		String message = "";
		
		System.out.println("Trigger Alarm");
		
		
		if (currentTime.getHour()>23 || currentTime.getHour()<7 && presence == true) {
			message = "Trigger Alarm";

			ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Alarm.getDataRep("Floor"+floor+"_Manager/ROOM"+room,"Alarm", "ON"));
			dataInstance.setContentInfo("application/obix:0");
			
			Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor"+floor+"_Manager/ROOM"+room+"/Alarm", mapper.marshal(dataInstance), ORIGINATOR, "4");
			System.out.println("[Master : if : ] Alarm triggered");
			System.out.println(res);
		}else {
			message= "Alarm not triggered";

			
			ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Alarm.getDataRep("Floor"+floor+"_Manager/ROOM"+room, "Alarm", "OFF"));
			dataInstance.setContentInfo("application/obix:0");
			
			Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor"+floor+"_Manager/ROOM"+room+"/Alarm", mapper.marshal(dataInstance), ORIGINATOR, "4");
			System.out.println("[Master : if : ] Alarm triggered");
			System.out.println(res);
		}
		
		System.out.println("It is : " + currentTime);
		return currentTime.toString()+" : "+message;
	}
	
}
