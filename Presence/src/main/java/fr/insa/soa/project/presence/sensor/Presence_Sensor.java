package fr.insa.soa.project.presence.sensor;

import java.util.ArrayList;

public class Presence_Sensor {
	private String location;
	private String category;
	private String state;
	private ArrayList<String> dataHistory;
	
	public Presence_Sensor() {
		this.location = "";
		this.category = "";
		this.state = "";
		this.dataHistory = new ArrayList<String>();
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return state;
	}

	public void setStatus(String state) {
		this.state = state;
	}
	public void adddatatoHistory(String value){
		this.dataHistory.add(value);
	}
	public ArrayList<String> getHistoric(){
		return this.dataHistory;
	}
	public String toString(){
		return this.location + this.category + this.state;
	}

	
}
