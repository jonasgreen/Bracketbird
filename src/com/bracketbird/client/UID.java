package com.bracketbird.client;


import com.google.gwt.user.client.Window;

/**
 *
 */
public class UID {
    private static long count = 0;

    public static String next() {
        if (count == 1000000) {
            count = 0;
        }
        return count++ + " " + Window.Location.getHostName() + " " + System.currentTimeMillis();
    }


}
