package com.bracketbird.client.gui.rtc.event;


/**
 *
 */
public class DeleteModelEvent<T> extends ModelEvent<T>{

    public DeleteModelEvent(boolean fromClient, T before) {
        super(fromClient, before, null);
    }

    @Override
    public boolean isDelete() {
        return true;
    }

}
