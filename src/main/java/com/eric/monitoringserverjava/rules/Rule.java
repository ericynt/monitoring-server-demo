package com.eric.monitoringserverjava.rules;

import org.springframework.data.annotation.Id;

/**
 *
 */
public abstract class Rule {
	@Id
	private String id;
	private String name;
	private int timeoutSeconds;

	public Rule () {
	}

	public Rule (String id, String name, int timeoutSeconds) {
		this.id = id;
		this.name = name;
		this.timeoutSeconds = timeoutSeconds;
	}

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getTimeoutSeconds () {
		return timeoutSeconds;
	}

	public void setTimeoutSeconds (int timeoutSeconds) {
		this.timeoutSeconds = timeoutSeconds;
	}

	@Override
	public String toString () {
		return "Rule{" +
		  "id='" + id + '\'' +
		  ", name='" + name + '\'' +
		  ", timeoutSeconds=" + timeoutSeconds +
		  '}';
	}
}
