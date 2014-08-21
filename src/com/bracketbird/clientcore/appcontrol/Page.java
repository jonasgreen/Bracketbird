package com.bracketbird.clientcore.appcontrol;


import com.google.gwt.user.client.ui.FlowPanel;

/**
 *
 */
public abstract class Page<C extends PageController> extends FlowPanel {

    private C controller;

    void setController(C controller){
        this.controller = controller;
    }

    public C getController() {
        return controller;
    }

    protected abstract void init();

}
