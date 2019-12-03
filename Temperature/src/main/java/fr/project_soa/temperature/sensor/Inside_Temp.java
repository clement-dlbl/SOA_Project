package fr.project_soa.temperature.sensor;

public class Inside_Temp {
	private String category;
	private float data;
	private String unit; 
	
	public Inside_Temp() {
		
	}
	
	public Inside_Temp(String category, float data, String unit) {
		this.category = category;
		this.data = data;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getData() {
		return data;
	}
	public void setData(float data) {
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
