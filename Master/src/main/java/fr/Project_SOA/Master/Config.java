package fr.Project_SOA.Master;

public class Config {
	static private String Temperature_Service 	= "http://localhost:8082";
	static private String Window_Service 		= "http://localhost:8083";
	static private String Door_Service 			= "http://localhost:8084";
	static private String Presence_Service 		= "http://localhost:8085";
	static private String Alarm_Service 		= "http://localhost:8086";
	//static private String Light_Service 		= "http://localhost:8087";
	
	public static String getTemperature_Service() {
		return Temperature_Service;
	}
	public static void setTemperature_Service(String temperature_Service) {
		Temperature_Service = temperature_Service;
	}
	public static String getWindow_Service() {
		return Window_Service;
	}
	public static void setWindow_Service(String window_Service) {
		Window_Service = window_Service;
	}
	public static String getDoor_Service() {
		return Door_Service;
	}
	public static void setDoor_Service(String door_Service) {
		Door_Service = door_Service;
	}
	public static String getPresence_Service() {
		return Presence_Service;
	}
	public static void setPresence_Service(String presence_Service) {
		Presence_Service = presence_Service;
	}
	public static String getAlarm_Service() {
		return Alarm_Service;
	}
	public static void setAlarm_Service(String alarm_Service) {
		Alarm_Service = alarm_Service;
	}
}
