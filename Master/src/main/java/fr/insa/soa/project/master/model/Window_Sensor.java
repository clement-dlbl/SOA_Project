package fr.insa.soa.project.master.model;

import obix.Obj;
import obix.Str;
import obix.io.ObixEncoder;

public class Window_Sensor {
	private String category;
	private String status;
	
	public Window_Sensor() {
		
	}
	
	public Window_Sensor(String category, String status) {
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
	public static String getDataRep(String category, String status) {
		// Create the obix object
		Obj obj = new Obj();
		obj.add(new Str("category", category));
		obj.add(new Str("status", status));

		return ObixEncoder.toString(obj);

	}	
}