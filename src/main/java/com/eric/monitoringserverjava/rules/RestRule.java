package com.eric.monitoringserverjava.rules;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Document
public class RestRule extends HttpRule {
	private RequestMethod requestMethod;

	public RestRule () {
	}

	public RestRule (String name, int timeoutSeconds, HttpStatus expectedHttpStatus, String expectedResponseBody, RequestMethod requestMethod) {
		super(name, timeoutSeconds, expectedHttpStatus, expectedResponseBody);
		this.requestMethod = requestMethod;
	}

	public RequestMethod getRequestMethod () {
		return requestMethod;
	}

	public void setRequestMethod (RequestMethod requestMethod) {
		this.requestMethod = requestMethod;
	}

	@Override
	public String toString () {
		return "RestRule{" +
		  "requestMethod=" + requestMethod +
		  "} " + super.toString();
	}
}
