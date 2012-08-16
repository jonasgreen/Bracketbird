package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.style.*;

/**
 *
 */
public abstract class PanelComponent extends GuiComponent {


    public PanelComponent() {
    }

    public void add(Widget w) {
        try {
            getPanel().add(w);
        }
        catch (Throwable t) {
            t.printStackTrace();//ApplicationController.getInstance().error(this.getClass().getName() +" adding "+w.getClass().getName()+ ". "+ t.getMessage());
        }
    }

    public void add(GuiComponent gc, Layout17 l) {
        getPanel().add(gc);
        gc.layout(l);
    }

    public void add(DataContainer md, Layout17 l){
        GuiComponent gc = md.getGui();
        getPanel().add(gc);
        gc.layout(l);
        super.add(md);
    }

    public abstract Panel getPanel();




}
