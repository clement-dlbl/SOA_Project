package fr.project_soa.presence.sensor;

public class Presence_Sensor {
	private String category;
	private boolean presence;
	
	public Presence_Sensor() {
		
	}
	
	public Presence_Sensor(String category, boolean presence) {
		this.category = category;
		this.presence = presence;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isPresence() {
		return presence;
	}

	public void setPresence(boolean presence) {
		this.presence = presence;
	}

	
}
