package fr.Project_SOA.Window.Sensor;

public class Window_Sensor {
	private String location;
	private String status;
	
	public Window_Sensor(String location, String status) {
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
	
	
}