package fr.insa.soa.project.alarm.sensor;

import java.util.ArrayList;

public class Alarm_sensor {
	private String location;
	private String category;
	private String status;
	private static ArrayList<String> dataHistory;
	
	public Alarm_sensor() {
		this.location ="";
		this.category = "";
		this.status = "";
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
		dataHistory.add(value);
	}
	public ArrayList<String> getHistoric(){
		return dataHistory;
	}
	public String toString() {
		return this.location + this.category + this.status;
	}

	
}
