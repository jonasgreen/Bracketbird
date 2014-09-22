package com.bracketbird.clientcore.appcontrol;

import com.google.gwt.user.client.ui.ScrollPanel;

/**
 *
 */
public class FrontPageContext extends ApplicationContext<ScrollPanel>{

    private static FrontPageContext instance;
    private ScrollPanel scrollPanel = new ScrollPanel();

    private FrontPageContext() {
    }

    public static FrontPageContext get() {
        if (instance == null) {
            instance = new FrontPageContext();
        }
        return instance;
    }

    //Page container and Context widget are the same;

    @Override
    protected ScrollPanel createContextWidget() {
        return scrollPanel;
    }

    @Override
    protected ScrollPanel createPageContainer() {
        return scrollPanel;
    }
}
