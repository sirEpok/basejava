package com.urise.webapp.model;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    //    public Resume() {
    //    }
    //    Resume(String uuid) {
    //            this.uuid = uuid;
    //    }



    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
