package com.bracketbird.client.rtc.event;

import com.bracketbird.client.model.keys.EntityId;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public abstract class REvent<H extends REventHandler, E extends EntityId> implements Serializable{

    private static final long serialVersionUID = -8485391344904512338L;
    private Long eventId;
    private Long stateId;
    private E modelId;
    private boolean changeState = false;
    private Long timeStamp;

    protected REvent() {
    }

    public REvent(Long eventId, E modelId) {
        this.timeStamp = new Date().getTime();
        this.eventId = eventId;
        this.modelId = modelId;
    }


    public Long getEventId() {
        return eventId;
    }

    public E getModelId() {
        return modelId;
    }

    public boolean isFromClient() {
        return eventId == null;
    }

    public boolean isChangeState() {
        return changeState;
    }

    public void setChangeState(boolean changeState) {
        this.changeState = changeState;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEventName(){
        return this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")+1);
    }

    @Override
    public String toString() {
        return "REvent{" +
                "eventId=" + eventId +
                ", name=" + this.getClass().getName() +
                ", stateId=" + stateId +
                ", modelId=" + modelId +
                ", changeState=" + changeState +
                '}';
    }

    public abstract Class<H> getHandler();
    public boolean isUpdateEvent(){
        return false;
    }
}
