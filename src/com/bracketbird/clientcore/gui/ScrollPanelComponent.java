package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.ScrollPanel;

/**
 *
 */
public class ScrollPanelComponent extends GuiComponent{
    private ScrollPanel scrollPanel;

    public ScrollPanelComponent(GuiComponent gc) {
        super();
        this.scrollPanel = new ScrollPanel(gc);
       // add(scrollPanel);
    }


    public ScrollPanel getScrollPanel() {
        return scrollPanel;
    }
}
