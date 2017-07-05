package com.eric.monitoringserverjava.endpoints;

import org.springframework.data.mongodb.core.mapping.Document;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
@Document
public class RestEndpointConfig extends EndpointConfig {
	private String host;
	private int port;
	private String path;

	public RestEndpointConfig () {
		super();
	}

	@Override
	public URL getUrl () {
		URL url = null;

		try {
			url = new URL(getProtocol().toString().toLowerCase() + "://" + host + ':' + port + '/' + path);
		} catch (MalformedURLException e) {
			// TODO add logging
		}

		return url;
	}

	public RestEndpointConfig (String id, String name, Protocol protocol, String host, int port, String path) {
		super(id, name, EndpointType.REST, protocol);
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
