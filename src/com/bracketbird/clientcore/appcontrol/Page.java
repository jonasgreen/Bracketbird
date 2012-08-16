package com.bracketbird.clientcore.appcontrol;

import com.bracketbird.clientcore.gui.*;

/**
 *
 */
public abstract class Page <C extends PageController> extends GuiComponent {

    private C controller;

    public void setController(C controller){
        this.controller = controller;
    }

    public C getController() {
        return controller;
    }

    public abstract void init();


    protected abstract void setSubPageHolder(Page subPage);

    public void clear(){
        
    }
}
