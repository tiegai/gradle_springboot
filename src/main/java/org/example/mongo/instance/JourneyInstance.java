package org.example.mongo.instance;

import org.springframework.data.annotation.Id;

public class JourneyInstance {

    @Id
    private String id;

    private int version;

    private String journeyId;

    private String jobName;

    private int jobStatus;

    private String jobUpdateTime;

    private int instanceStatus;

    private int instanceUpdateTime;

    private int instanceCreatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(String journeyId) {
        this.journeyId = journeyId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobUpdateTime() {
        return jobUpdateTime;
    }

    public void setJobUpdateTime(String jobUpdateTime) {
        this.jobUpdateTime = jobUpdateTime;
    }

    public int getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(int instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public int getInstanceUpdateTime() {
        return instanceUpdateTime;
    }

    public void setInstanceUpdateTime(int instanceUpdateTime) {
        this.instanceUpdateTime = instanceUpdateTime;
    }

    public int getInstanceCreatedTime() {
        return instanceCreatedTime;
    }

    public void setInstanceCreatedTime(int instanceCreatedTime) {
        this.instanceCreatedTime = instanceCreatedTime;
    }
}
