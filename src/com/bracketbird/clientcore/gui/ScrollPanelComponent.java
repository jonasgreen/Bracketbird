package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.CustomScrollPanel;

/**
 *
 */
public class ScrollPanelComponent extends GuiComponent{
    private CustomScrollPanel scrollPanel;

    public ScrollPanelComponent(GuiComponent gc) {
        super();
        this.scrollPanel = new CustomScrollPanel(gc);
        initWidget(scrollPanel);
    }


    public CustomScrollPanel getScrollPanel() {
        return scrollPanel;
    }
}
