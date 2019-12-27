package fr.insa.soa.project.presence.model;



public class Presence_model {
	private String location;
	private String category;
	private String state;
	
	public Presence_model() {
		this.location = "";
		this.category = "";
		this.state = "";
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

	public String getStatus() {
		return state;
	}

	public void setStatus(String string) {
		this.state = string;
	}

	
}
