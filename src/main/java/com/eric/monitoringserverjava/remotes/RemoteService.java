package com.eric.monitoringserverjava.remotes;

import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 *
 */
public interface RemoteService {
    ResponseEntity<String> sendGetRequest (URI url);
}
