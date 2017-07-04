package com.eric.monitoringserverjava.dashboard;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 *
 */
@Document
public class RuleResult {
	@Id
	private String id;
	private String jobId;
	private LocalDateTime startTime;
	private int requestDuration;
	private boolean status;

	public RuleResult () {
	}

	public RuleResult (String id, LocalDateTime startTime, int requestDuration, boolean status) {
		this.id = id;
		this.startTime = startTime;
		this.requestDuration = requestDuration;
		this.status = status;
	}

	public String getId () {
		return id;
	}

	public void setId (String id) {
		this.id = id;
	}

	public String getJobId () {
		return jobId;
	}

	public void setJobId (String jobId) {
		this.jobId = jobId;
	}

	public LocalDateTime getStartTime () {
		return startTime;
	}

	public void setStartTime (LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public int getRequestDuration () {
		return requestDuration;
	}

	public void setRequestDuration (int requestDuration) {
		this.requestDuration = requestDuration;
	}

	public boolean isStatus () {
		return status;
	}

	public void setStatus (boolean status) {
		this.status = status;
	}

	@Override
	public String toString () {
		return "RuleResult{" +
		  "id='" + id + '\'' +
		  ", startTime=" + startTime +
		  ", requestDuration=" + requestDuration +
		  ", status=" + status +
		  '}';
	}
}
