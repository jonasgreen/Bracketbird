package com.bracketbird.client.rtc.event;


/**
 *
 */
public class CreateModelEvent<T> extends ModelEvent<T>{

    public CreateModelEvent(boolean fromClient, T model) {
        super(fromClient, null, model);
    }

    @Override
    public boolean isCreate() {
        return true;
    }

}