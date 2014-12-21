package com.bracketbird.client.rtc;

/**
 *
 */
public class ClientChannel {

    public ClientChannel(JSChannelBridgeHandler handler) {
        JSChannelBridge.handler = handler;
        JSChannelBridge.exportAPI();
    }


    //ensures that gwt module is loaded before exported api is called from java-script.
    public native void openChannel(String token)
    /*-{
          $wnd.openChannel(token);
      }-*/;


}
