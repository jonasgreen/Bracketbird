package com.bracketbird.client.pages.front;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class Slogan extends FlowPanel {

    public Slogan(String header, String text) {
        setStyleName("frontPage_slogan");
        Label headerLabel = new Label(header);
        headerLabel.setStyleName("frontPage_slogan_header");
        add(headerLabel);

        Label textLabel = new Label(text);
        textLabel.setStyleName("frontPage_slogan_text");
        add(textLabel);
    }

}


