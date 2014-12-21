package com.bracketbird.client.rtc.event;


/**
 *
 */
public abstract class ModelEvent<T> {

    private boolean fromClient;

    private T oldValue;
    private T newValue;

    public ModelEvent(boolean fromClient, T oldValue, T newValue) {
        this.fromClient = fromClient;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public T getOldValue() {
        return oldValue;
    }

    public T getNewValue() {
        return newValue;
    }

    public boolean isFromClient() {
        return fromClient;
    }

    public boolean isCreate() {
        return false;
    }

    public boolean isDelete() {
        return false;
    }

    public boolean isUpdate() {
        return false;
    }

}
