package com.eric.monitoringserverjava.endpoints;

/**
 *
 */
public class RestEndpointConfig extends EndpointConfig {
	private String host;
	private int port;
	private String path;
	public RestEndpointConfig () {
		super();
	}

	public RestEndpointConfig (String id, String name, EndpointType endpointType, String host, int port, String path) {
		super(id, name, endpointType);
		this.host = host;
		this.port = port;
		this.path = path;
	}

	public String getHost () {
		return host;
	}

	public void setHost (String host) {
		this.host = host;
	}

	public int getPort () {
		return port;
	}

	public void setPort (int port) {
		this.port = port;
	}

	public String getPath () {
		return path;
	}

	public void setPath (String path) {
		this.path = path;
	}

	@Override
	public String toString () {
		return "RestEndpointConfig{" +
		  "host='" + host + '\'' +
		  ", port=" + port +
		  ", path='" + path + '\'' +
		  "} " + super.toString();
	}
}
