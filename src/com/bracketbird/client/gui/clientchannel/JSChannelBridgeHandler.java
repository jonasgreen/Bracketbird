package com.bracketbird.client.gui.clientchannel;

/**
 *
 */
public interface JSChannelBridgeHandler {

    public void messageRecieved(String json);

    public void channelOpened();

    public void channelClosed();

    public void errorRecieved();



}
