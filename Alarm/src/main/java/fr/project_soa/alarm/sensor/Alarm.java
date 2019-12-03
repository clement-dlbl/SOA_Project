package fr.project_soa.alarm.sensor;

public class Alarm {
	private String category;
	private String data;
	public Alarm() {
		
	}
	
	public Alarm(String category, String data) {
		this.category = category;
		this.data = data;
	}
	
	public String getCategory() {
		return category;
	}
	public void setLocation(String category) {
		this.category = category;
	}
	public String getStatus() {
		return data;
	}
	public void setStatus(String data) {
		this.data = data;
	}
	
	public String toString() {
		return this.category + this.data;
	}
}
