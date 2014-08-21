package com.bracketbird.clientcore.appcontrol;

import com.google.gwt.user.client.ui.ScrollPanel;

/**
 *
 */
public class FrontPageContext extends ApplicationContext<ScrollPanel>{

    private static FrontPageContext instance;

    private FrontPageContext() {
    }

    public static FrontPageContext get() {
        if (instance == null) {
            instance = new FrontPageContext();
        }
        return instance;
    }

    @Override
    protected ScrollPanel createPageContainer() {
        return new ScrollPanel();
    }
}
