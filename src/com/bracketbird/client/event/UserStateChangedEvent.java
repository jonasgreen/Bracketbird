package com.bracketbird.client.event;

import com.bracketbird.client.model.*;
import com.google.gwt.event.shared.*;

/**
 *
 */
public class UserStateChangedEvent extends GwtEvent {

    public static Type TYPE = new Type();

    private final UserStateConstant newValue;
    private final UserStateConstant oldValue;

    private User user;
    /**
     * Creates a value change event.
     *
     * @param value the value
     * @param newValue new value
     * @param oldValue old value
     */
    public UserStateChangedEvent(User value, UserStateConstant newValue, UserStateConstant oldValue) {
        this.user = value;
        this.newValue= newValue;
        this.oldValue =oldValue;
    }

    public User getUser() {
        return user;
    }

    public UserStateConstant getNewValue() {
        return newValue;
    }

    public UserStateConstant getOldValue() {
        return oldValue;
    }


    public Type getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(EventHandler handler) {
        ((UserStateChangedHandler)handler).onChange(this);
    }
}
