package com.bracketbird.client.model.event;


/**
 *
 */
public class CreateEvent<T> extends ModelEvent<T> {

    private final T value;

    public CreateEvent(boolean fromClient, T model) {
        super(fromClient);
        this.value = model;
    }

    public T getValue() {
        return value;
    }
}
