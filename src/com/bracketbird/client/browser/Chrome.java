package com.bracketbird.client.browser;

/**
 *
 */
public class Chrome extends Browser {

    public static String ID_NAME = "Chrome";

    protected Chrome(String userAgent) {
        super(userAgent);
    }

    @Override
    public int deriveVersionNumber(String userAgent) {
        return 11;
    }

    @Override
    public boolean isInternetExplorer() {
        return false;
    }


    @Override
    public boolean isSafari() {
        return false;
    }

    @Override
    public boolean isChrome() {
        return true;
    }


    /*
   // In Chrome, the true version is after "Chrome"
         else if ((verOffset = nAgt.indexOf("Chrome")) != -1) {
             browserName = "Chrome";
             fullVersion = nAgt.substring(verOffset + 7);
         }
    */

}
