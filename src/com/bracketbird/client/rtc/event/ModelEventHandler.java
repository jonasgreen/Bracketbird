package com.bracketbird.client.rtc.event;

public interface ModelEventHandler<T> {
    public void handleEvent(ModelEvent<T> event);
}
