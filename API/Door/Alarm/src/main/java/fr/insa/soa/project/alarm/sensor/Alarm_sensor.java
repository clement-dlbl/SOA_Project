package fr.insa.soa.project.alarm.sensor;

public class Alarm_sensor {
	private String location;
	private String category;
	private String status;
	
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
	public String toString() {
		return this.location + this.category + this.status;
	}

	
}
