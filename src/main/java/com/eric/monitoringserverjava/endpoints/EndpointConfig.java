package com.eric.monitoringserverjava.endpoints;

import org.springframework.data.annotation.Id;

import java.net.URL;

/**
 *
 */
public abstract class EndpointConfig {
	@Id
	private String id;
	private String name;
	private EndpointType endpointType;
	private Protocol protocol;

	public enum EndpointType {REST}

	public enum Protocol {HTTP, HTTPS}

	EndpointConfig () {
	}

	EndpointConfig (String id, String name, EndpointType endpointType, Protocol protocol) {
		this.id = id;
		this.name = name;
		this.endpointType = endpointType;
		this.protocol = protocol;
	}

	public abstract URL getUrl ();

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

	public EndpointType getEndpointType () {
		return endpointType;
	}

	public Protocol getProtocol () {
		return protocol;
	}

	public void setProtocol (Protocol protocol) {
		this.protocol = protocol;
	}

	@Override
	public String toString () {
		return "EndpointConfig{" +
		  "id='" + id + '\'' +
		  ", name='" + name + '\'' +
		  ", endpointType=" + endpointType +
		  ", protocol=" + protocol +
		  '}';
	}
}
