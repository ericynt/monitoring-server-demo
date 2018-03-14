package com.eric.monitoringserverjava.rules;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Document
public class Rule {
    @Id
    private String id;
    private RequestMethod requestMethod;
    private HttpStatus expectedHttpStatus;
    private String expectedResponseBody;
    private String name;
    private int timeoutSeconds;
    
    public Rule (String id, RequestMethod requestMethod, HttpStatus expectedHttpStatus, String expectedResponseBody,
				 String name, int timeoutSeconds) {
        this.id = id;
        this.requestMethod = requestMethod;
        this.expectedHttpStatus = expectedHttpStatus;
        this.expectedResponseBody = expectedResponseBody;
        this.name = name;
        this.timeoutSeconds = timeoutSeconds;
    }
    
    public String getId () {
        return id;
    }
    
    public void setId (String id) {
        this.id = id;
    }
    
    public RequestMethod getRequestMethod () {
        return requestMethod;
    }
    
    public void setRequestMethod (RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
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
                ", requestMethod=" + requestMethod +
                ", expectedHttpStatus=" + expectedHttpStatus +
                ", expectedResponseBody='" + expectedResponseBody + '\'' +
                ", name='" + name + '\'' +
                ", timeoutSeconds=" + timeoutSeconds +
                '}';
    }
}
