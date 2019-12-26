package fr.insa.soa.project.master.model;

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
	
	public String toString() {
		return this.location + this.status;
	}
}
