package com.bracketbird.client.pages.settingspage;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class LevelComponent extends FlowPanel{


    public LevelComponent(String name) {
        setStyleName("levelComponent");
        Label l = new Label(name);
        l.setStyleName("levelComponent_name");
        add(l);
    }
}
