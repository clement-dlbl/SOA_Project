package fr.insa.soa.project.temperature.sensor;

public class Temperature_sensor {
	private String category;
	private String data;
	private String unit;
	public Temperature_sensor() {
		
	}
	
	public Temperature_sensor(String category, String data) {
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
	public void setUnit(String unit) {
		this.unit =unit;
		
	}
}