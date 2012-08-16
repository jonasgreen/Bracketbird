package com.bracketbird.client.gui.util;


import com.google.gwt.user.client.Window;

/**
 *
 */
public class UID {
    private static long count = 0;

    public static String getUID(){
        return count++ +" " + Window.Location.getHostName() + " "+ System.currentTimeMillis();
    }
}
