package com.bracketbird.client.pages.settingspage;

import com.bracketbird.client.Flex;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 *
 */
public class EndLevelComponent extends FlowPanel{

    public EndLevelComponent() {
        setStyleName(Flex.FLEX_CENTER);

        FlowPanel leftEar = new FlowPanel();
        leftEar.setStyleName("levelComponent_ear");
        leftEar.addStyleName("levelComponent_leftEar");
        add(leftEar);

        FlowPanel innerPanel = new FlowPanel();
        innerPanel.setStyleName(Flex.FLEX_CENTER);
        innerPanel.addStyleName("beginAndEndComponent_innerPanel");

        Label l = new Label("End");
        l.setStyleName("levelComponent_name");
        innerPanel.add(l);
        add(innerPanel);

    }
}
