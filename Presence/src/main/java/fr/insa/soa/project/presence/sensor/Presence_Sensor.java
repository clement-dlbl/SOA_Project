package fr.insa.soa.project.presence.sensor;

public class Presence_Sensor {
	private String location;
	private String category;
	private String state;
	
	public Presence_Sensor() {}
	
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

	public void setStatus(String state) {
		this.state = state;
	}
	public String toString(){
		return this.location + this.category + this.state;
	}

	
}
