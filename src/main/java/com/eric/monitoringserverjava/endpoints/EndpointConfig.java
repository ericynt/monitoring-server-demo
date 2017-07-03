package com.eric.monitoringserverjava.endpoints;

import org.springframework.data.annotation.Id;

/**
 *
 */
public abstract class EndpointConfig {
	@Id
	private String id;
	private String name;
	private EndpointType endpointType;

	public enum EndpointType {REST}

	public EndpointConfig () {
	}

	public EndpointConfig (String id, String name, EndpointType endpointType) {
		this.id = id;
		this.name = name;
		this.endpointType = endpointType;
	}

	@Override
	public String toString () {
		return "EndpointConfig{" +
		  "id='" + id + '\'' +
		  ", name='" + name + '\'' +
		  ", endpointType=" + endpointType +
		  '}';
	}
}
