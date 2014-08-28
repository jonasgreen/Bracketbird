package com.bracketbird.clientcore.appcontrol;


import com.google.gwt.user.client.ui.Panel;

/**
 *
 */
public abstract class Page<W extends Panel, C extends PageController> {

    private C controller;

    void setController(C controller){
        this.controller = controller;
    }

    public C getController() {
        return controller;
    }

    protected abstract void init();

    protected abstract W getContentPanel();
}
