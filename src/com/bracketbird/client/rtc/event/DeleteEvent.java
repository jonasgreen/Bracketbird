package com.bracketbird.client.rtc.event;


/**
 *
 */
public class DeleteEvent<T> extends ModelEvent<T>{

    private final T value;

    public DeleteEvent(boolean fromClient, T model) {
        super(fromClient);
        this.value = model;
    }

    public T getValue() {
        return value;
    }
}
