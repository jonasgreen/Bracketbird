package com.bracketbird.client.gui.rtc.event;


/**
 *
 */
public abstract class ModelEvent<T> {

    private boolean fromClient;

    private T before;
    private T after;

    public ModelEvent(boolean fromClient, T before, T after) {
        this.fromClient = fromClient;
        this.before = before;
        this.after = after;
    }

    public T getBefore() {
        return before;
    }

    public T getAfter() {
        return after;
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
