package com.eric.monitoringserverjava.endpoints;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 */
@Document
public class EndpointConfig {
    @Id
    private String id;
    private String name;
    private EndpointType endpointType;
    private Protocol protocol;
    private String host;
    private int port;
    private String path;
    
    public enum EndpointType {REST, SOAP}
    
    public enum Protocol {HTTP, HTTPS}
    
    public EndpointConfig (String id, String name, EndpointType endpointType, Protocol protocol, String host, int
            port, String path) {
        this.id = id;
        this.name = name;
        this.endpointType = endpointType;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
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
    
    public EndpointType getEndpointType () {
        return endpointType;
    }
    
    public void setEndpointType (EndpointType endpointType) {
        this.endpointType = endpointType;
    }
    
    public Protocol getProtocol () {
        return protocol;
    }
    
    public void setProtocol (Protocol protocol) {
        this.protocol = protocol;
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
        return "EndpointConfig{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", endpointType=" + endpointType +
                ", protocol=" + protocol +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", path='" + path + '\'' +
                '}';
    }
}
