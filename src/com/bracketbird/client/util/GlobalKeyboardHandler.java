package com.bracketbird.client.util;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.*;

/**
 *
 */
public class GlobalKeyboardHandler {


    public void setupSingleAppKeyboardShortcuts() {
        // Define an inner class to handle the event
        Event.addNativePreviewHandler(new Event.NativePreviewHandler() {
            public void onPreviewNativeEvent(Event.NativePreviewEvent preview) {
                NativeEvent event = preview.getNativeEvent();
                int keycode = event.getKeyCode();



                if(keycode == 27){//ESC
                 //    PopupManager.hide();
                  //  preview.consume();

                }

                // Tell the event handler that this event has been consumed
//                preview.consume();
            }
        });
    }









}
