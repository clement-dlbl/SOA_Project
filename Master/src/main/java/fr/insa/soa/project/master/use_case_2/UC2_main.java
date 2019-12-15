package fr.insa.soa.project.master.use_case_2;


import java.io.IOException;

import org.eclipse.om2m.commons.resource.ContentInstance;

import fr.insa.soa.project.master.client.Client;
import fr.insa.soa.project.master.client.Response;
import fr.insa.soa.project.master.mapper.Mapper;
import fr.insa.soa.project.master.mapper.MapperInterface;
import fr.insa.soa.project.master.model.Window_Sensor;

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

	public String openWindow(double in_temp, double out_temp, String window_status) throws IOException {
		
		Client client = new Client();
		String message = "";
		
		System.out.println("Aeration - Open Window");
		
		
		if (in_temp > out_temp && window_status == "CLOSE") {
			message = "Open Window";

			
			ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Window_Sensor.getDataRep("Window", "OPEN"));
			dataInstance.setContentInfo("application/obix:0");
			
			Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Window_Position", mapper.marshal(dataInstance), ORIGINATOR, "4");
			System.out.println("[Master : if : ] Window Opened");
			System.out.println(res);
		}else {
			message= "Window not opened";
			
			
			ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Window_Sensor.getDataRep("Window", "CLOSE"));
			dataInstance.setContentInfo("application/obix:0");
			//fr.project_soa.master.client.Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Window_Position", mapper.marshal(dataInstance), ORIGINATOR, "4");
			//System.out.println("[Master : else : ] "+res);
		}
		
		return message;
		
	}
	
}
