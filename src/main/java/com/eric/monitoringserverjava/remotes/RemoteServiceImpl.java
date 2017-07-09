package com.eric.monitoringserverjava.remotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 *
 */
@Service
public class RemoteServiceImpl implements RemoteService {
	private static Logger LOGGER = LoggerFactory.getLogger(RemoteServiceImpl.class);

	private RestTemplate restTemplate;

	@Autowired
	public RemoteServiceImpl (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public ResponseEntity<String> sendGetRequest (URI url) {
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
		LOGGER.debug("Retrieved {} from endpoint {} with http status {}.", entity.getBody(), url, entity.getStatusCode());

		return entity;
	}
}
