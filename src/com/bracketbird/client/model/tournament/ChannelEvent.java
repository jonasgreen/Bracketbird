package com.bracketbird.client.model.tournament;

import java.io.*;

/**
 *
 */
public class ChannelEvent implements Serializable{

    //number
    private long id;

    //encoded model id
    private String encodedModelId;

    private String eventName;

    private String value;

    public ChannelEvent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEncodedModelId() {
        return encodedModelId;
    }

    public void setEncodedModelId(String encodedModelId) {
        this.encodedModelId = encodedModelId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
