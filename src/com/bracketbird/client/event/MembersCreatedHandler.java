package com.bracketbird.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 *
 */
public interface MembersCreatedHandler extends EventHandler {
    public void onChange(MemberCreatedEvent event);
}
