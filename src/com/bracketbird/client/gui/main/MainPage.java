package com.bracketbird.client.gui.main;

import com.google.gwt.dom.client.*;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;
import com.bracketbird.clientcore.gui.*;
import com.bracketbird.clientcore.style.*;


/**
 *
 */
public class MainPage extends Page<MainPageController> {

    private DockLayoutPanel dockPanel;
    private SimplePanelComponent content = new SimplePanelComponent();
    private ScrollPanelComponent customerScrollPanel = new ScrollPanelComponent(content);

    private SimplePanelComponent topHolder = new SimplePanelComponent();

    public MainPage() {
        super();
        dockPanel = new DockLayoutPanel(Style.Unit.PX);
        initWidget(dockPanel);
    }

    public void init() {
        //SimplePanelComponent bottom = new SimplePanelComponent();
        //bottom.getElement().getStyle().setBackgroundColor("#161616");
        
        dockPanel.addNorth(topHolder, 138);
        //dockPanel.addSouth(bottom, 120);
        dockPanel.add(customerScrollPanel);
        content.setHeight("100%");

    }

    protected void setSubPageHolder(Page subPage) {
        TextLayout tlSubPage = new TextLayout("100%", "100%", Horizontal.CENTER);
        TextLayout tlScrollP = new TextLayout();

        if (subPage instanceof FrontPage) {
            //tlScrollP.backgroundBase();
        }

        else {
            tlScrollP.backgroundWhite();
            tlSubPage.padding(20);
        }


        content.add(subPage, tlSubPage);
        StyleIt.add(customerScrollPanel, tlScrollP);


        if (subPage instanceof TopPanelHolder) {
            topHolder.add(((TopPanelHolder) subPage).getTopPanel(), new TextLayout(null, "100%"));
        }
        

    }


    public int getHeight() {
        return dockPanel.getOffsetHeight();
    }
}