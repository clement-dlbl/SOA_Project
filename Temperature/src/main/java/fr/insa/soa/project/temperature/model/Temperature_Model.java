package fr.insa.soa.project.temperature.model;

import obix.Obj;
import obix.Str;
import obix.io.ObixEncoder;

public class Temperature_Model {
	
	public String getDataRep(String category, Double string, String unit) {
		// Create the obix object
		Obj obj = new Obj();
		obj.add(new Str("category", category));
		obj.add(new Str("data", String.valueOf(string)));
		obj.add(new Str("unit", unit));

		return ObixEncoder.toString(obj);

	}

}
