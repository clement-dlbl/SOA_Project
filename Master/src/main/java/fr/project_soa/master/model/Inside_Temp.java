package fr.project_soa.master.model;

public class Inside_Temp {
	private String category;
	private double data;
	private String unit; 
	
	public Inside_Temp() {
		
	}
	
	public Inside_Temp(String category, double data, String unit) {
		this.category = category;
		this.data = data;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getData() {
		return data;
	}
	public void setData(double data) {
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
