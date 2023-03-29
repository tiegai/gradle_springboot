package org.example.mongo.entity.push;

import java.io.Serializable;

public class PushConfig implements Serializable{

    private String templateId;

    private String cpCode;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

}
