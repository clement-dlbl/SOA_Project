package fr.insa.soa.project.door.sensor;

import java.util.ArrayList;

public class Door_actuator {
	private String location;
	private String category;
	private String state;
	private ArrayList<String> dataHistory;
	
	
	public Door_actuator() {
		this.location ="";
		this.category = "";
		this.state = "";
		this.dataHistory = new ArrayList<String>();
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setCategory(String category) {
		this.category = category;
		
	}
	public String getCategory() {
		return category;
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
	
	public String toString() {
		return this.location + this.category + this.state;
	}
}
