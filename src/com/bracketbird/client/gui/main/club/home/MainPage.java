package com.bracketbird.client.gui.main.club.home;


import com.bracketbird.client.gui.main.MainAppFrame;
import com.google.gwt.user.client.ui.*;
import com.bracketbird.clientcore.appcontrol.*;

/**
 *
 */
public class MainPage extends Page<MainPageController> {


    public MainPage() {
        super();
    }

    public void init() {
        RootPanel.get("panel").add(MainAppFrame.getInstance());

        RootPanel.get("panel").setHeight("100%");
        MainAppFrame.getInstance().setHeight("100%");
        //Window.enableScrolling(false);
        //Window.setMargin("0px");

        //setting up application frames
        //sp.add();

        MainAppFrame.getInstance().setWidth("100%");
        MainAppFrame.getInstance().setHeight("100%");

    }

    protected void setSubPageHolder(Page subPage) {
        MainAppFrame.getInstance().addCenterContent(subPage);
    }




}