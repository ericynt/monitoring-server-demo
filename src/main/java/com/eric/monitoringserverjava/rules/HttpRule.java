package com.eric.monitoringserverjava.rules;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

/**
 *
 */
@Document
public class HttpRule extends Rule {
	private HttpStatus expectedHttpStatus;
	private String expectedResponseBody;

	public HttpRule () {
		super();
	}

	public HttpRule (String name, int timeoutSeconds, HttpStatus expectedHttpStatus, String expectedResponseBody) {
		super(name, timeoutSeconds);
		this.expectedHttpStatus = expectedHttpStatus;
		this.expectedResponseBody = expectedResponseBody;
	}

	public HttpStatus getExpectedHttpStatus () {
		return expectedHttpStatus;
	}

	public void setExpectedHttpStatus (HttpStatus expectedHttpStatus) {
		this.expectedHttpStatus = expectedHttpStatus;
	}

	public String getExpectedResponseBody () {
		return expectedResponseBody;
	}

	public void setExpectedResponseBody (String expectedResponseBody) {
		this.expectedResponseBody = expectedResponseBody;
	}

	@Override
	public String toString () {
		return "HttpRule{" +
		  "expectedHttpStatus=" + expectedHttpStatus +
		  ", expectedResponseBody='" + expectedResponseBody + '\'' +
		  "} " + super.toString();
	}
}
