package com.bracketbird.clientcore.appcontrol;

import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public class FrontPageContext extends ApplicationContext<FlowPanel>{

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
    protected FlowPanel createPageContainer() {
        return new FlowPanel();
    }
}
