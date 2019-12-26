package fr.insa.soa.project.alarm.sensor;

import java.util.ArrayList;

public class Alarm_sensor {
	private String location;
	private String category;
	private String status;
	private ArrayList<String> dataHistory;
	
	public Alarm_sensor() {
		this.location ="";
		this.category = "";
		this.status = "";
		this.dataHistory = new ArrayList<String>();
	}
	public void setCategory(String category) {
		this.category = category;
		
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void adddatatoHistory(String value){
		this.dataHistory.add(value);
	}
	public ArrayList<String> getHistoric(){
		return this.dataHistory;
	}
	public String toString() {
		return this.location + this.category + this.status;
	}

	
}
