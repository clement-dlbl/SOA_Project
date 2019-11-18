package fr.project_soa.presence.sensor;

public class Presence_Sensor {
	private String location;
	private boolean presence;
	
	public Presence_Sensor() {
		
	}
	
	public Presence_Sensor(String location, boolean presence) {
		this.location = location;
		this.presence = presence;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isPresence() {
		return presence;
	}

	public void setPresence(boolean presence) {
		this.presence = presence;
	}

	
}
