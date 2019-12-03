package fr.project_soa.master.use_case_1;


import java.io.IOException;
import java.time.LocalTime;

import org.eclipse.om2m.commons.resource.ContentInstance;

import fr.project_soa.master.client.Client;
import fr.project_soa.master.mapper.Mapper;
import fr.project_soa.master.mapper.MapperInterface;
import fr.project_soa.master.model.Alarm;

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
			//Post OM2M cin_Alarm
			//fr.project_soa.master.client.Response resp = client.retrieve("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Presence_Detection/la", "admin:admin");
			//System.out.println("[Master : if : ] : "+resp);
			/*"<m2m:cin xmlns:m2m=\"http://www.onem2m.org/xml/protocols\">\n"+ 
		    "<cnf>message</cnf>\n"+
		    "<con>\n"+
		      "&lt;obj&gt;\n"+
		        "&lt;str name=&quot;category&quot; val=&quot;Alarm &quot;/&gt;\n"+
		        "&lt;int name=&quot;data&quot; val=&quot;ON&quot;/&gt;\n"+
		      "&lt;/obj&gt;\n"+
		    "</con>\n"+
		"</m2m:cin>";*/
			ContentInstance dataInstance = new ContentInstance();
			dataInstance.setContent(Alarm.getDataRep("Alarm", "ON"));
			dataInstance.setContentInfo("application/obix:0");
			
			fr.project_soa.master.client.Response res = client.create("http://localhost:8080/~/in-cse/in-name/Floor1_Manager/Alarm", mapper.marshal(dataInstance), ORIGINATOR, "4");
			System.out.println("[Master : if : ] Alarm triggered");
			System.out.println(res);
		}else {
			message= "Alarm not triggered";
			/*String cinAlarm = "<m2m:cin xmlns:m2m=\"http://www.onem2m.org/xml/protocols\">\n"+ 
				    "<cnf>message</cnf>\n"+
				    "<con>\n"+
				      "&lt;obj&gt;\n"+
				        "&lt;str name=&quot;category&quot; val=&quot;Alarm &quot;/&gt;\n"+
				        "&lt;int name=&quot;data&quot; val=&quot;OFF&quot;/&gt;\n"+
				      "&lt;/obj&gt;\n"+
				    "</con>\n"+
				"</m2m:cin>";*/
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