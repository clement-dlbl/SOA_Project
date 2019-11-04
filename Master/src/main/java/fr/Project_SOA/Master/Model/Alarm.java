package fr.Project_SOA.Master.Model;

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
	public void setLocation(String location) {
		this.location = location;
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
