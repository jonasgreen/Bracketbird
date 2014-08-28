package com.bracketbird.clientcore.appcontrol;

import com.google.gwt.user.client.ui.*;

/**
 *
 */
public abstract class ScrollPanelPage<C extends PageController> extends Page<ScrollPanel, C>{

    private ScrollPanel scrollPanel;
    private FlowPanel scrollPanelContent;

    protected ScrollPanel getContentPanel(){
        if (scrollPanel == null) {
            scrollPanel = new ScrollPanel(getScrollPanelContent());
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
        if(scrollPanelContent == null){
            scrollPanelContent = new FlowPanel();
            scrollPanelContent.setStyleName("tournament_scrollPanel");
        }
        return scrollPanelContent;
    }

}
