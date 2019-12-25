package fr.insa.soa.project.master.use_cases;


import java.io.IOException;

import org.eclipse.om2m.commons.resource.ContentInstance;
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

	public String openWindow(Double d, Double temp, String window_status, int floor, int room) throws IOException {
		
		Client client = new Client();
		String message = "";
		
		System.out.println("Aeration - Open Window");
		
		
		if (d > temp && window_status == "CLOSE") {
			message = "Open Window";

			
			ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Window_Sensor.getDataRep("Floor"+floor+"_Manager/ROOM"+room,"Window", "OPEN"));
			dataInstance.setContentInfo("application/obix:0");
			
			Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor"+floor+"_Manager/ROOM"+room+"/Window", mapper.marshal(dataInstance), ORIGINATOR, "4");
			System.out.println("[Master : if : ] Window Opened");
			System.out.println(res);
		}else {
			message= "Window not opened";
			
			
			ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Window_Sensor.getDataRep("Floor"+floor+"_Manager/ROOM"+room, "Window", "CLOSE"));
			dataInstance.setContentInfo("application/obix:0");
			Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor"+floor+"_Manager/ROOM"+room+"/Window", mapper.marshal(dataInstance), ORIGINATOR, "4");
			System.out.println("[Master : else : ] "+res);
		}
		System.out.println(message);
		return message;
		
	}
	
}
