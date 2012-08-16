package com.bracketbird.client.browser;

/**
 *
 */
public class OtherBrowser extends Browser {


    protected OtherBrowser(String userAgent) {
        super(userAgent);
    }

    @Override
    public int deriveVersionNumber(String userAgent) {
        return 0;
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
        return false;
    }

}
