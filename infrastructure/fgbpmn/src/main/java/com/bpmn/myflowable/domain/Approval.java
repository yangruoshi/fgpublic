package com.bpmn.myflowable.domain;

public class Approval {
    private String id;
    private boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Approval(String id, boolean status) {
        this.id = id;
        this.status = status;
    }
}
