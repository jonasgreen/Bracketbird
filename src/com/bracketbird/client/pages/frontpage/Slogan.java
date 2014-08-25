package com.bracketbird.client.pages.frontpage;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class Slogan extends FlowPanel {

    public Slogan(String header, String text) {
        setStyleName("slogan");
        Label headerLabel = new Label(header);
        headerLabel.setStyleName("slogan_header");
        add(headerLabel);

        Label textLabel = new Label(text);
        textLabel.setStyleName("slogan_text");
        add(textLabel);
    }

}


