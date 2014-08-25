package com.bracketbird.client.pages;


import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;

/**
 *
 */
public class TournamentPage<C extends TournamentPageController> extends Page<C> {

    private ScrollPanel scrollPanel;
    private FlowPanel scrollPanelContent;

    public TournamentPage() {
        super();
    }

    public void init() {
        add(getScrollPanel());
    }

    public ScrollPanel getScrollPanel() {
        if (scrollPanel == null) {
            scrollPanel = new ScrollPanel(getScrollPanelContent());
            scrollPanel.getElement().getStyle().setBackgroundColor("yellow");
            scrollPanelContent.setHeight("100%");
            scrollPanelContent.setWidth("100%");
        }
        return scrollPanel;
    }

    public FlowPanel getScrollPanelContent() {
        if (scrollPanelContent == null) {
            scrollPanelContent = new FlowPanel();
        }
        return scrollPanelContent;
    }

}