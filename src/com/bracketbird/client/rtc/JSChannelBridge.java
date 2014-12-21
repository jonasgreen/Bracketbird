package com.bracketbird.client.rtc;

/**
 *
 */
public class JSChannelBridge {

    public static JSChannelBridgeHandler handler;

    public static native void exportAPI() /*-{
             $wnd.channelOpened = $entry(@com.bracketbird.client.rtc.JSChannelBridge::channelOpened());
             $wnd.messageRecieved = $entry(@com.bracketbird.client.rtc.JSChannelBridge::messageRecieved(Ljava/lang/String;));
             $wnd.errorRecieved = $entry(@com.bracketbird.client.rtc.JSChannelBridge::errorRecieved());
             $wnd.channelClosed = $entry(@com.bracketbird.client.rtc.JSChannelBridge::channelClosed());

       }-*/;




      // CALLS FROM JAVA-SCRIPT

      //called from java-script.
      private static void channelOpened() {
          handler.channelOpened();
      }

      //called from java-script.
      private static void channelClosed() {
          handler.channelClosed();
      }

      //called from java-script.
      private static void messageRecieved(String json) {
          handler.messageRecieved(json);
      }

      //called from java-script.
      private static void errorRecieved() {
          handler.errorRecieved();
      }




}
