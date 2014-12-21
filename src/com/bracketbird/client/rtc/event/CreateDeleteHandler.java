package com.bracketbird.client.rtc.event;

public interface CreateDeleteHandler<T> {

    public void onCreate(CreateEvent<T> event);
    public void onDelete(DeleteEvent<T> event);


}
