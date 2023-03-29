package org.example.mongo.entity.inbox;

import java.io.Serializable;

public class InboxConfig implements Serializable{

    private String threadId;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }
}
