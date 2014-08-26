package com.bracketbird.clientcore.appcontrol;


import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public abstract class FlowPanelPage<C extends PageController> extends Page<FlowPanel, C>{

    private FlowPanel contentPanel = new FlowPanel();

    protected FlowPanel getContentPanel(){
        return contentPanel;
    }

    protected void add(Widget w){
        contentPanel.add(w);
    }

    public void setStyleName(String style) {
        contentPanel.setStyleName(style);
    }
}
