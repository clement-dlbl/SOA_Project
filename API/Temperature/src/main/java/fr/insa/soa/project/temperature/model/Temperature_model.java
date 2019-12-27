package fr.insa.soa.project.temperature.model;


public class Temperature_model {
	private String location;
	private String category;
	private String data;
	private String unit;
	
	public Temperature_model() {
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
	public void setData(String string) {
		this.data = string;
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
