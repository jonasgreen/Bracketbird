package com.bracketbird.client.browser;

/**
 *
 */
public class InternetExplorer extends Browser {

    public static String ID_NAME = "MSIE";

    protected InternetExplorer(String userAgent) {
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
        return true;
    }


    @Override
    public boolean isSafari() {
        return false;
    }

    @Override
    public boolean isChrome() {
        return false;
    }
}
