package fr.insa.soa.project.alarm.sensor;

public class Alarm_sensor {
	private String category;
	private String data;
	public Alarm_sensor() {
		
	}
	
	public Alarm_sensor(String category, String data) {
		this.category = category;
		this.data = data;
	}
	
	public String getCategory() {
		return category;
	}
	/*public void setLocation(String category) {
		this.category = category;
	}*/
	public String getStatus() {
		return data;
	}
	public void setStatus(String data) {
		this.data = data;
	}
	
	public String toString() {
		return this.category + this.data;
	}

	public void setCategory(String category) {
		this.category = category;
		
	}
}
