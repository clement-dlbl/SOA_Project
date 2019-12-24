package fr.insa.soa.project.presence.sensor;

public class Presence_Sensor {
	private String category;
	private boolean presence;
	private int floor;
	private int room;
	
	public Presence_Sensor() {
		
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
	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public void setRoom(int room) {
		this.room = room;
	}

	
}
