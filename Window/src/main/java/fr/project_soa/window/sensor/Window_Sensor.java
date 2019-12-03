package fr.project_soa.window.sensor;

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
	
	
}