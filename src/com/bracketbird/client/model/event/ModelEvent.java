package com.bracketbird.client.model.event;


/**
 *
 */
public abstract class ModelEvent<T> {

    private boolean fromClient;

    public ModelEvent(boolean fromClient) {
        this.fromClient = fromClient;

    }

    public boolean isFromClient() {
        return fromClient;
    }

}
