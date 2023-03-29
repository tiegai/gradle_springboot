package org.example.mongo.request;

import java.io.Serializable;

public class JourneyEntity implements Serializable {

    private String id;

    private int version;

    private String name;

    private String description;

    private String programId;

    private String campaignId;

    private String subCampaignId;

    private String periodicType;

    private String periodicBegin;

    private String periodicEnd;

    private String periodicValues;

    private String periodicTimes;

    private String nextStartTime;

    private int audienceId;

    private String audienceName;

    private String startActivityId;

    private String createdBy;

    private String createdByName;

    private String createdTime;

    private String updatedBy;

    private String updatedByName;

    private String updatedTime;

    private String approvedBy;

    private String approvedByName;

    private String approvedTime;

    private String suspendedBy;

    private String suspendedByName;

    private String suspendedTime;

    private String status;

    private Object[] journeyActivities;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getSubCampaignId() {
        return subCampaignId;
    }

    public void setSubCampaignId(String subCampaignId) {
        this.subCampaignId = subCampaignId;
    }

    public String getPeriodicType() {
        return periodicType;
    }

    public void setPeriodicType(String periodicType) {
        this.periodicType = periodicType;
    }

    public String getPeriodicBegin() {
        return periodicBegin;
    }

    public void setPeriodicBegin(String periodicBegin) {
        this.periodicBegin = periodicBegin;
    }

    public String getPeriodicEnd() {
        return periodicEnd;
    }

    public void setPeriodicEnd(String periodicEnd) {
        this.periodicEnd = periodicEnd;
    }

    public String getPeriodicValues() {
        return periodicValues;
    }

    public void setPeriodicValues(String periodicValues) {
        this.periodicValues = periodicValues;
    }

    public String getPeriodicTimes() {
        return periodicTimes;
    }

    public void setPeriodicTimes(String periodicTimes) {
        this.periodicTimes = periodicTimes;
    }

    public String getNextStartTime() {
        return nextStartTime;
    }

    public void setNextStartTime(String nextStartTime) {
        this.nextStartTime = nextStartTime;
    }

    public int getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(int audienceId) {
        this.audienceId = audienceId;
    }

    public String getAudienceName() {
        return audienceName;
    }

    public void setAudienceName(String audienceName) {
        this.audienceName = audienceName;
    }

    public String getStartActivityId() {
        return startActivityId;
    }

    public void setStartActivityId(String startActivityId) {
        this.startActivityId = startActivityId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovedByName() {
        return approvedByName;
    }

    public void setApprovedByName(String approvedByName) {
        this.approvedByName = approvedByName;
    }

    public String getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getSuspendedBy() {
        return suspendedBy;
    }

    public void setSuspendedBy(String suspendedBy) {
        this.suspendedBy = suspendedBy;
    }

    public String getSuspendedByName() {
        return suspendedByName;
    }

    public void setSuspendedByName(String suspendedByName) {
        this.suspendedByName = suspendedByName;
    }

    public String getSuspendedTime() {
        return suspendedTime;
    }

    public void setSuspendedTime(String suspendedTime) {
        this.suspendedTime = suspendedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object[] getJourneyActivities() {
        return journeyActivities;
    }

    public void setJourneyActivities(Object[] journeyActivities) {
        this.journeyActivities = journeyActivities;
    }
}
