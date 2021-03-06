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
    private long requestDuration;
    private boolean passed;

    public RuleResult (String id, String jobId, LocalDateTime startTime, long requestDuration, boolean passed) {
        this.id = id;
        this.jobId = jobId;
        this.startTime = startTime;
        this.requestDuration = requestDuration;
        this.passed = passed;
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

    public long getRequestDuration () {
        return requestDuration;
    }

    public void setRequestDuration (long requestDuration) {
        this.requestDuration = requestDuration;
    }

    public boolean isPassed () {
        return passed;
    }

    public void setPassed (boolean passed) {
        this.passed = passed;
    }

    @Override
    public String toString () {
        return "RuleResult{" +
                "id='" + id + '\'' +
                ", jobId='" + jobId + '\'' +
                ", startTime=" + startTime +
                ", requestDuration=" + requestDuration +
                ", passed=" + passed +
                '}';
    }
}
