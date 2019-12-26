package fr.insa.soa.project.master.model;

import java.util.ArrayList;

public class Inside_Temp {
	
	
	
	private ArrayList<String> dataHistory;
	private String location;
	private String category;
	private Double data;
	private String unit;

	public Inside_Temp() {
		
		this.dataHistory = new ArrayList<String>();
		this.location = "";
		this.category = "";
		this.data = 0.;
		this.unit = "";
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation(){
		return location;
	}
	public void setCategory(String category) {
		this.category = category;
		
	}
	public String getCategory() {
		return category;
	}
	public void setData(Double data) {
		this.data = data;
	}
	public Double getData() {
		return this.data;
	}
	
	public void setUnit(String unit) {
		this.unit =unit;
		
	}
	public String getUnit(){
		return unit;
	}
	public void adddatatoHistory(String value){
		this.dataHistory.add(value);
	}
	public ArrayList<String> getHistoric(){
		return this.dataHistory;
	}
	
	public String toString() {
		return this.location + this.category + this.data + this.unit;
	}

	
}
