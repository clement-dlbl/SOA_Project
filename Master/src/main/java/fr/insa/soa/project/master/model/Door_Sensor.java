package fr.insa.soa.project.master.model;

import obix.Obj;
import obix.Str;
import obix.io.ObixEncoder;

public class Door_Sensor {
	private String category;
	private String status;
	
	public Door_Sensor() {
		
	}
	
	public Door_Sensor(String category, String status) {
		this.category = category;
		this.status = status;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//Create obix object to insert in oneM2M tree
	public static String getDataRep(String location, String category, String status) {
		// Create the obix object
		Obj obj = new Obj();
		obj.add(new Str("location", location));
		obj.add(new Str("category", category));
		obj.add(new Str("state", status));

		return ObixEncoder.toString(obj);

	}	
}