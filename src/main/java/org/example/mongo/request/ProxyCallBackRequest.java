package org.example.mongo.request;

public class ProxyCallBackRequest {

    private String id;

    private String parentActivityId;

    private String activityIndex;

    private String category;

    private String description;

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

    public String getActivityIndex() {
        return activityIndex;
    }

    public void setActivityIndex(String activityIndex) {
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
}
