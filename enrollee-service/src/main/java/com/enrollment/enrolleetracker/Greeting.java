package com.enrollment.enrolleetracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author waynes
 *
 *       Purpose:  Provides a welcome message defined in application properties.
 */
@Component
public class Greeting {

	@Value("${welcome.message}")
	private String welcomeMessage;

	public String retrieveWelcomeMessage() {
		
		return welcomeMessage;
	}	
}
