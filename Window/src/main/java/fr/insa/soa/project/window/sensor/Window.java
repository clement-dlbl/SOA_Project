package fr.insa.soa.project.window.sensor;

public class Window {
	private String category;
	private String data;
	public Window() {
		
	}
	
	public Window(String category, String data) {
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
