package fr.insa.soa.project.window.sensor;

public class Window_Sensor {
	private String category;
	private String status;
	private int floor;
	private int room;
	
	public Window_Sensor() {
		this.category = "";
		this.status = "";
		this.floor = 0;
		this.room = 0;
		
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	
	
}