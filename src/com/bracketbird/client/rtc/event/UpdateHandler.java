package com.bracketbird.client.rtc.event;

public interface UpdateHandler<T> {
    public void onUpdate(UpdateEvent<T> event);
}
