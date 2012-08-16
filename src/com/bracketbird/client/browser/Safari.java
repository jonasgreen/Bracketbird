package com.bracketbird.client.browser;

/**
 *
 */
public class Safari extends Browser {

    public static String ID_NAME = "Safari";

    protected Safari(String userAgent) {
        super(userAgent);
    }

    @Override
    public int deriveVersionNumber(String userAgent) {
        String s = userAgent.substring(userAgent.indexOf(ID_NAME));

        int indexOfDot = s.indexOf(".");
        if (indexOfDot != -1) {
            return Integer.valueOf(s.substring(ID_NAME.length() + 1, indexOfDot));
        }
        return Integer.valueOf(s.substring(ID_NAME.length() + 1));
    }

    @Override
    public boolean isInternetExplorer() {
        return false;
    }


    @Override
    public boolean isSafari() {
        return true;
    }

    @Override
    public boolean isChrome() {
        return false;
    }


}
