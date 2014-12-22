package com.bracketbird.client.model.event;

public interface CreateDeleteHandler<T> {

    public void onCreate(CreateEvent<T> event);
    public void onDelete(DeleteEvent<T> event);


}
