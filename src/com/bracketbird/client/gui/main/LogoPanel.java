package com.bracketbird.client.gui.main;

import com.bracketbird.client.LogoDiv;
import com.bracketbird.clientcore.gui.HorizontalComponent;
import com.bracketbird.clientcore.gui.ImageComponent;
import com.bracketbird.clientcore.gui.SimplePanelComponent;
import com.bracketbird.clientcore.style.Horizontal;
import com.bracketbird.clientcore.style.TextLayout;
import com.bracketbird.clientcore.style.Vertical;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 *
 */
public class LogoPanel {

    private HorizontalComponent content;
    private static LogoPanel instance;

    private LogoPanel() {
    }



    public static LogoPanel getInstance() {
        if (instance == null) {
            instance = new LogoPanel();
        }
        return instance;
    }


      public HorizontalComponent getPanel() {
        if (content == null) {
            content = new HorizontalComponent();
            content.add(new LogoDiv(), new TextLayout(0,0,10,0,"40px", "175px", Horizontal.LEFT, Vertical.BOTTOM));
            content.add(new SimplePanelComponent(), new TextLayout(null, "100%"));
        }
        return content;
    }



}
