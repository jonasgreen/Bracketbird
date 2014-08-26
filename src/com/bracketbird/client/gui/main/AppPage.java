package com.bracketbird.client.gui.main;

import com.bracketbird.clientcore.appcontrol.FlowPanelPage;
import com.bracketbird.clientcore.appcontrol.Page;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;


/**
 *
 */
public class AppPage extends FlowPanelPage<AppPageController> {

    private RootLayoutPanel rootPanel;

    Page content;

    public AppPage() {
        super();
    }


    protected void setSubPageHolder(final Page spc) {
        if(content != null){
           // content.removeFromParent();
        }
        content = spc;
        //rootPanel.add(content);
        //rootPanel.setWidgetLeftRight(content, 0, Style.Unit.PX, 0, Style.Unit.PX);
        rootPanel.forceLayout();
    }


    protected void init() {
        rootPanel = RootLayoutPanel.get();
        //rootPanel.getElement().getStyle().setBackgroundColor("blue");
    }


}