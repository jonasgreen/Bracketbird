package com.bracketbird.client.gui.clientchannel;

/**
 *
 */
public abstract class CreateTeamHandler extends ChannelEventHandler {
    private static final String EVENT_NAME = "createTeam";




    public String getEventName() {
        return EVENT_NAME;
    }
}
