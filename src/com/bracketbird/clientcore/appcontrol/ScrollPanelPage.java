package com.bracketbird.clientcore.appcontrol;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public abstract class ScrollPanelPage<C extends PageController> extends Page<ScrollPanel, C>{

    private ScrollPanel scrollPanel;
    private FlowPanel scrollPanelContent = new FlowPanel();

    protected ScrollPanel getContentPanel(){
        if (scrollPanel == null) {
            scrollPanel = new ScrollPanel(getScrollPanelContent());
            scrollPanel.getElement().getStyle().setBackgroundColor("yellow");
            scrollPanel.setHeight("100%");
            scrollPanel.setWidth("100%");
        }
        return scrollPanel;
    }

    protected void add(Widget w){
        getScrollPanelContent().add(w);
    }

    public void setStyleName(String style) {
        getContentPanel().setStyleName(style);
    }


    public FlowPanel getScrollPanelContent() {
        return scrollPanelContent;
    }

}
