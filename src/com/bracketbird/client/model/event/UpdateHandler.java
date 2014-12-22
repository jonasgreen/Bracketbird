package com.bracketbird.client.model.event;

public interface UpdateHandler<T> {
    public void onUpdate(UpdateEvent<T> event);
}
