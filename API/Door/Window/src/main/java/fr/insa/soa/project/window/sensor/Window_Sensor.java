package fr.insa.soa.project.window.sensor;

public class Window_Sensor {
	private String location;
	private String category;
	private String status;
	
	public Window_Sensor() {
		this.location="";
		this.category = "";
		this.status = "";
		
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
	
	
	
	
}