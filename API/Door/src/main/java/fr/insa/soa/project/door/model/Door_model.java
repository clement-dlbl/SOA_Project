package fr.insa.soa.project.door.model;


public class Door_model {
	private String location;
	private String category;
	private String state;
	
	
	public Door_model() {
		this.location ="";
		this.category = "";
		this.state = "";
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
	public String getStatus() {
		return state;
	}
	public void setStatus(String state) {
		this.state = state;
	}
	
	public String toString() {
		return this.location + this.category + this.state;
	}
}
