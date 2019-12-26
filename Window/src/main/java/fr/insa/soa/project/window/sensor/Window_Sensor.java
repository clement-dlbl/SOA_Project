package fr.insa.soa.project.window.sensor;

import java.util.ArrayList;

public class Window_Sensor {
	private String location;
	private String category;
	private String status;
	private ArrayList<String> dataHistory;
	
	public Window_Sensor() {
		this.location="";
		this.category = "";
		this.status = "";
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
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void adddatatoHistory(String value) {
		this.dataHistory.add(value);
	}
	public ArrayList<String> getHistoric(){
		return this.dataHistory;
	}
	
	
	
	
}