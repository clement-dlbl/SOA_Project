package fr.insa.soa.project.presence.sensor;

public class Presence_Sensor {
	private String category;
	private String presence;
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

	public String isPresence() {
		return presence;
	}

	public void setPresence(String data) {
		this.presence = data;
	}

	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public void setRoom(int room) {
		this.room = room;
	}

	
}
