package com.bracketbird.client.gui.rtc.event;

public interface ModelEventHandler<T> {
    public void handleEvent(ModelEvent<T> event);
}
