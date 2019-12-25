package fr.insa.soa.project.presence.sensor;

public class Presence_Sensor {
	private String category;
	private String state;
	private int floor;
	private int room;
	
	public Presence_Sensor() {}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getState() {
		return state;
	}

	public void setStatus(String state) {
		this.state = state;
	}

	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public void setRoom(int room) {
		this.room = room;
	}

	
}
