package com.enrollment.enrolleetracker.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author waynes
 *
 *       Purpose:  Configuration used for dynamic configuration.
 */
@Component
@ConfigurationProperties("default")
public class DefaultConfiguration {
	private boolean value;
	private String message;
	private int number;

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
