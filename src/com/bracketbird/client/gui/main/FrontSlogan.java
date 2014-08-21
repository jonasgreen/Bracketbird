package com.bracketbird.client.gui.main;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class FrontSlogan extends FlowPanel {

    public FrontSlogan(String header, String text) {
        setStyleName("slogan");
        Label headerLabel = new Label(header);
        headerLabel.setStyleName("slogan_header");
        add(headerLabel);

        Label textLabel = new Label(text);
        textLabel.setStyleName("slogan_text");
        add(textLabel);
    }

}


