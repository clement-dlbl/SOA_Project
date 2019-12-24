package fr.insa.soa.project.alarm.sensor;

public class Alarm_sensor {
	private String category;
	private String status;
	private int floor;
	private int room;
	
	public Alarm_sensor() {
		this.category = "";
		this.status = "";
		this.floor = 0;
		this.room = 0;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setCategory(String category) {
		this.category = category;
		
	}
	public void setFloor(int numFloor) {
		this.floor = numFloor;
	}
	
	public void setRoom(int room) {
		this.room = room;
		
	}
	public String toString() {
		return this.floor + this.room + this.category + this.status;
	}

	
}
