package com.bracketbird.clientcore.gui;

import com.google.gwt.user.client.ui.LayoutPanel;

/**
 *
 */
public class LayoutComponent extends GuiComponent{
    private LayoutPanel panel;

    public LayoutComponent() {
        super();
        panel = new LayoutPanel();
        initWidget(panel);
    }

    public LayoutPanel getPanel() {
        return panel;
    }
}
