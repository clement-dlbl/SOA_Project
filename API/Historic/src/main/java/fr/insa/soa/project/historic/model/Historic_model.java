package fr.insa.soa.project.historic.model;

import java.time.Instant;
import java.util.HashMap;

public final class Historic_model {
	private static HashMap<String, Log> historic = new HashMap<String, Log>();
	
	
	public static HashMap<String, Log> gethistoric() {
		return historic;
	}
	
	public static void addLogToHistoric(Log log) {
		Instant instant = Instant.now();
		historic.put(instant.toString(), log);
	}
	
	
	
}