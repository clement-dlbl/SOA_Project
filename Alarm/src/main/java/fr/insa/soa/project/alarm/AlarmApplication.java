package fr.insa.soa.project.alarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.insa.soa.project.alarm.sensor.Alarm_sensor;

@SpringBootApplication
public class AlarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlarmApplication.class, args);
	}

}
