package com.bracketbird.client.browser;

import com.google.gwt.user.client.*;

/**
 *
 */
public abstract class Browser {
    private static Browser instance;
    private static Boolean isVml;

    private int version;

    public static Browser getInstance() {
        if (instance == null) {
            instance = create();
        }
        return instance;
    }

    protected Browser(String userAgent) {
        version = deriveVersionNumber(userAgent);
    }

    public int getVersion() {
        return version;
    }

    public abstract int deriveVersionNumber(String userAgent);

    public abstract boolean isInternetExplorer();
    public abstract boolean isSafari();
    public abstract boolean isChrome();


    private static Browser create() {
        try{
        String userAgent = Window.Navigator.getUserAgent();

        if (userAgent.indexOf(InternetExplorer.ID_NAME) != -1) {
            return new InternetExplorer(userAgent);
        }
        //has to be before safari
        else if (userAgent.indexOf(Chrome.ID_NAME) != -1) {
            return new Chrome(userAgent);
        }
        else if (userAgent.indexOf(Safari.ID_NAME) != -1) {
            return new Safari(userAgent);
        }

        else {
            return new OtherBrowser(userAgent);
        }
        }
        catch (Exception e){
            return new OtherBrowser("Unable to derive browser");
        }
    }


    public static boolean isVML(){
        if(isVml == null){
            isVml = getInstance().isInternetExplorer() && getInstance().getVersion() < 9;
        }
        return isVml;
    }

    public static boolean isChromeBrowser(){
        return getInstance().isChrome();
    }

    public static boolean isIEBrowser(){
        return getInstance().isInternetExplorer();
    }

}
