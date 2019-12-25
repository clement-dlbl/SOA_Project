package fr.insa.soa.project.master.model;

public class Inside_Temp {
	private String location;
	private String category;
	private Double data;
	private String unit; 
	
	
	public Inside_Temp() {
		
		this.location ="";
		this.category = "";
		this.data = 0.;
		this.unit = "";
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getData() {
		return data;
	}
	public void setData(Double data) {
		this.data = data;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String toString() {
		return this.category + this.data + this.unit;
	}
}
