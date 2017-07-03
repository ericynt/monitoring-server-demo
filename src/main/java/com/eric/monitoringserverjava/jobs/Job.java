package com.eric.monitoringserverjava.jobs;

import com.eric.monitoringserverjava.admin.User;
import com.eric.monitoringserverjava.endpoints.EndpointConfig;
import com.eric.monitoringserverjava.rules.Rule;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class Job {
	@Id
	private String id;
	private List<Rule> rules;
	private EndpointConfig endpointConfig;
	private int intervalSeconds;
	private boolean activated;
	private User user;

	public Job () {
	}

	public Job (String id, List<Rule> rules, EndpointConfig endpointConfig, int intervalSeconds, boolean activated, User user) {
		this.id = id;
		this.rules = rules;
		this.endpointConfig = endpointConfig;
		this.intervalSeconds = intervalSeconds;
		this.activated = activated;
		this.user = user;
	}

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}
	public List<Rule> getRules () {
		return rules;
	}

	public void setRules (List<Rule> rules) {
		this.rules = rules;
	}

	public EndpointConfig getEndpointConfig () {
		return endpointConfig;
	}

	public void setEndpointConfig (EndpointConfig endpointConfig) {
		this.endpointConfig = endpointConfig;
	}

	public int getIntervalSeconds () {
		return intervalSeconds;
	}

	public void setIntervalSeconds (int intervalSeconds) {
		this.intervalSeconds = intervalSeconds;
	}

	public boolean isActivated () {
		return activated;
	}

	public void setActivated (boolean activated) {
		this.activated = activated;
	}

	public User getUser () {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}

	public void addRule (Rule rule) {
		rules.add(rule);
	}

	public void removeRule (String name) {
		rules = rules.stream()
		  .filter(
			r -> !r.getName().equals(name)
		  )
		  .collect(Collectors.toList());
	}

	@Override
	public String toString () {
		return "Job{" +
		  "id='" + id + '\'' +
		  ", rules=" + rules +
		  ", endpointConfig=" + endpointConfig +
		  ", intervalSeconds=" + intervalSeconds +
		  ", activated=" + activated +
		  ", user=" + user +
		  '}';
	}
}
