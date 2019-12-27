package fr.insa.soa.project.historic.model;

import java.time.Instant;
import java.util.HashMap;

public final class Historic_model {
	private static HashMap<String, String> historic = new HashMap<String, String>();
	
	
	public static HashMap<String, String> gethistoric() {
		return historic;
	}
	
	public static void addLogToHistoric(String source, String log) {
		Instant instant = Instant.now();
		historic.put(source+","+instant.toString(), log);
	}
	
	
	
}