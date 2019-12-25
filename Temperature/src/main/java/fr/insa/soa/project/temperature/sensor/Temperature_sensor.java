package fr.insa.soa.project.temperature.sensor;

public class Temperature_sensor {
	private String location;
	private String category;
	private String data;
	private String unit;
	
	public Temperature_sensor() {
		this.location = "";
		this.category = "";
		this.data = "";
		this.unit = "";
		
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation(){
		return location;
	}
	public void setCategory(String category) {
		this.category = category;
		
	}
	public String getCategory() {
		return category;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		return this.data;
	}
	
	public void setUnit(String unit) {
		this.unit =unit;
		
	}
	public String getUnit(){
		return unit;
	}
	
	
	public String toString() {
		return this.location + this.category + this.data + this.unit;
	}

	
}
