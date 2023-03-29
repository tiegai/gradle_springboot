package org.example.mongo.entity.audience;

import java.io.Serializable;

public class AudienceConfig implements Serializable{

    private int audienceId;

    private String audienceName;

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
}
