package org.example.mongo.entity.sms;


import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class SmsNode implements Serializable{

    @Id
    private String id;

    private String parentActivityId;

    private int activityIndex;

    private String category;

    private String description;

    private SmsConfig smsConfig;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentActivityId() {
        return parentActivityId;
    }

    public void setParentActivityId(String parentActivityId) {
        this.parentActivityId = parentActivityId;
    }

    public int getActivityIndex() {
        return activityIndex;
    }

    public void setActivityIndex(int activityIndex) {
        this.activityIndex = activityIndex;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SmsConfig getSmsConfig() {
        return smsConfig;
    }

    public void setSmsConfig(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    @Override
    public String toString() {
        return "SmsNode{" +
                "id='" + id + '\'' +
                ", parentActivityId='" + parentActivityId + '\'' +
                ", activityIndex=" + activityIndex +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", smsConfig=" + smsConfig +
                '}';
    }
}
