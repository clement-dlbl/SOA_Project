package fr.insa.soa.project.door.sensor;

public class Door_actuator {
	private String category;
	private String state;
	public Door_actuator() {
		
	}
	
	public Door_actuator(String category, String state) {
		this.category = category;
		this.state = state;
	}
	
	public String getCategory() {
		return category;
	}
	/*public void setLocation(String category) {
		this.category = category;
	}*/
	public String getStatus() {
		return state;
	}
	public void setStatus(String state) {
		this.state = state;
	}
	
	public String toString() {
		return this.category + this.state;
	}

	public void setCategory(String category) {
		this.category = category;
		
	}
}
