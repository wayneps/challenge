package com.enrollment.enrolleetracker;

import com.enrollment.enrolleetracker.service.SeedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * @author waynes
 *
 *       Purpose: 	Main class for application bootstrap provided by SpringBoot.
 *       			The class also seeds database data when running in the "development" profile.
 */
@SpringBootApplication
public class EnrolleeTrackerApplication {

	@Autowired
	private SeedDataService seedDataService;
	
	public static void main(String[] args) {
		SpringApplication.run(EnrolleeTrackerApplication.class, args);

	}

	@Profile("dev")
	@Bean
	public void seedData() {
		seedDataService.seed();
	}
	
}
