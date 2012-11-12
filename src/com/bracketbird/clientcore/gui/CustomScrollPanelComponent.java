package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.CustomScrollPanel;

/**
 *
 */
public class CustomScrollPanelComponent extends GuiComponent{
    private CustomScrollPanel scrollPanel;

    public CustomScrollPanelComponent(GuiComponent gc) {
        super();
        this.scrollPanel = new CustomScrollPanel(gc);
        initWidget(scrollPanel);
    }


    public CustomScrollPanel getScrollPanel() {
        return scrollPanel;
    }
}
