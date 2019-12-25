package fr.insa.soa.project.master.model;

import obix.Obj;
import obix.Str;
import obix.io.ObixEncoder;

public class Alarm {
	private String location;
	private String status;
	
	public Alarm() {
		
	}
	
	public Alarm(String location, String status) {
		this.location = location;
		this.status = status;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(int floor, int room) {
		this.location = "Floor:"+floor+"/Room"+room;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//Create obix object to insert in oneM2M tree
	public static String getDataRep(String location, String category, String state) {
		// Create the obix object
		Obj obj = new Obj();
		obj.add(new Str("location", location));
		obj.add(new Str("category", category));
		obj.add(new Str("state", state));

		return ObixEncoder.toString(obj);

	}
	
	public String toString() {
		return this.location + this.status;
	}
}
