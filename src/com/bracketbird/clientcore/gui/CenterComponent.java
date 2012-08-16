package com.bracketbird.clientcore.gui;

import com.bracketbird.clientcore.style.*;

/**
 *
 */
public class CenterComponent extends GuiComponent {

    private HorizontalComponent panel;
    private GuiComponent center;


    public CenterComponent() {
        super();
        this.panel = new HorizontalComponent();
        initWidget(panel);
    }


    public void add(GuiComponent gc, Layout17 layout) {
        removeInternGui();
        center = gc;
        panel.add(gc, layout);
        panel.setHeight("100%");
        panel.setWidth("100%");
    }

    public void removeInternGui() {
        if (center != null) {
            center.removeFromParent();
        }
        center = null;
    }


    public GuiComponent getInternalGuiComponent(){
        return center;
    }



}