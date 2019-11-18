package fr.project_soa.master.use_case_1;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import fr.project_soa.master.Config;

import java.time.*;

public class UC1_main {
	private int id = 1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String triggerAlarm(boolean presence, String status) {
		LocalTime currentTime = LocalTime.now();
		if (currentTime.getHour()>23 || currentTime.getHour()<7 && presence == true) {
			System.out.println("Trigger Alarm");
			//Post OM2M cin_Alarm
		}
		else {
			System.out.println("Alarm not triggered");
		}
		
		System.out.println("It is : " + currentTime);
		return currentTime.toString();
	}
	
}
