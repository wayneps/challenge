package com.enrollment.enrolleetracker.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enrollment.enrolleetracker.Greeting;
import com.enrollment.enrolleetracker.configuration.DefaultConfiguration;

/**
 * @author waynes
 *
 *       Purpose:  Dynamic configuration support and welcome message.
 */
@RestController
public class GreetingController {
	
	@Autowired
	private Greeting greeting;

	@Autowired
	private DefaultConfiguration defaultConfiguration;
	
	@RequestMapping("/apiv1/welcome")
	public String welcome() {
		return greeting.retrieveWelcomeMessage();
	}

	@RequestMapping("/dynamic-configuration")
	public Map<Object, Object> dynamicConfiguration() {
		Map<Object, Object> map = new HashMap<>();
		map.put("message", defaultConfiguration.getMessage());
		map.put("number", defaultConfiguration.getNumber());
		map.put("value", defaultConfiguration.isValue());

		return map;
	}


}
