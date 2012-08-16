package com.bracketbird.client.event;

import com.bracketbird.client.model.*;
import com.google.gwt.event.shared.*;

/**
 *
 */
public class MemberCreatedEvent extends GwtEvent {

    private Member member;

    public static Type TYPE = new Type();


    public MemberCreatedEvent(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Type getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(EventHandler handler) {
    	((MembersCreatedHandler)handler).onChange(this);
    }
}