package com.bracketbird.client.gui.rtc.event;


/**
 *
 */
public class UpdateModelEvent<T> extends ModelEvent<T>{

    public UpdateModelEvent(boolean fromClient, T before, T after) {
        super(fromClient, before, after);
    }

    @Override
    public boolean isUpdate() {
        return true;
    }

}
