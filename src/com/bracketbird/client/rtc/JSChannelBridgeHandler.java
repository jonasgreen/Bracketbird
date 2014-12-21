package com.bracketbird.client.rtc;

/**
 *
 */
public interface JSChannelBridgeHandler {

    public void messageRecieved(String json);

    public void channelOpened();

    public void channelClosed();

    public void errorRecieved();



}
