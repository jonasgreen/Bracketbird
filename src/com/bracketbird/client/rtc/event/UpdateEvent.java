package com.bracketbird.client.rtc.event;


/**
 *
 */
public class UpdateEvent<T> extends ModelEvent<T>{

    private final T oldValue;
    private final T newValue;

    public UpdateEvent(boolean fromClient, T before, T after) {
        super(fromClient);
        this.oldValue = before;
        this.newValue = after;
    }

    public T getOldValue() {
        return oldValue;
    }

    public T getNewValue() {
        return newValue;
    }
}
