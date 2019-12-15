package fr.insa.soa.project.presence.sensor;

public class Presence {
	private String category;
	private String data;
	public Presence() {
		
	}
	
	public Presence(String category, String data) {
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
}
