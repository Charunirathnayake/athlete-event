package com.vitalHub.athleteevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.vitalHub.athleteevent"})
@SpringBootApplication
public class AthleteEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(AthleteEventApplication.class, args);
	}

}
