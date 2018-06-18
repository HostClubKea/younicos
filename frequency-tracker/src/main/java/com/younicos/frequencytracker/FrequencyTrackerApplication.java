package com.younicos.frequencytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FrequencyTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrequencyTrackerApplication.class, args);
	}
}
