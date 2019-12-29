package fr.insa.soa.project.historic.model;

import java.time.Instant;

public class Log {
	private String dateId;
	private String dateValue;
	private String sourceId;
	private String sourceValue;
	private String logTypeId;
	private String logTypeValue;
	private String logValueId;
	private String logValueValue;
	
	public Log(){
		this.dateId= "date";
		this.dateValue= "";
		this.sourceId= "source";
		this.sourceValue= "";
		this.logTypeId= "logType";
		this.logTypeValue= "";
		this.logValueId= "logValue";
		this.logValueValue= "";
	}
	
	public String getDateId() {
		return this.dateId;
	}
	public String getDateValue() {
		return this.dateValue;
	}
	public String getSourceId() {
		return this.sourceId;
	}
	public String getSourceValue() {
		return this.sourceValue;
	}
	public String getLogTypeId() {
		return this.logTypeId;
	}

	public String getLogTypeValue() {
		return this.logTypeValue;
	}
	public String getLogValueId() {
		return this.logValueId;
	}
	public String getLogValueValue() {
		return this.logValueValue;
	}
	
	
	public void setLog(String service, String logType, String logValue) {
		Instant instant = Instant.now();
		this.dateValue= instant.toString();
		this.sourceValue= service;
		this.logTypeValue= logType;
		this.logValueValue= logValue;
	}
	
	
	public String toString(){
		return this.dateId + 
		this.dateValue +
		this.sourceId +
		this.sourceValue +
		this.logTypeId +
		this.logTypeValue +
		this.logValueId +
		this.logValueValue;
	}

}
